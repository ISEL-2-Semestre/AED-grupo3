import java.util.PriorityQueue
/*
fun left(i: Int) = 2*i + 1
fun right(i: Int) = 2*i + 2
fun parent(i: Int) = (i - 1)/2
data class Utente(
    val name: String,
    val utenteId : Int,
    var priotity : Int
)

data class PriorityClient(
    val array: Array<Utente?>,
    val cmp: (u1: Utente?, u2: Utente?)-> Int,
    var size: Int
)

fun offer(e: Utente?){
    //adiamos o elemento à fila prioritária O(logn)

}

fun PriorityClient.peek(): Utente? {
    //obtém o elemento mais prioritário O(1)
    return if (isEmpty())null else array[0]
}

fun PriorityQueue<Utente?>.offer(utente: Utente): Boolean{
    if (size == array.size) return false
    array[size++] = utente
    decreaseKey(size-1)
    return true
}

fun PriorityQueue<Utente?>.decreseKey(pos: Int) {
    var pai = parent(pos)
    var actualPos = pos
    while (pai >= 0 && cmp(array[pai], array[actualPos]) > 0){
        exchange(array, actualPos, pai)
        actualPos = pai
        pai = parent(pai)
    }
}
fun PriorityQueue<Utente?>.poll(): Utente?{
    val ut = peek()
    if (ut != null){
        array[0] = array[--size]
        minHeapify(/*array, size, */0/*, cmp*/)
    }
    return ut
    //obtém e remove o element mais prioritário O(logn)
}

fun PriorityClient.isEmpty(): Boolean = size == 0
    // verifica se a fila prioritária está vazia O(1)

fun PriorityClient.minHeapify(pos: Int){
    var pai = pos
    val l = left(pos)
    val r = right(pos)
    if (l < size && cmp(array[l] , array[pai]) < 0) pai = l
    if (r < size && cmp(array[r], array[pai]) < 0) pai = r
    exchange(array, pai, pos)
    minHeapify(pai)
}

fun main(){
    val cmp ={u1: Utente, u2: Utente -> u1.priotity - u2.priotity}
}

 */