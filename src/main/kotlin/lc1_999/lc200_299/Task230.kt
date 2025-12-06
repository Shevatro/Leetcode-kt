package lc1_999.lc200_299

import common.IntTreeNode
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

//From Beyond Cracking The Coding Interview, Solved
//https://leetcode.com/problems/kth-smallest-element-in-a-bst/
class Task230 {
    fun kthSmallest(root: IntTreeNode?, k: Int): Int {
        return Solution2(k).kthSmallest(root)
    }

    private class Solution2(private val k: Int) {
        private var count = 1
        private var result: Int? = null
        fun kthSmallest(root: IntTreeNode?): Int {
            dfs(root)
            return result ?: -1
        }

        private fun dfs(node: IntTreeNode?) {
            if (node == null || result != null) return
            dfs(node.left)
            if (count == k) result = node.`val`
            count++
            dfs(node.right)
        }
    }
}

private class Task230Test {
    private val task = Task230()

    @Test
    fun test1() {
        val tree = IntTreeNode(3).apply {
            left = IntTreeNode(1).apply {
                right = IntTreeNode(2)
            }
            right = IntTreeNode(4)
        }
        Assertions.assertEquals(1, task.kthSmallest(tree, 1))
    }

    @Test
    fun test2() {
        val tree = IntTreeNode(5).apply {
            left = IntTreeNode(3).apply {
                left = IntTreeNode(2).apply {
                    left = IntTreeNode(1)
                }
                right = IntTreeNode(4)
            }
            right = IntTreeNode(6)
        }
        Assertions.assertEquals(3, task.kthSmallest(tree, 3))
    }

    @Test
    fun test3() {
        val tree = IntTreeNode(4).apply {
            left = IntTreeNode(2).apply {
                right = IntTreeNode(3)
            }
            right = IntTreeNode(5)
        }
        Assertions.assertEquals(2, task.kthSmallest(tree, 1))
    }
}