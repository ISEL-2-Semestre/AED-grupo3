fun left(i: Int) = 2*i + 1
fun right(i: Int) = 2*i + 2
fun parent(i: Int) = (i - 1)/2

fun exchange(a: Array<Int>, i: Int, j: Int) {
    val tmp = a[i]
    a[i] = a[j]
    a[j] = tmp
}

fun maxHeapify(a: Array<Int>, n: Int, pos: Int) {
    val l = left(pos)
    val r = right(pos)
    var pai = pos
    if(l < n && a[l] > a[pai])
        pai = l
    if(r < n && a[r] > a[pai])
        pai = r
    if(pai != pos) {
        exchange(a, pai, pos)
        maxHeapify(a, n, pai)
    }
}

//Passo 2: Sem nome específico / Assume que o array já está em Max-Heap
fun transform(a: Array<Int>, n: Int) {
    var s = n
    while(s > 0) {
        exchange(a,0,--s) //Faz a troca do 1º com o último
        maxHeapify(a, s, 0)
    }
}

fun buildMaxHeap(a: Array<Int>, n: Int) { // O( n.log n, base 2)
    var pai = parent(n-1)
    while(pai>=0) {
        maxHeapify(a, n, pai)
        pai--
    }
}

fun increaseKey(a: Array<Int>, n: Int, pos: Int) {
    var actualPos = pos
    var pai = parent(actualPos)
    while(actualPos > 0 && a[actualPos]>a[pos]) {
        exchange(a, actualPos, pai)
        actualPos = pai
        pai = parent(actualPos)
    }
}
fun heapSort(a: Array<Int>,n: Int) {
    buildMaxHeap(a, n)
    transform(a, n)
}

/*
Heap Sort -> O(n.log n, base 2)

O heapsort tbm pode ser feito com min ao inves de max, ficamos com as fun:
    buildMinHeap
    minHeap
    minHeapify
    decreaseKey
*/
