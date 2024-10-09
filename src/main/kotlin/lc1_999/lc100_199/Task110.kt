package lc1_999.lc100_199

import common.IntTreeNode
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.math.abs
import kotlin.math.max

//From Cracking The Coding Interview, Not Solved but was pretty close
//https://leetcode.com/problems/balanced-binary-tree/
class Task110 {
    fun isBalanced(root: IntTreeNode?): Boolean {
        return calculateLevels(root) >= 0
    }

    private fun calculateLevels(node: IntTreeNode?): Int {
        if (node == null) return 0
        val leftLevels = node.left?.let { calculateLevels(it) } ?: 0
        val rightLevels = node.right?.let { calculateLevels(it) } ?: 0
        if (leftLevels == -1 || rightLevels == -1 || abs(leftLevels - rightLevels) > 1) {
            return -1
        }
        return max(leftLevels, rightLevels) + 1
    }
}

private class Task110Test {
    @Test
    fun isBalancedTest() {
        val task = Task110()
        val tree = IntTreeNode(3).apply {
            left = IntTreeNode(9)
            right = IntTreeNode(20).apply {
                left = IntTreeNode(15)
                right = IntTreeNode(7)
            }
        }
        val result = task.isBalanced(tree)
        Assertions.assertEquals(true, result)

        val tree2 = IntTreeNode(1).apply {
            left = IntTreeNode(2).apply {
                left = IntTreeNode(3).apply {
                    left = IntTreeNode(4)
                }
            }
            right = IntTreeNode(2).apply {
                right = IntTreeNode(3).apply {
                    right = IntTreeNode(4)
                }
            }
        }
        val result2 = task.isBalanced(tree2)
        Assertions.assertEquals(false, result2)

        val tree3 = IntTreeNode(1).apply {
            left = IntTreeNode(2).apply {
                left = IntTreeNode(3).apply {
                    left = IntTreeNode(4)
                    right = IntTreeNode(4)
                }
                right = IntTreeNode(3)
            }
            right = IntTreeNode(2)
        }
        val result3 = task.isBalanced(tree3)
        Assertions.assertEquals(false, result3)
    }
}