fun binarySearch(a:IntArray, left:Int, right:Int, elem:Int):Int{
    if (left>right) return -1
    val mid = (left+right)/2
    return when{
        a[mid] == elem -> mid
        a[mid] > elem -> binarySearch(a,left,mid-1,elem)
        else -> binarySearch(a,left, mid+1, elem)
    }
}