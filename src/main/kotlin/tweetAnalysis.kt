import java.text.SimpleDateFormat
import java.util.*
import java.io.File
import kotlin.math.abs


data class Tweet(val created_at: String, val hashtags: List<String>, val id: Long, val uid: Long)

private const val TWEET_PARTS = 4

fun loadTweets(filename: String): List<Tweet> {
    val tweets = mutableListOf<Tweet>()
    File(filename).forEachLine { line ->
        val parts = line.split("; ")
        if (parts.size == TWEET_PARTS) {
            val createdAt = parts[0].split(": ")[1].trim('\"')
            val id = parts[2].split(": ")[1].toLong()
            val uid = parts[3].split(": ")[1].trim('}', '\"').toLong()
            val hashtags = parts[1].split(": ")[1].trim('[', ']', '\"').split(", ")
            tweets.add(Tweet(createdAt, hashtags, id, uid))
        }
    }
    return tweets
}

fun dateStringToSeconds(dateString: String): Long {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    dateFormat.timeZone = TimeZone.getTimeZone("UTC") // Set the timezone if necessary
    try {
        val date = dateFormat.parse(dateString)// Parse the date string into a Date
        // Calculate the number of milliseconds since (January 1, 1970, 00:00:00 UTC)
        val milliseconds = date.time
        return milliseconds / 1000 // Convert milliseconds to seconds
    } catch (e: Exception) {
        e.printStackTrace() // Handle parsing exceptions, if any
    }
    return -1 // Return -1 if parsing fails
}

fun nearest(tweets: List<Tweet>, k: Int, dateTime: String): List<Tweet> {
    val dateInSeconds = dateStringToSeconds(dateTime)
    //abs -> valor absoluto
    return tweets.sortedBy { abs(dateStringToSeconds(it.created_at) - dateInSeconds) }.take(k)
}

fun mostMentioned(tweets: List<Tweet>, hashtags: List<String>): String {
    val counts = mutableMapOf<String, Int>()
    for (hashtag in hashtags) {
        counts[hashtag] = tweets.count { it.hashtags.contains(hashtag) }
    }
    return counts.maxByOrNull { it.value }?.key ?: ""
}



fun processCommand(command: String, tweets: List<Tweet>) {
    val parts = command.split(" ")
    when (parts[0]) {
        "moreMentioned" -> {
            val hashtags = File(parts[1]).readLines()
            println(mostMentioned(tweets, hashtags))
        }
        "nearest" -> {
            val k = parts[1].toInt()
            val dateTime = parts.slice(2 until parts.size).joinToString(" ").trim('\"')
            println(nearest(tweets, k, dateTime))
        }
    }
}

fun main(args: Array<String>) {
    val tweets = loadTweets("tweetExample.twt")
    while (true) {
        print("Enter command: ")
        val command = readln()
        processCommand(command, tweets)
    }
}
