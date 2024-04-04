//Este sort é O(log n), mas só pode ser usado em casos especificos
//Quando a Gama (variabilidade dos dados -> EX: 1 / 10000 / 10 / -10000) é muito grande, n podemos utilizar o algoritmo
//A Gama para poder utilizar este sort tem que ser O(n) ou proximo disso, n pode ser muito discrepante
//Só funciona para array de inteiros (Um array de inteiros em kotlin nunca é vaizo, fica sempre com 0)

fun countingSort(arrayA: IntArray, min: Int, max: Int): IntArray {
    val arrayB = IntArray(arrayA.size)
    val arrayC = IntArray(max - min + 1)
    for(a in arrayA) {
        arrayC[a - min]++
    }
    for (i in 1..< arrayC.size) {
        arrayC[i] = arrayC[i] + arrayC[i - 1]
    }
    for(j in arrayB.size-1 downTo 0) {
        arrayB[arrayC[arrayA[j] - min]] = arrayA[j]
        arrayC[arrayA[j] - min]--
    }
    return arrayB
}

//Seja n o nº de elementos a ordenar:
// O algoritmo countingSort é O(n) se a gama T'=[max-min+1] for T'= O(n), sendo que max é o maior elemento do array
//e min é o menor elemento do array