fun insertionSort(array:IntArray, left:Int, right:Int){
    var v = 0
    for(i in left+1 .. right){
        v = array[i]
        var j = i
        while(j>left && v<array[j-1]){
            array[j] = array[j-1]
            j--
        }
        array[j] = v
    }
}

fun insertionSortNew(arr: IntArray) {
    val n = arr.size
    for (i in 1 until n) {
        val key = arr[i]
        var j = i - 1
        while (j >= 0 && arr[j] > key) {
            arr[j + 1] = arr[j]
            j--
        }
        arr[j + 1] = key
    }
}
