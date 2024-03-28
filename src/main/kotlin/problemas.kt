
//exercicio 1
fun countPairsThatSumN(v: IntArray, l: Int, r: Int, s: Int): Int{
    var p = 0
    var left = l
    var right = r
    while (left < right){
        val j = v[right] + v[left]
        if (j < s) ++left
        if (j > s) --right
        if (j == s){
            p++
            left++
            right--
        }
    }
    return p
}

// exercicio 2

// 2.1 O(n^3)
fun countEachThreeElementsThatSumN21(v: IntArray, l: Int, r: Int, s: Int): Int{
    var p = 0
    for (i in l..r - 2){
        for (j in i+1 ..r - 1){
            for (k in j+1 .. r){
                if (v[i] + v[j] + v[k] == s && v[i] != v[j] && v[k] != v[i] && v[j] != v[k])p++
            }
        }
    }
    return p
}

//2.2 O(n^2logn)
fun countEachThreeElementsThatSumN22(v: IntArray, l: Int, r: Int, s: Int): Int{
    var p = 0
    //var lastTriple = Triple(0,0,0)
    mergeSort(v,l,r)
    for (i in l .. r - 2){
        for (j in i + 1 .. r - 1){
            val k = s - v[i] - v[j]
            if (k < v[j]) break
            val c = binarySearch(v, j + 1, r, k)
            if (c != -1 && v[i] != v[j] && v[j] != k && v[i] != k /*&& lastTriple != Triple(v[i], v[j], c)*/) p++
            //lastTriple = Triple(v[i], v[j], c)
        }
    }
    return p
}

//2.3 O(n^2)


fun countEachThreeElementsThatSumN23(v: IntArray, l: Int, r: Int, s: Int): Int {
    var p = 0
    mergeSort(v, l, r)
    for (i in l .. r - 2) {
        var left = l + i + 1
        var right = r
        while (left < right){
            val j = v[right] + v[left] + v[i]
            if (j < s) ++left
            if (j > s) --right
            if (j == s){
                if (v[right] != v[left] && v[right] != v[i] && v[left] != v[i]) p++
                --right
            }
        }
    }
    return p
}

//exercicio 3
fun countInRange(v: IntArray, l: Int, r: Int, min: Int, max: Int): Int{

}


fun main () {
    //print(countPairsThatSumN(intArrayOf(1, 2, 3, 4, 5, 6, 7, 8), 0, 7, 10))
    println(countEachThreeElementsThatSumN21(intArrayOf(1, 2, 3, 3, 5, 6, 7, 8), 0, 7, 15))
    println(countEachThreeElementsThatSumN22(intArrayOf(1, 2, 3, 3, 5, 6, 7, 8), 0, 7, 15))
    println(countEachThreeElementsThatSumN23(intArrayOf(1, 2, 3, 3, 5, 6, 7, 8), 0, 7, 15))
}