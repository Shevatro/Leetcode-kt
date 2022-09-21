package common

class IntSinglyNode(var `val`: Int) {
    var next: IntSinglyNode? = null

    fun print() {
        val list = mutableListOf<Int>()
        var node: IntSinglyNode? = this
        while (node != null) {
            list.add(node.`val`)
            node = node.next
        }
        println(list.toString())
    }
}