package bctci.trees

import common.IntTreeNode
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.math.min

class Task35_5 {
    fun countTriangles(root: IntTreeNode?): Int {
        return Solution(root).countTriangles()
    }

    private class Solution(private val root: IntTreeNode?) {
        private var amount = 0
        fun countTriangles(): Int {
            dfs(root)
            return amount
        }

        private fun dfs(node: IntTreeNode?): Pair<Int, Int> {
            if (node == null) return 0 to 0
            val left = dfs(node.left)
            val right = dfs(node.right)
            if (node.left != null && node.right != null) {
                //minimum between left and right path (the leftmost and rightmost)
                amount += min(left.first, right.second) + 1
            }
            val leftMostLength = if (node.left != null) left.first + 1 else 0
            val rightMostLength = if (node.right != null) right.second + 1 else 0
            return leftMostLength to rightMostLength
        }
    }
}


private class Task35_5Test {
    private val task = Task35_5()

    @Test
    fun test1() {
        val tree = IntTreeNode(0).apply {
            left = IntTreeNode(1).apply {
                right = IntTreeNode(3).apply {
                    left = IntTreeNode(6)
                    right = IntTreeNode(7)
                }
            }
            right = IntTreeNode(2).apply {
                left = IntTreeNode(4).apply {
                    left = IntTreeNode(8)
                }
                right = IntTreeNode(5).apply {
                    right = IntTreeNode(9)
                }
            }
        }
        Assertions.assertEquals(4, task.countTriangles(tree))
    }

    @Test
    fun test2() {
        val tree = IntTreeNode(0).apply {
            left = IntTreeNode(1).apply {
                left = IntTreeNode(2)
                right = IntTreeNode(3)
            }
            right = IntTreeNode(4).apply {
                right = IntTreeNode(5)
            }
        }
        Assertions.assertEquals(3, task.countTriangles(tree))
    }

    @Test
    fun test3() {
        val tree = IntTreeNode(1)
        Assertions.assertEquals(0, task.countTriangles(tree))
    }

    @Test
    fun test4() {
        Assertions.assertEquals(0, task.countTriangles(null))
    }

    @Test
    fun test5() {
        val tree = IntTreeNode(1).apply {
            left = IntTreeNode(2).apply {
                left = IntTreeNode(3)
            }
        }
        Assertions.assertEquals(0, task.countTriangles(tree))
    }

    @Test
    fun test6() {
        val tree = IntTreeNode(1).apply {
            right = IntTreeNode(2).apply {
                right = IntTreeNode(3)
            }
        }
        Assertions.assertEquals(0, task.countTriangles(tree))
    }
}