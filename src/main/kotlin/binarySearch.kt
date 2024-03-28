fun binarySearch(a: IntArray, l: Int, r: Int, elem: Int): Int {
    if(r < l) return -1
    val mid = (l+r)/2
    return when {
        a[mid]==elem -> mid
        a[mid]>elem -> binarySearch(a, l, mid - 1, elem)
        else -> binarySearch(a, mid+1, r, elem)
    }
}