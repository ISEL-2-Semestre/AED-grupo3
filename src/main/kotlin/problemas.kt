
fun countPairsThatSumN(v: IntArray, l: Int, r: Int, s: Int): Int{
    var p = 0
    for (i in l..r){
        for (j in i+1 ..r){
            if (v[i] + v[j] == s) p++
        }
    }
    return p
}

fun countEachThreeElementsThatSumN(v: IntArray, l: Int, r: Int, s: Int): Int{
    var p = 0
    for (i in l..r){
        for (j in i+1 ..r){
            for (k in j+1 .. r){
                if (v[i] + v[j] + v[k] == s && v[i] != v[j] && v[k] != v[i] && v[j] != v[k])p++
            }
        }
    }
    return p
}

fun countInRange(v: IntArray, l: Int, r: Int, min: Int, max: Int): Int{

}


fun main (){
    //print(countPairsThatSumN(intArrayOf(1, 2, 3, 4, 5, 6, 7, 8), 0, 7, 4))
    print(countEachThreeElementsThatSumN(intArrayOf(1, 2, 3, 4, 5, 6, 7, 8), 0, 7, 9))
}