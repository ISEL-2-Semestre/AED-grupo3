import java.util.Stack

fun quickSort(array: IntArray, left: Int, right: Int) {
    if (left<right) {
        val i = partition(array, left, right)
        quickSort(array, left, i - 1)
        quickSort(array, i+1, right)
    }
}

fun quickSortIterativo(array: IntArray, left: Int, right: Int) {
    val stack = Stack<Int>()
    stack.push(left)
    stack.push(right)
    while (!stack.isEmpty()) {
        var r = stack.pop()
        var l = stack.pop()
        if (r <= l) continue
        var i = partition(array, l, r)
        if (l - i >= r - i) {
            stack.push(l)
            stack.push(i-1)
        }
        stack.push(i+1)
        stack.push(r)
        if (l - i < r - i) {
            stack.push(l)
            stack.push(i-1)
        }
    }
}

fun partition(array: IntArray, left:Int, right: Int): Int {
    var i = left - 1
    var j = right
    val pivot = array[right]
    while (true) {
        while ( i < right && array[++i] < pivot);
        while ( j > left && array[--j] > pivot);
        if (i >= j) break
        exchange(array, i, j)
    }
    exchange(array, i, right)
    return i
}

private fun exchange(a: Array<Int>, i: Int, j: Int) {
    val tmp = a[i]
    a[i] = a[j]
    a[j] = tmp
}
