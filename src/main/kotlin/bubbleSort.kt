fun bubbleSort(a: IntArray, l: Int, r: Int){
    for (i in l until r){
        for (j in r downTo i + 1){
            if (a[j] < a[j-1]) exchange(a, j, j-1)
        }
    }
}

