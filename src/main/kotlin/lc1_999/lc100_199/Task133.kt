package lc1_999.lc100_199
//From a learning section, wrong
//https://leetcode.com/problems/clone-graph/
class Task133 {
    fun cloneGraph(node: Node?): Node? {
        if (node == null) return null
        val visitedVertexes = hashMapOf<Int, Unit>()
        val newNode = Node(node.`val`)
        cloneGraph(visitedVertexes, node, newNode)
        return newNode
    }

    private fun cloneGraph(visitedVertexes: MutableMap<Int, Unit>, node: Node?, newNode: Node) {
        val nodeInd = node?.`val` ?: return
        if (visitedVertexes[nodeInd] == null) {
            visitedVertexes[nodeInd] = Unit
            for (relatedNote in node.neighbors) {
                val newRelatedNode = Node(relatedNote!!.`val`)
                newNode.neighbors.add(newRelatedNode)
                cloneGraph(visitedVertexes, relatedNote, newRelatedNode)
            }
        }
    }

    class Node(var `val`: Int) {
        var neighbors: MutableList<Node?> = ArrayList()
        override fun toString(): String {
            return "$`val` $neighbors"
        }
    }
}

fun main() {
    val task = Task133()
    val graph1 = createTest1()
    printGraph(graph1)
//    println(task.cloneGraph(graph1))
}

private fun printGraph(node: Task133.Node?, visitedVertex: MutableMap<Int, Unit> = mutableMapOf()) {
    if (node == null) return
    if (visitedVertex[node.`val`]!=null)return
    visitedVertex[node.`val`] = Unit
    print(node.`val`)
    for (relatedNote in node.neighbors) {
        print(" [")
        printGraph(relatedNote, visitedVertex)
        print(" ] ")
    }
}

private fun createTest1(): Task133.Node? {
    val node1 = Task133.Node(1)
    val node2 = Task133.Node(2)
    val node3 = Task133.Node(3)
    val node4 = Task133.Node(4)
    node1.neighbors = mutableListOf(node2, node4)
    node2.neighbors = mutableListOf(node1, node3)
    node3.neighbors = mutableListOf(node2, node4)
    node4.neighbors = mutableListOf(node1, node3)
    return node1
}