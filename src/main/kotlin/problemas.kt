//binary search
private fun binarySearch(a: IntArray, l: Int, r: Int, elem: Int): Int {
    if(r < l) return -1
    val mid = (l+r)/2
    return when {
        a[mid]==elem -> mid
        a[mid]>elem -> binarySearch(a, l, mid - 1, elem)
        else -> binarySearch(a, mid+1, r, elem)
    }
}

//exercicio 1
fun countPairsThatSumN(v: IntArray, l: Int, r: Int, s: Int): Int{
    var pairCount = 0
    var left = l
    var right = r
    while (left < right){
        val pair = v[right] + v[left]
        when {
            pair < s -> left++
            pair > s -> right--
            else -> {
                pairCount++
                left++
                right--
            }
        }
    }
    return pairCount
}

// exercicio 2

// 2.1 O(n^3)
fun countEachThreeElementsThatSumN21(v: IntArray, l: Int, r: Int, s: Int): Int{
    var count = 0
    for (i in l..r - 2){
        for (j in i+1..<r){
            for (k in j+1 .. r){
                if (v[i] + v[j] + v[k] == s &&
                    i != j && j != k && i != k)
                    count++
            }
        }
    }
    return count
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
                left++
            }
        }
    }
    return p
}

//exercicio 3
fun countInRange(v: IntArray, l: Int, r: Int, min: Int, max: Int): Int {
    val lower = binarySearch(v,min,l,r+1)
    val upper = binarySearch(v,max, l, r + 1)

    return if (lower >= 0 && upper >= 0) {
        upper - lower + 1
    } else {
        -(lower + 1) - (-(upper + 1))
    }
}
