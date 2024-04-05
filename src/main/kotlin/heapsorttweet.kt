fun left(i: Int) = 2*i + 1
fun right(i: Int) = 2*i + 2
fun parent(i: Int) = (i - 1)/2

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

