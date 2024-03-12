fun mergeWithoutCreatingArrays(a: IntArray, left: Int, right: Int, b: IntArray, c: IntArray) {
    var iA = left
    var iB = 0
    var iC = 0
    while (iB < b.size && iC < c.size) {
        if(b[iB] <= c[iC]) {
            a[iA]=b[iB] // tbm podemos fazer a[iA]=b[iB++], só incrementa dps da linha feita
            iB++
        }
        else {
            a[iA]=c[iC] // tbm podemos fazer a[iA]=c[iC++], só incrementa dps da linha feita
            iC++
        }
        iA++
    }
    while (iB < b.size) {
        a[iA++]=b[iB++]
        //iB++
        //iA++
    }
    while (iC < c.size) {
        a[iA++]=c[iC++]
        //iC++
        //iA++
    }
}

fun mergeCreatingArrays(a: IntArray, l: Int, mid: Int, r: Int) {
    val b = IntArray(mid-l+1)
    val c = IntArray(r -mid)
    for(i in b.indices) {
       b[i]=a[i+l]
    }
    for(i in c.indices) {
        c[i]=a[i+mid+1]
    }
    mergeWithoutCreatingArrays(a, l, r, b, c)
}

fun mergeSort(a: IntArray, l: Int, r: Int) {
    if(l<r) {
        val mid = (l+r)/2
        mergeSort(a, l, mid)
        mergeSort(a, mid+1, r)
        mergeCreatingArrays(a, l, mid, r)
    }
}

fun main() {
    val a = intArrayOf(1,43,3,95,10,5,2,7,6,8,24,0,67,19,4,9)
    mergeSort(a,0,a.size-1)
    println(a.toList())
}