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





fun merge(tweets: Array<Pair<Tweet, Long>>, aux: Array<Pair<Tweet, Long>>, low: Int, mid: Int, high: Int) {
    for (k in low..high) {
        aux[k] = tweets[k]
    }
    var i = low
    var j = mid + 1
    for (k in low..high) {
        when {
            i > mid -> tweets[k] = aux[j++]
            j > high -> tweets[k] = aux[i++]
            aux[j].first.createdAt < aux[i].first.createdAt -> tweets[k] = aux[j++]
            else -> tweets[k] = aux[i++]
        }
    }
}

//heap sort 3,66 s
//quicksort 4,77 s
//mergesort 4,51 s