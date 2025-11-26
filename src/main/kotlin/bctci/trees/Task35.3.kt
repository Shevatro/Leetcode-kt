package bctci.trees

import common.IntTreeNode
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.math.max

//Tricky, repeat
class Task35_3 {
    fun getAlignedPathSize(root: IntTreeNode?): Int {
        return Solution().getAlignedPathSize(root)
    }

    private class Solution() {
        private var maxLength = 0
        fun getAlignedPathSize(root: IntTreeNode?): Int {
            dfs(root, 0)
            return maxLength
        }

        private fun dfs(node: IntTreeNode?, level: Int): Int {
            if (node == null) return 0
            val leftLength = dfs(node.left, level + 1)
            val rightLength = dfs(node.right, level + 1)

            if (node.`val` != level) return 0

            val curLength = leftLength + rightLength + 1
            maxLength = max(maxLength, curLength)
            return max(leftLength, rightLength) + 1
        }
    }

}


private class Task35_3Test {
    private val task = Task35_3()

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
        Assertions.assertEquals(3, task.getAlignedPathSize(tree))
    }

    @Test
    fun test2() {
        val tree = IntTreeNode(7).apply {
            left = IntTreeNode(1).apply {
                left = IntTreeNode(20).apply {
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
        Assertions.assertEquals(3, task.getAlignedPathSize(tree))
    }

    @Test
    fun test3() {
        Assertions.assertEquals(0, task.getAlignedPathSize(null))
    }

    @Test
    fun test4() {
        val tree = IntTreeNode(0)
        Assertions.assertEquals(1, task.getAlignedPathSize(tree))
    }

    @Test
    fun test5() {
        val tree = IntTreeNode(1)
        Assertions.assertEquals(0, task.getAlignedPathSize(tree))
    }

    @Test
    fun test6() {
        val tree = IntTreeNode(0).apply {
            left = IntTreeNode(1)
            right = IntTreeNode(1)
        }
        Assertions.assertEquals(3, task.getAlignedPathSize(tree))
    }

    @Test
    fun test7() {
        val tree = IntTreeNode(5).apply {
            left = IntTreeNode(4)
            right = IntTreeNode(2)
        }
        Assertions.assertEquals(0, task.getAlignedPathSize(tree))
    }

    @Test
    fun test8() {
        val tree = IntTreeNode(0).apply {
            left = IntTreeNode(1).apply {
                left = IntTreeNode(2)
                right = IntTreeNode(2)
            }
            right = IntTreeNode(1)
        }
        Assertions.assertEquals(4, task.getAlignedPathSize(tree))
    }

    @Test
    fun test9() {
        val tree = IntTreeNode(7).apply {
            left = IntTreeNode(1).apply {
                left = IntTreeNode(2).apply {
                    left = IntTreeNode(4)
                    right = IntTreeNode(3)
                }
                right = IntTreeNode(8)
            }
            right = IntTreeNode(3).apply {
                right = IntTreeNode(20).apply {
                    left = IntTreeNode(3)
                    right = IntTreeNode(3)
                }
            }
        }
        Assertions.assertEquals(3, task.getAlignedPathSize(tree))
    }

    @Test
    fun test10() {
        val tree = IntTreeNode(7).apply {
            left = IntTreeNode(1).apply {
                left = IntTreeNode(20).apply {
                    left = IntTreeNode(4)
                    right = IntTreeNode(3)
                }
                right = IntTreeNode(8)
            }
            right = IntTreeNode(3).apply {
                right = IntTreeNode(20).apply {
                    left = IntTreeNode(3)
                    right = IntTreeNode(3)
                }
            }
        }
        Assertions.assertEquals(1, task.getAlignedPathSize(tree))
    }
}