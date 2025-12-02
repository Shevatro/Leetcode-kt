package lc1_999.lc100_199

import common.IntTreeNode
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.math.max

//Solved
//https://leetcode.com/problems/maximum-depth-of-binary-tree/description/
class Task104 {
    fun maxDepth(root: IntTreeNode?): Int {
        return Solution().maxDepth(root)
    }

    class Solution() {
        private var maxLevel = 0
        fun maxDepth(root: IntTreeNode?): Int {
            dfs(root, 1)
            return maxLevel
        }

        private fun dfs(node: IntTreeNode?, level: Int) {
            if (node == null) return
            maxLevel = max(maxLevel, level)
            dfs(node.left, level + 1)
            dfs(node.right, level + 1)
        }
    }
}

private class Task104Test {
    private val task = Task104()

    @Test
    fun test1() {
        val tree = IntTreeNode(3).apply {
            left = IntTreeNode(9)
            right = IntTreeNode(20).apply {
                left = IntTreeNode(15)
                right = IntTreeNode(7)
            }
        }
        Assertions.assertEquals(3, task.maxDepth(tree))
    }

    @Test
    fun test2() {
        val tree = IntTreeNode(1).apply {
            right = IntTreeNode(2)
        }
        Assertions.assertEquals(2, task.maxDepth(tree))
    }

    @Test
    fun test3() {
        Assertions.assertEquals(0, task.maxDepth(null))
    }
}