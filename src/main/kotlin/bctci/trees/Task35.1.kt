package bctci.trees

import common.IntTreeNode
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.math.max

class Task35_1() {
    fun getAlignedChainSize(root: IntTreeNode?): Int {
        return Solution().getAlignedChainSize(root)
    }

    class Solution() {
        private var maxLength = 0
        fun getAlignedChainSize(root: IntTreeNode?): Int {
            goDown(root, 0, 1)
            return maxLength
        }

        private fun goDown(node: IntTreeNode?, level: Int, length: Int) {
            if (node == null) return
            val isAligned = node.`val` == level
            if (isAligned) maxLength = max(length, maxLength)
            val newLength = if (isAligned) length + 1 else 1
            goDown(node.left, level + 1, newLength)
            goDown(node.right, level + 1, newLength)
        }
    }
}


private class Task35_1Test {
    private val task = Task35_1()

    @Test
    fun test1() {
        val tree = IntTreeNode(7).apply {
            left = IntTreeNode(1).apply {
                left = IntTreeNode(2).apply {
                    left = IntTreeNode(4)
                    right = IntTreeNode(3)
                }
                right = IntTreeNode(8)
            }
            right = IntTreeNode(3).apply {
                right = IntTreeNode(2).apply {
                    left = IntTreeNode(3)
                    right = IntTreeNode(3)
                }
            }
        }
        Assertions.assertEquals(3, task.getAlignedChainSize(tree))
    }

    @Test
    fun test2() {
        val tree = IntTreeNode(0).apply {
            left = IntTreeNode(1).apply {
                left = IntTreeNode(2).apply {
                    left = IntTreeNode(3)
                }
                right = IntTreeNode(4)
            }
            right = IntTreeNode(5)
        }
        Assertions.assertEquals(4, task.getAlignedChainSize(tree))
    }

    @Test
    fun test3() {
        Assertions.assertEquals(0, task.getAlignedChainSize(null))
    }

    @Test
    fun test4() {
        val tree = IntTreeNode(0)
        Assertions.assertEquals(1, task.getAlignedChainSize(tree))
    }

    @Test
    fun test5() {
        val tree = IntTreeNode(1)
        Assertions.assertEquals(0, task.getAlignedChainSize(tree))
    }

    @Test
    fun test6() {
        val tree = IntTreeNode(0).apply {
            left = IntTreeNode(1).apply {
                left = IntTreeNode(2).apply {
                    left = IntTreeNode(4)
                }
                right = IntTreeNode(2).apply {
                    left = IntTreeNode(3)
                }
            }
        }
        Assertions.assertEquals(4, task.getAlignedChainSize(tree))
    }

    @Test
    fun test7() {
        val tree = IntTreeNode(5).apply {
            left = IntTreeNode(4).apply {
                left = IntTreeNode(3)
                right = IntTreeNode(3)
            }
            right = IntTreeNode(2)
        }
        Assertions.assertEquals(0, task.getAlignedChainSize(tree))
    }

    @Test
    fun test8() {
        val tree = IntTreeNode(0).apply {
            left = IntTreeNode(1)
            right = IntTreeNode(1)
        }
        Assertions.assertEquals(2, task.getAlignedChainSize(tree))
    }

    @Test
    fun test9() {
        val tree = IntTreeNode(0).apply {
            left = IntTreeNode(1).apply {
                left = IntTreeNode(2)
                right = IntTreeNode(5)
            }
        }
        Assertions.assertEquals(3, task.getAlignedChainSize(tree))
    }
}