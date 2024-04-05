import java.text.SimpleDateFormat
import java.util.*
import java.io.BufferedReader
import java.io.FileReader
import kotlin.math.abs


//ORDENAR AS DATAS!!!!! USAR BINARY SEARCH PARA PROCURAR DATA QUE QUEREMOS!!!

data class Tweet(val createdAt: String, val hashtags: List<String>, val id: Long, val uid: Long)

private const val TWEET_PARTS = 4

fun loadTweets(filename: String): List<Tweet> {
    val tweets = mutableListOf<Tweet>()
    val reader = BufferedReader(FileReader(filename))
    reader.use { r ->
        r.forEachLine { line ->
            val parts = line.split("; ")
            if (parts.size == TWEET_PARTS) {
                val createdAt = parts[0].split(": ")[1].trim('\"')
                val id = parts[2].split(": ")[1].toLong()
                val uid = parts[3].split(": ")[1].trim('}', '\"').toLong()
                val hashtags = parts[1].split(": ")[1].trim('[', ']', '\"').split(", ").map { it.trim('\"') }
                tweets.add(Tweet(createdAt, hashtags, id, uid))
            }
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
    return tweets.sortedBy { abs(dateStringToSeconds(it.createdAt) - dateInSeconds) }.take(k)
}



fun nearest2(tweets: List<Tweet>, k: Int, dateTime: String): List<Tweet> {
    val dateInSeconds = dateStringToSeconds(dateTime)
    val tweetsWithDifference = tweets.map { Pair(it, abs(dateStringToSeconds(it.createdAt) - dateInSeconds)) }
    val array = tweetsWithDifference.toTypedArray()
    heapSort(array, array.size)
    return array.take(k).map { it.first }
}


fun binarySearchtweet(tweets: Array<Pair<Tweet, Long>>, target: Long): Tweet {
    var left = 0
    var right = tweets.size - 1
    while (left < right) {
        val mid = left + (right - left) / 2
        if (tweets[mid].second == target) {
            return tweets[mid].first
        }
        if (tweets[mid].second < target) {
            left = mid + 1
        } else {
            right = mid
        }
    }
    // At this point, left == right.
    // Check which one is closer to target, tweets[left - 1] or tweets[left]?
    if (left > 0 && (tweets[left].second - target >= target - tweets[left - 1].second)) {
        return tweets[left - 1].first
    }
    return tweets[left].first
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
            val reader = BufferedReader(FileReader(parts[1]))
            val hashtags = reader.use { it.readLines() }
            println(mostMentioned(tweets, hashtags))
        }
        "nearest" -> {
            val k = parts[1].toInt()
            val dateTime = parts.slice(2..<parts.size).joinToString(" ").trim('\"')
            println(nearest2(tweets,k,dateTime))
        }
    }
}
fun main() {
    val tweets = loadTweets("tweetsSample.twt")
    print("introduza instrução: ")
    val command = readln()
    val init= java.lang.System.currentTimeMillis()
    processCommand(command, tweets)
    val end = java.lang.System.currentTimeMillis()
    val diff = end-init
    println(diff)
}

