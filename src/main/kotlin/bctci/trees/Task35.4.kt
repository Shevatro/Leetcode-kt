package bctci.trees

import common.IntTreeNode
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.math.max

class Task35_4 {
    fun getMaxNodesStacked(root: IntTreeNode?): Int {
        return Solution().getMaxNodesStacked(root)
    }

    private class Solution() {
        //when h= 500, max of lAmount, and rAmount are 250
        private val arr = IntArray(250)
        private var max = 0
        fun getMaxNodesStacked(root: IntTreeNode?): Int {
            dfs(root, 0, 0)
            return max
        }

        private fun dfs(node: IntTreeNode?, lAmount: Int, rAmount: Int) {
            if (node == null) return
            if (lAmount == rAmount) {
                arr[lAmount]++
                max = max(arr[lAmount], max)
            }
            dfs(node.left, lAmount + 1, rAmount)
            dfs(node.right, lAmount, rAmount + 1)
        }
    }
}


private class Task35_4Test {
    private val task = Task35_4()

    @Test
    fun test1() {
        val tree = IntTreeNode(1).apply {
            left = IntTreeNode(2).apply {
                left = IntTreeNode(4).apply {
                    right = IntTreeNode(7)
                }
                right = IntTreeNode(5)
            }
            right = IntTreeNode(3).apply {
                left = IntTreeNode(6).apply {
                    left = IntTreeNode(8)
                    right = IntTreeNode(9)
                }
            }
        }
        Assertions.assertEquals(2, task.getMaxNodesStacked(tree))
    }

    @Test
    fun test2() {
        val tree = IntTreeNode(1)
        Assertions.assertEquals(1, task.getMaxNodesStacked(tree))
    }

    @Test
    fun test3() {
        val tree = IntTreeNode(1).apply {
            left = IntTreeNode(2)
            right = IntTreeNode(3)
        }
        Assertions.assertEquals(1, task.getMaxNodesStacked(tree))
    }

    @Test
    fun test4() {
        val tree = IntTreeNode(1).apply {
            left = IntTreeNode(2).apply {
                left = IntTreeNode(4).apply {
                    left = IntTreeNode(8)
                    right = IntTreeNode(7).apply {
                        right = IntTreeNode(16)
                    }
                }
                right = IntTreeNode(5).apply {
                    left = IntTreeNode(10).apply {
                        right = IntTreeNode(17)
                    }
                    right = IntTreeNode(11).apply {
                        left = IntTreeNode(18)
                    }
                }
            }
            right = IntTreeNode(3).apply {
                left = IntTreeNode(6).apply {
                    left = IntTreeNode(12)
                    right = IntTreeNode(13)
                }
                right = IntTreeNode(7).apply {
                    left = IntTreeNode(14).apply {
                        left = IntTreeNode(19)
                    }
                    right = IntTreeNode(15).apply {
                        left = IntTreeNode(20)
                    }
                }
            }
        }
        Assertions.assertEquals(4, task.getMaxNodesStacked(tree))
    }
}