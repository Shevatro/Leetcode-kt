package bctci.trees

import common.IntTreeNode
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Task35_10 {
    fun applyZigZagOrder(root: IntTreeNode?): List<Int> {
        if (root == null) return emptyList()
        var curLevel = -1
        val result = mutableListOf<Int>()
        val queue = ArrayDeque<IntTreeNode>()
        queue.addLast(root)
        while (queue.isNotEmpty()) {
            curLevel++
            val temp = ArrayDeque<Int>()
            repeat(queue.size) {
                val item = queue.removeFirst()
                temp.addLast(item.`val`)
                if (item.left != null) queue.addLast(requireNotNull(item.left))
                if (item.right != null) queue.addLast(requireNotNull(item.right))
            }
            val isLeftToRight = curLevel % 2 == 0
            while (temp.isNotEmpty()) {
                result.add(if (isLeftToRight) temp.removeFirst() else temp.removeLast())
            }
        }
        return result
    }
}

private class Task35_10Test {
    private val task = Task35_10()

    @Test
    fun test1() {
        val tree = IntTreeNode(1).apply {
            left = IntTreeNode(2).apply {
                left = IntTreeNode(4)
                right = IntTreeNode(5)
            }
            right = IntTreeNode(3).apply {
                left = IntTreeNode(6)
            }
        }
        val expected = listOf(1, 3, 2, 4, 5, 6)
        Assertions.assertEquals(expected, task.applyZigZagOrder(tree))
    }

    @Test
    fun test2() {
        val tree = IntTreeNode(5).apply {
            left = IntTreeNode(9).apply {
                left = IntTreeNode(8)
                right = IntTreeNode(2).apply {
                    right = IntTreeNode(1)
                }
            }
            right = IntTreeNode(6).apply {
                left = IntTreeNode(2).apply {
                    right = IntTreeNode(3)
                }
            }
        }
        val expected = listOf(5, 6, 9, 8, 2, 2, 3, 1)
        Assertions.assertEquals(expected, task.applyZigZagOrder(tree))
    }

    @Test
    fun test3() {
        Assertions.assertEquals(emptyList<Int>(), task.applyZigZagOrder(null))
    }

    @Test
    fun test4() {
        val tree = IntTreeNode(1)
        Assertions.assertEquals(listOf(1), task.applyZigZagOrder(tree))
    }

    @Test
    fun test5() {
        val tree = IntTreeNode(1).apply {
            left = IntTreeNode(2).apply {
                left = IntTreeNode(3).apply {
                    left = IntTreeNode(4)
                }
            }
        }
        val expected = listOf(1, 2, 3, 4)
        Assertions.assertEquals(expected, task.applyZigZagOrder(tree))
    }

    @Test
    fun test6() {
        val tree = IntTreeNode(1).apply {
            left = IntTreeNode(2).apply {
                left = IntTreeNode(4).apply {
                    left = IntTreeNode(8)
                    right = IntTreeNode(9)
                }
                right = IntTreeNode(5).apply {
                    left = IntTreeNode(10)
                    right = IntTreeNode(11)
                }
            }
            right = IntTreeNode(3).apply {
                left = IntTreeNode(6).apply {
                    left = IntTreeNode(12)
                }
                right = IntTreeNode(7).apply {
                    left = IntTreeNode(14)
                    right = IntTreeNode(15)
                }
            }
        }
        val expected = listOf(1, 3, 2, 4, 5, 6, 7, 15, 14, 12, 11, 10, 9, 8)
        Assertions.assertEquals(expected, task.applyZigZagOrder(tree))
    }
}