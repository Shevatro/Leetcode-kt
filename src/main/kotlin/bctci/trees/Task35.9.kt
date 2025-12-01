package bctci.trees

import common.IntTreeNode
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Task35_9 {
    fun getMostProlificLevel(root: IntTreeNode?): Int {
        if (root == null) return -1
        var maxProlificness = -1.0
        var maxLevel = -1
        var curLevel = -1
        val queue = ArrayDeque<IntTreeNode>()
        queue.addLast(root)
        while (queue.isNotEmpty()) {
            val nodesAmount = queue.size
            curLevel++
            var childrenAmount = 0
            //explore each level
            repeat(nodesAmount) {
                val item = queue.removeFirst()
                if (item.left != null) {
                    childrenAmount++
                    queue.addLast(requireNotNull(item.left))
                }
                if (item.right != null) {
                    childrenAmount++
                    queue.addLast(requireNotNull(item.right))
                }
            }
            val prolificness = childrenAmount.toDouble() / nodesAmount
            if (prolificness > maxProlificness) {
                maxProlificness = prolificness
                maxLevel = curLevel
            }
        }
        return maxLevel
    }
}

private class Task35_9Test {
    private val task = Task35_9()

    @Test
    fun test1() {
        val tree = IntTreeNode(0).apply {
            left = IntTreeNode(0).apply {
                left = IntTreeNode(0).apply {
                    left = IntTreeNode(0).apply {
                        left = IntTreeNode(0)
                        right = IntTreeNode(0)
                    }
                    right = IntTreeNode(0).apply {
                        right = IntTreeNode(0)
                    }
                }
                right = IntTreeNode(0)
            }
        }
        Assertions.assertEquals(1, task.getMostProlificLevel(tree))
    }

    @Test
    fun test2() {
        Assertions.assertEquals(-1, task.getMostProlificLevel(null))
    }

    @Test
    fun test3() {
        val tree = IntTreeNode(1)
        Assertions.assertEquals(0, task.getMostProlificLevel(tree))
    }

    @Test
    fun test4() {
        val tree = IntTreeNode(5).apply {
            left = IntTreeNode(2).apply {
                right = IntTreeNode(6)
            }
            right = IntTreeNode(9).apply {
                left = IntTreeNode(9).apply {
                    right = IntTreeNode(1)
                }
                right = IntTreeNode(8)
            }
        }
        Assertions.assertEquals(0, task.getMostProlificLevel(tree))
    }

    @Test
    fun test5() {
        val tree = IntTreeNode(1).apply {
            left = IntTreeNode(2).apply {
                left = IntTreeNode(4)
                right = IntTreeNode(5)
            }
            right = IntTreeNode(3).apply {
                left = IntTreeNode(6)
                right = IntTreeNode(7)
            }
        }
        Assertions.assertEquals(0, task.getMostProlificLevel(tree))
    }

    @Test
    fun test6() {
        val tree = IntTreeNode(1).apply {
            left = IntTreeNode(2).apply {
                left = IntTreeNode(4).apply {
                    left = IntTreeNode(8)
                    right = IntTreeNode(9)
                }
                right = IntTreeNode(5)
            }
            right = IntTreeNode(3)
        }
        Assertions.assertEquals(0, task.getMostProlificLevel(tree))
    }

    @Test
    fun test7() {
        val tree = IntTreeNode(1).apply {
            left = IntTreeNode(2).apply {
                left = IntTreeNode(4).apply {
                    left = IntTreeNode(8)
                    right = IntTreeNode(9)
                }
            }
        }
        Assertions.assertEquals(2, task.getMostProlificLevel(tree))
    }
}