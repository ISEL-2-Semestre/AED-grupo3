package part1

data class Node<E>(var item: E, var left:Node<E>?, var right:Node<E>?)

fun printIf( root:Node<Int>?, predicate: (Int) -> Boolean ): Int {
    if (root == null) return 0
    var count = printIf(root.right, predicate)
    if (predicate(root.item)) {
        println(root.item)
        count++
    }
    return count + printIf(root.left, predicate)
}

fun countBetween(root: Node<Int>?, min: Int, max: Int): Int {
    return when {
        root == null -> 0
        root.item in min..max -> countBetween(root.left, min, max) + countBetween(root.right, min, max) + 1
        root.item < min -> countBetween(root.right, min, max)
        else -> countBetween(root.left, min, max)
    }
}

fun isChildrenSum(root:Node<Int>?):Boolean{
    if(root == null) return true
    val s = when{
        root.left?.item != null && root.right?.item != null ->  root.left!!.item + root.right!!.item
        root.left?.item == null && root.right?.item != null ->  root.right!!.item
        root.left?.item != null && root.right?.item == null ->  root.left!!.item
        else -> 0
    }
    if(s==0) return true
    return if (s == root.item) isChildrenSum(root.left) || isChildrenSum(root.right)
    else false
}

