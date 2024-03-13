import java.util.Random

fun maximalSubArrayy(a: IntArray, l:Int, r: Int): Triple {
    var bestLeft = l
    var bestRight = l - 1
    var bestSum = 0
    var actualSum = 0
    for (i in l..r) {
        actualSum = 0
        for (j in i..r) {
            actualSum += a[j]
            if (actualSum >= bestSum) {
                bestSum = actualSum
                bestLeft = i
                bestRight = j
            }
        }
    }
    return Triple(bestLeft, bestSum, bestRight)
}

fun linearMaximalSubArray(a:IntArray,l:Int,r:Int):Triple{
    var bestleft = 0
    var bestright = -1
    var bestSum = 0
    var actualSum = 0
    for (i in l..r) {
        actualSum += a[i]
        if (actualSum >= bestSum) {
            bestSum = actualSum
            bestright=i
        }
        if(actualSum < 0) {
            actualSum = 0
            bestleft = i
            bestright = i + 1
        }
    }
    return Triple(bestleft, bestSum, bestright)
}

fun randomArray(n:Int):IntArray{
    val random = kotlin.random.Random(500)
    val arrayexample = IntArray(n)
    for(i in 0 .. arrayexample.size-1){
        arrayexample[i] = random.nextInt(0, n)
    }
    return arrayexample
}


fun main(){ //main do submaximal com n^2
    val number = readln().toInt()
    val a = randomArray(number)
    val init= java.lang.System.currentTimeMillis()
    linearMaximalSubArray(a,0, a.size-1)
    val end = java.lang.System.currentTimeMillis()
    val diff = end - init
    println(a.toList())
    print(diff.toFloat()/1000)
    print(" segundos")
}