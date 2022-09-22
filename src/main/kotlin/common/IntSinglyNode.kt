package common

class IntSinglyNode(var `val`: Int) {
    var next: IntSinglyNode? = null

    fun print(shouldPrintRoot: Boolean = true) {
        val list = mutableListOf<Int>()
        var node: IntSinglyNode? = this
        while (node != null) {
            if (shouldPrintRoot || node != this) {
                list.add(node.`val`)
            }
            node = node.next
        }
        println(list.toString())
    }
}

fun IntSinglyNode.addAtTail(newNode: IntSinglyNode) {
    var node: IntSinglyNode = this
    while (node.next != null) {
        node = requireNotNull(node.next)
    }
    node.next = newNode
}

fun IntSinglyNode.get(index: Int): IntSinglyNode? {
    var node: IntSinglyNode? = this
    for (i in 0 until index) {
        node = node?.next
        if (node == null) return null
    }
    return node
}