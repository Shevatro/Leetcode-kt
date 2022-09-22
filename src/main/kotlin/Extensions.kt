import common.IntSinglyNode

fun IntArray.toString2(): String {
    return joinToString(prefix = "[", postfix = "]", separator = " ")
}

fun IntArray.toIntSinglyNode(): IntSinglyNode? {
    var head: IntSinglyNode? = null
    for (i in this.lastIndex downTo 0) {
        val newHead = IntSinglyNode(this[i])
        head = newHead.apply { next = head }
    }
    return head
}

fun <T> Array<T>.toString2(): String {
    return if (this.isNotEmpty() && (this[0] is IntArray || this[0] is CharArray)) {
        joinToString(prefix = "[", postfix = "]", separator = " ") {
            if (it is IntArray) {
                it.toString2()
            } else {
                (it as CharArray).toString2()
            }
        }
    } else {
        joinToString(prefix = "[", postfix = "]", separator = " ")
    }
}

fun CharArray.toString2(): String {
    return joinToString(prefix = "[", postfix = "]", separator = " ")
}