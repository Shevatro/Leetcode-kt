package lc1_999.lc100_199

import common.IntTreeNode
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

//From Cracking The Coding Interview, Solved
//https://leetcode.com/problems/binary-tree-level-order-traversal/
class Task102 {
    fun levelOrder(root: IntTreeNode?): List<List<Int>> {
        val list = ArrayList<ArrayList<Int>>()
        val queue = ArrayDeque<Node>()
        root?.let { queue.addLast(Node(it, 0)) }
        while (queue.isNotEmpty()) {
            val item = queue.removeFirst()
            val nodeRef = item.nodeRef
            val level = item.level
            if (list.getOrNull(level) == null) {
                list.add(ArrayList(listOf(nodeRef.`val`)))
            } else {
                list[level].add(nodeRef.`val`)
            }
            val newLevel = level + 1
            nodeRef.left?.let { queue.addLast(Node(it, newLevel)) }
            nodeRef.right?.let { queue.addLast(Node(it, newLevel)) }
        }
        return list
    }

    private data class Node(
        val nodeRef: IntTreeNode,
        val level: Int
    )
}

private class Task102Test {
    @Test
    fun levelOrderTest() {
        val task = Task102()
        val tree = IntTreeNode(3).apply {
            left = IntTreeNode(9)
            right = IntTreeNode(20).apply {
                left = IntTreeNode(15)
                right = IntTreeNode(7)
            }
        }
        val result = task.levelOrder(tree)
        Assertions.assertEquals(listOf(listOf(3), listOf(9, 20), listOf(15, 7)), result)

        val result2 = task.levelOrder(null)
        Assertions.assertEquals(emptyList<Int>(), result2)
    }
}