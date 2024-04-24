//Exercicio 1.1
fun <T> kElement(a: Array<T>, l: Int, r: Int, k: Int, compare: (T, T) -> Int): T {
    var left = l
    var right = r
    val pivot = a[k]

    while (left <= right) {
        while (compare(a[left], pivot) < 0) left++
        while (compare(a[right], pivot) > 0) right--

        if (left <= right) {
            val temp = a[left]
            a[left] = a[right]
            a[right] = temp
            left++
            right--
        }
    }

    if (l < right && k <= right) return kElement(a, l, right, k, compare)
    if (left < r && k >= left) return kElement(a, left, r, k, compare)
    return a[k]
}

//Exercicio 1.2
fun evaluateRPN(expression: String): Int {
    val stack = ArrayDeque<Int>()
    expression.split(" ").forEach { token ->
        when (token) {
            "+", "-", "*", "/" -> {
                val secondOperand = stack.removeLast()
                val firstOperand = stack.removeLast()
                val result = when (token) {
                    "+" -> firstOperand + secondOperand
                    "-" -> firstOperand - secondOperand
                    "*" -> firstOperand * secondOperand
                    "/" -> {
                        if (secondOperand == 0) throw ArithmeticException("Divisão por zero")
                        firstOperand / secondOperand
                    }
                    else -> throw IllegalArgumentException("Operador inválido: $token")
                }
                stack.addLast(result)
            }
            else -> {
                val number = token.toIntOrNull() ?: throw NumberFormatException("Número inválido: $token")
                stack.addLast(number)
            }
        }
    }
    return stack.last()
}
