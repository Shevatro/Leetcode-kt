package bctci.trees

import common.IntTreeNode
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Task35_15 {
    fun detectDuplicatesInBST(root: IntTreeNode?): Boolean {
        return Solution().validateBST(root)
    }

    private class Solution() {
        private var previous: Int? = null
        fun validateBST(root: IntTreeNode?): Boolean {
            return dfs(root)
        }

        private fun dfs(node: IntTreeNode?): Boolean {
            if (node == null) return false
            val lefResult = dfs(node.left)
            if (previous != null && node.`val` == previous) return true
            previous = node.`val`
            val rightResult = dfs(node.right)
            return lefResult || rightResult
        }
    }
}

private class Task35_15Test {
    private val task = Task35_15()

    @Test
    fun test1() {
        val tree = IntTreeNode(5).apply {
            left = IntTreeNode(2).apply {
                right = IntTreeNode(4)
            }
            right = IntTreeNode(9).apply {
                left = IntTreeNode(9).apply {
                    right = IntTreeNode(9)
                }
                right = IntTreeNode(11)
            }
        }
        Assertions.assertEquals(true, task.detectDuplicatesInBST(tree))
    }

    @Test
    fun test2() {
        Assertions.assertEquals(false, task.detectDuplicatesInBST(null))
    }

    @Test
    fun test3() {
        val tree = IntTreeNode(1)
        Assertions.assertEquals(false, task.detectDuplicatesInBST(tree))
    }

    @Test
    fun test4() {
        val tree = IntTreeNode(5).apply {
            left = IntTreeNode(2).apply {
                left = IntTreeNode(1)
                right = IntTreeNode(4)
            }
            right = IntTreeNode(8).apply {
                left = IntTreeNode(6)
                right = IntTreeNode(9)
            }
        }
        Assertions.assertEquals(false, task.detectDuplicatesInBST(tree))
    }
}