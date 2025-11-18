package lc1_999.lc200_299

import common.IntTreeNode
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.math.max

//Solved
//https://leetcode.com/problems/binary-tree-longest-consecutive-sequence/description/
class Task298 {
    fun longestConsecutive(root: IntTreeNode?): Int {
        return Solution(root).longestConsecutive()
    }

    class Solution(private val root: IntTreeNode?) {
        private var maxLength = 0
        fun longestConsecutive(): Int {
            longestConsecutive(requireNotNull(root), 1)
            return maxLength
        }

        private fun longestConsecutive(node: IntTreeNode, length: Int) {
            maxLength = max(maxLength, length)
            if (node.left != null) {
                val leftNode = requireNotNull(node.left)
                val newLeftCnt = if (isDiffEq1(leftNode, node)) length + 1 else 1
                longestConsecutive(leftNode, newLeftCnt)
            }
            if (node.right != null) {
                val rightNode = requireNotNull(node.right)
                val newRightCnt = if (isDiffEq1(rightNode, node)) length + 1 else 1
                longestConsecutive(rightNode, newRightCnt)
            }
        }

        private fun isDiffEq1(node1: IntTreeNode, node2: IntTreeNode) = node1.`val` - node2.`val` == 1
    }
}

private class Task298Test {
    private val task = Task298()

    @Test
    fun test1() {
        val tree = IntTreeNode(1).apply {
            right = IntTreeNode(3).apply {
                left = IntTreeNode(2)
                right = IntTreeNode(4).apply {
                    right = IntTreeNode(5)
                }
            }
        }
        Assertions.assertEquals(3, task.longestConsecutive(tree))
    }

    @Test
    fun test2() {
        val tree = IntTreeNode(2).apply {
            right = IntTreeNode(3).apply {
                left = IntTreeNode(2).apply {
                    left = IntTreeNode(1)
                }
            }
        }
        Assertions.assertEquals(2, task.longestConsecutive(tree))
    }

    @Test
    fun test3() {
        val tree = IntTreeNode(2).apply {
            right = IntTreeNode(3).apply {
                left = IntTreeNode(4)
                right = IntTreeNode(4).apply {
                    right = IntTreeNode(6)
                    left = IntTreeNode(5)
                }
            }
        }
        Assertions.assertEquals(4, task.longestConsecutive(tree))
    }
}