fun selectionSort(array:IntArray, left: Int, right:Int){
    for(i in left until right){
        var min = i
        for(j in i+1 .. right){
            if(array[min]>array[j]) min=j
        }
        exchange(array,min,i)
    }
}

fun exchange(a: IntArray, i: Int, j: Int) {
    val temp = a[i]
    a[i] = a[j]
    a[j] = temp
}
