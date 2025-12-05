package bctci.trees

import common.IntTreeNode
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Task35_14 {
    fun validateBST(root: IntTreeNode?): Boolean {
        return Solution().validateBST(root)
    }

    private class Solution() {
        private var previous: Int? = null
        fun validateBST(root: IntTreeNode?): Boolean {
            return dfs(root)
        }

        private fun dfs(node: IntTreeNode?): Boolean {
            if (node == null) return true
            val lefResult = dfs(node.left)
            if (previous != null && node.`val` < requireNotNull(previous)) return false
            previous = node.`val`
            val rightResult = dfs(node.right)
            return lefResult && rightResult
        }
    }
}

private class Task35_14Test {
    private val task = Task35_14()

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
        Assertions.assertEquals(true, task.validateBST(tree))
    }

    @Test
    fun test2() {
        val tree = IntTreeNode(5).apply {
            left = IntTreeNode(2).apply {
                right = IntTreeNode(4)
            }
            right = IntTreeNode(12).apply {
                left = IntTreeNode(10).apply {
                    right = IntTreeNode(9)
                }
                right = IntTreeNode(13)
            }
        }
        Assertions.assertEquals(false, task.validateBST(tree))
    }

    @Test
    fun test3() {
        Assertions.assertEquals(true, task.validateBST(null))
    }

    @Test
    fun test4() {
        val tree = IntTreeNode(1)
        Assertions.assertEquals(true, task.validateBST(tree))
    }

    @Test
    fun test5() {
        val tree = IntTreeNode(5).apply {
            left = IntTreeNode(2)
            right = IntTreeNode(4)
        }
        Assertions.assertEquals(false, task.validateBST(tree))
    }

    @Test
    fun test6() {
        val tree = IntTreeNode(5).apply {
            left = IntTreeNode(6)
            right = IntTreeNode(7)
        }
        Assertions.assertEquals(false, task.validateBST(tree))
    }
}