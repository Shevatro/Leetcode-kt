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