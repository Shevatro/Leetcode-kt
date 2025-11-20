package lc1_999.lc800_899

import common.IntTreeNode
import common.toLevelsList
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

//Similar to Beyond Cracking The Coding Interview, Solved
//https://leetcode.com/problems/increasing-order-search-tree/description/
class Task897 {
    fun increasingBST(root: IntTreeNode?): IntTreeNode? {
        return Solution().increasingBST(root)
    }

    class Solution {
        private val newRoot = IntTreeNode(0)
        private var p: IntTreeNode? = newRoot
        fun increasingBST(root: IntTreeNode?): IntTreeNode? {
            dfs(root)
            return newRoot.right
        }

        fun dfs(node: IntTreeNode?) {
            if (node == null) return
            dfs(node.left)
            addNode(node)
            dfs(node.right)
        }

        private fun addNode(node: IntTreeNode) {
            node.left = null
            p?.right = node
            p = p?.right
        }
    }
}

private class Task897Test {
    private val task = Task897()

    @Test
    fun test1() {
        val tree = IntTreeNode(5).apply {
            left = IntTreeNode(3).apply {
                left = IntTreeNode(2).apply {
                    left = IntTreeNode(1)
                }
                right = IntTreeNode(4)
            }
            right = IntTreeNode(6).apply {
                right = IntTreeNode(8).apply {
                    left = IntTreeNode(7)
                    right = IntTreeNode(9)
                }
            }
        }
        val expected = listOf(1, null, 2, null, 3, null, 4, null, 5, null, 6, null, 7, null, 8, null, 9)
        Assertions.assertEquals(expected, task.increasingBST(tree)?.toLevelsList())
    }

    @Test
    fun test2() {
        val tree = IntTreeNode(5).apply {
            left = IntTreeNode(1)
            right = IntTreeNode(7)
        }
        val expected = listOf(1, null, 5, null, 7)
        Assertions.assertEquals(expected, task.increasingBST(tree)?.toLevelsList())
    }
}