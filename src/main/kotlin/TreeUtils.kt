data class Node<E>(var item: E, var left:Node<E>?, var right:Node<E>?)

fun printIf(root: Node<Int>?, predicate: (Int) -> Boolean) {
    if (root != null) {
        printIf(root.left, predicate)
        if (predicate(root.item)) {
            println(root.item)
        }
        printIf(root.right, predicate)
    }
}

fun countBetween(root: Node<Int>?, min: Int, max: Int): Int {
    if (root == null) {
        return 0
    }
    val leftCount = countBetween(root.left, min, max)
    val rightCount = countBetween(root.right, min, max)
    return leftCount + rightCount + if (root.item in min..max) 1 else 0
}

fun findCommonAncestor(root: Node<Int>?, n1: Int, n2: Int): Node<Int>? {
    if (root == null) {
        return null
    }
    if (root.value > n1 && root.value > n2) {
        return findCommonAncestor(root.previous, n1, n2)
    }
    if (root.value < n1 && root.value < n2) {
        return findCommonAncestor(root.next, n1, n2)
    }
    return root
}

fun main() {
    // Criando uma árvore binária de exemplo
    val root = Node(12,
        previous = Node(25,
            previous = Node(20)
        ),
        next = Node(10,
            next = Node(5)
        )
    )

    // Testando a função printIf
    println("Números maiores que 15:")
    printIf(root) { it > 15 }

    // Testando a função countBetween
    val min = 5
    val max = 15
    val count = countBetween(root, min, max)
    println("Número de elementos entre $min e $max: $count")

    // Testando a função findCommonAncestor
    val n1 = 20
    val n2 = 5
    val commonAncestor = findCommonAncestor(root, n1, n2)
    println("Ancestral comum mais próximo de $n1 e $n2: ${commonAncestor?.value}")
}
