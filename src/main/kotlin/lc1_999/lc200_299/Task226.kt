package lc1_999.lc200_299

import common.IntTreeNode
import common.toLevelsList
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

//Beyond Cracking The Coding Interview, Solved
//https://leetcode.com/problems/invert-binary-tree/
class Task226 {
    fun invertTree(root: IntTreeNode?): IntTreeNode? {
        dfs(root)
        return root
    }

    private fun dfs(node: IntTreeNode?) {
        if (node == null) return
        dfs(node.left)
        dfs(node.right)
        val temp = node.left
        node.left = node.right
        node.right = temp
    }
}

private class Task226Test {
    private val task = Task226()

    @Test
    fun test1() {
        val tree = IntTreeNode(4).apply {
            left = IntTreeNode(2).apply {
                left = IntTreeNode(1)
                right = IntTreeNode(3)
            }
            right = IntTreeNode(7).apply {
                left = IntTreeNode(6)
                right = IntTreeNode(9)
            }
        }
        val expected = listOf(4, 7, 2, 9, 6, 3, 1)
        Assertions.assertEquals(expected, task.invertTree(tree)?.toLevelsList())
    }

    @Test
    fun test2() {
        val tree = IntTreeNode(2).apply {
            left = IntTreeNode(1)
            right = IntTreeNode(3)
        }
        val expected = listOf(2, 3, 1)
        Assertions.assertEquals(expected, task.invertTree(tree)?.toLevelsList())
    }

    @Test
    fun test3() {
        Assertions.assertEquals(null, task.invertTree(null))
    }
}