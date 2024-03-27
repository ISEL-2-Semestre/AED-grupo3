
//exercicio 1
fun countPairsThatSumN(v: IntArray, l: Int, r: Int, s: Int): Int{
    var p = 0
    var left = l
    var right = r
    while (left < right){
        val count = v[right] + v[left]
        if (count < s) ++left
        if (count > s) --right
        if (count == s){
            p++
            left++
            right--
        }
    }
    return p
}

// exercicio 2

// 2.1
fun countEachThreeElementsThatSumN21(v: IntArray, l: Int, r: Int, s: Int): Int{
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

//2.2
fun countEachThreeElementsThatSumN22(v: IntArray, l: Int, r: Int, s: Int): Int{

}


/*fun countInRange(v: IntArray, l: Int, r: Int, min: Int, max: Int): Int{

}*/


fun main (){
    print(countPairsThatSumN(intArrayOf(1, 2, 3, 4, 5, 6, 7, 8), 0, 7, 10))
    //print(countEachThreeElementsThatSumN(intArrayOf(1, 2, 3, 4, 5, 6, 7, 8), 0, 7, 9))
}