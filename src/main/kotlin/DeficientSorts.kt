private fun left(i: Int) = 2*i + 1
private fun right(i: Int) = 2*i + 2
private fun parent(i: Int) = (i - 1)/2

fun exchange(a: Array<Pair<Tweet, Long>>, i: Int, j: Int) {
    val tmp = a[i]
    a[i] = a[j]
    a[j] = tmp
}

fun transform(a: Array<Pair<Tweet, Long>>, n: Int) {
    var s = n
    while(s > 0) {
        exchange(a,0,--s) //Faz a troca do 1º com o último
        maxHeapify(a, s, 0)
    }
}

fun maxHeapify(a: Array<Pair<Tweet, Long>>, n: Int, pos: Int) {
    val l = left(pos)
    val r = right(pos)
    var pai = pos
    if(l < n && a[l].second > a[pai].second)
        pai = l
    if(r < n && a[r].second > a[pai].second)
        pai = r
    if(pai != pos) {
        exchange(a, pai, pos)
        maxHeapify(a, n, pai)
    }
}

fun buildMaxHeap(a: Array<Pair<Tweet, Long>>, n: Int) {
    var pai = parent(n-1)
    while(pai>=0) {
        maxHeapify(a, n, pai)
        pai--
    }
}

fun heapSort(a: Array<Pair<Tweet, Long>>,n: Int) {
    buildMaxHeap(a, n)
    transform(a, n)
}


fun quickSort(tweets: Array<Pair<Tweet, Long>>, n: Int) {
    quickSortHelper(tweets, 0, n - 1)
}

fun quickSortHelper(tweets: Array<Pair<Tweet, Long>>, low: Int, high: Int) {
    if (low < high) {
        val pi = partition(tweets, low, high)
        quickSortHelper(tweets, low, pi - 1)
        quickSortHelper(tweets, pi + 1, high)
    }
}

fun partition(tweets: Array<Pair<Tweet, Long>>, low: Int, high: Int): Int {
    val mid = low + (high - low) / 2
    val pivotIndices = arrayOf(low, mid, high)
    pivotIndices.sortBy { tweets[it].first.createdAt }
    val pivotIndex = pivotIndices[1]
    val pivot = tweets[pivotIndex]
    tweets[pivotIndex] = tweets[high]
    tweets[high] = pivot

    var i = low - 1
    for (j in low until high) {
        if (tweets[j].first.createdAt <= pivot.first.createdAt) {
            i++
            val temp = tweets[i]
            tweets[i] = tweets[j]
            tweets[j] = temp
        }
    }
    val temp = tweets[i + 1]
    tweets[i + 1] = tweets[high]
    tweets[high] = temp
    return i + 1
}





fun mergeSort(a: Array<Pair<Tweet, Long>>, low: Int, high: Int) {
    if (low < high) {
        val mid = (low + high) / 2
        mergeSort(a, low, mid)
        mergeSort(a, mid + 1, high)
        merge(a, low, mid, high)
    }
}

fun merge(a: Array<Pair<Tweet, Long>>, low: Int, mid: Int, high: Int) {
    val left = a.copyOfRange(low, mid + 1)
    val right = a.copyOfRange(mid + 1, high + 1)
    var i = 0
    var j = 0
    var k = low

    while (i < left.size && j < right.size) {
        if (left[i].second <= right[j].second) {
            a[k] = left[i]
            i++
        } else {
            a[k] = right[j]
            j++
        }
        k++
    }

    while (i < left.size) {
        a[k] = left[i]
        i++
        k++
    }

    while (j < right.size) {
        a[k] = right[j]
        j++
        k++
    }
}


fun insertionSort(a: Array<Pair<Tweet, Long>>, n: Int) {
    for (i in 1 until n) {
        val key = a[i]
        var j = i - 1

        while (j >= 0 && a[j].second > key.second) {
            a[j + 1] = a[j]
            j = j - 1
        }
        a[j + 1] = key
    }
}


fun selectionSort(array: Array<Pair<Tweet, Long>>, left: Int, right: Int) {
    for (i in left until right) {
        var min = i
        for (j in i + 1..right) {
            if (array[min].second > array[j].second) min = j
        }
        exchange(array, min, i)
    }
}

fun bubbleSort(a: Array<Pair<Tweet, Long>>, n: Int) {
    for (i in 0 until n - 1) {
        for (j in 0 until n - i - 1) {
            if (a[j].second > a[j + 1].second) {
                val temp = a[j]
                a[j] = a[j + 1]
                a[j + 1] = temp
            }
        }
    }
}
