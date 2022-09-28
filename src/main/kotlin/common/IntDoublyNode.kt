package common

class IntDoublyNode(var `val`: Int) {
    var next: IntDoublyNode? = null
    var prev: IntDoublyNode? = null

    fun print(shouldPrintRoot: Boolean = true) {
        val list = mutableListOf<Int>()
        var node: IntDoublyNode? = this
        while (node != null) {
            if (shouldPrintRoot || node != this) {
                list.add(node.`val`)
            }
            node = node.next
        }
        println(list.toString())
    }
}

fun IntDoublyNode?.printAll() {
    if (this == null) {
        println("[]")
        return
    }
    var node = this
    while (node != null) {
        println(node.`val`.toString() + " " + node.prev?.`val` + " " + node.next?.`val`)
        node = node.next
    }
}

fun IntDoublyNode?.equals(otherHead: IntDoublyNode?): Boolean {
    if (this == null || otherHead == null) {
        return this?.`val` == otherHead?.`val`
    }
    var node1 = this
    var node2 = otherHead
    while (node1 != null && node2 != null) {
        if (node1.`val` != node2.`val`) return false
        node1 = node1.next
        node2 = node2.next
    }
    return node1?.`val` == node2?.`val`
}