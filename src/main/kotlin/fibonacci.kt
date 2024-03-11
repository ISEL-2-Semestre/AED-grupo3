

fun fibonaccirecursive(n:Int):Int =
    if (n == 0 || n == 1) 1
    else fibonaccirecursive(n-1)+ fibonaccirecursive(n-2)

fun fibonacci2(n:Int):Int{
    val array = IntArray(n+1)
    array[0] = 1
    array[1] = 1
    for (i in 2 .. n){
        array[i] = array[i-1] + array[i-2]
    }
    return array[n]
}


fun fibonacci3(n:Int):Int{
    var num1 = 1
    var num2 = 1
    for (i in 3..n){
        val num3 = num1
        num1 = num1 + num2
        num2 = num3

    }
    return num1
}
fun main() {
    println(fibonacci3(7000000))
}



//Desafio :faz uma implementação reduzindo a complexidade espacial