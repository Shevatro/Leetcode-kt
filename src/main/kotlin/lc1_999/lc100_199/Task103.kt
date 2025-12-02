package lc1_999.lc100_199

import common.IntTreeNode
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.LinkedList

//From Beyond Cracking The Coding Interview, Solved
//https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
class Task103 {
    fun zigzagLevelOrder(root: IntTreeNode?): List<List<Int>> {
        if (root == null) return emptyList()
        var curLevel = -1
        val queue = ArrayDeque<IntTreeNode>()
        val result = mutableListOf<List<Int>>()
        queue.addLast(root)
        while (queue.isNotEmpty()) {
            curLevel++
            val temp = LinkedList<Int>()
            repeat(queue.size) {
                val item = queue.removeFirst()
                if (curLevel % 2 == 0) temp.addLast(item.`val`) else temp.addFirst(item.`val`)
                if (item.left != null) queue.addLast(requireNotNull(item.left))
                if (item.right != null) queue.addLast(requireNotNull(item.right))
            }
            result.add(temp)
        }
        return result
    }
}

private class Task103Test {
    private val task = Task103()

    @Test
    fun test1() {
        val tree = IntTreeNode(3).apply {
            left = IntTreeNode(9)
            right = IntTreeNode(20).apply {
                left = IntTreeNode(15)
                right = IntTreeNode(7)
            }
        }
        val expected = listOf(listOf(3), listOf(20, 9), listOf(15, 7))
        Assertions.assertEquals(expected, task.zigzagLevelOrder(tree))
    }

    @Test
    fun test2() {
        val tree = IntTreeNode(1)
        val expected = listOf(listOf(1))
        Assertions.assertEquals(expected, task.zigzagLevelOrder(tree))
    }

    @Test
    fun test3() {
        Assertions.assertEquals(emptyList<List<Int>>(), task.zigzagLevelOrder(null))
    }

    @Test
    fun test4() {
        val tree = IntTreeNode(1).apply {
            left = IntTreeNode(2).apply {
                left = IntTreeNode(4)
            }
            right = IntTreeNode(3).apply {
                right = IntTreeNode(5)
            }
        }
        val expected = listOf(listOf(1), listOf(3, 2), listOf(4, 5))
        Assertions.assertEquals(expected, task.zigzagLevelOrder(tree))
    }
}