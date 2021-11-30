package lc400_499

//Solved
//https://leetcode.com/problems/n-ary-tree-level-order-traversal/
class Task429 {
    fun levelOrder(root: Node?): List<List<Int>> {
        val result: MutableList<MutableList<Int>> = mutableListOf()
        levelOrder(root, 0, result)
        return result
    }

    private fun levelOrder(root: Node?, level: Int, result: MutableList<MutableList<Int>>) {
        if (root == null) return
        if (result.getOrNull(level) == null) result.add(mutableListOf())
        result[level].add(root.`val`)
        for (item in root.children) {
            levelOrder(item, level + 1, result)
        }
    }
}

class Node(var `val`: Int) {
    var children: List<Node?> = listOf()
}

fun main() {
    val obj = Task429()
    val example1 = Node(1).apply {
        children = listOf(Node(3).apply { children = listOf(Node(5), Node(6)) }, Node(2), Node(4))
    }
    println(obj.levelOrder(example1))

}