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

fun IntSinglyNode.getByValue(value: Int): IntSinglyNode? {
    var node: IntSinglyNode? = this
    while (node!=null) {
        if (node.`val` == value) return node
        node = node.next
    }
    return null
}

fun IntSinglyNode.getTail(): IntSinglyNode? {
    var node: IntSinglyNode? = this
    while (node?.next != null) {
        node = node.next
    }
    return node
}

fun IntSinglyNode?.equals(otherHead: IntSinglyNode?): Boolean {
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

fun IntSinglyNode?.equalsInCycle(otherHead: IntSinglyNode?): Boolean {
    if (this == null || otherHead == null) {
        return this?.`val` == otherHead?.`val`
    }
    var node1 = this
    var node2 = otherHead
    var node1Fast = node1
    var node2Fast = node2
    while (node1 != null && node2 != null) {
        if (node1.`val` != node2.`val`) return false
        node1 = node1.next
        node2 = node2.next
        node1Fast = node1Fast?.next?.next
        node2Fast = node2Fast?.next?.next
        if (node1 == node1Fast || node2 == node2Fast) return true
    }
    return node1?.`val` == node2?.`val`
}

fun IntSinglyNode?.printInCycle() {
    val list = mutableListOf<Int>()
    var pSlow = this
    var pFast = pSlow?.next
    while (pSlow != pFast) {
        pSlow?.`val`?.let { list.add(it) }
        pSlow = pSlow?.next
        pFast = pFast?.next?.next
    }
    pSlow?.`val`?.let { list.add(it) }
    println(list.toString())
}