package lc1_999.lc600_699

import common.IntTreeNode
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

//Similar to Beyond Cracking The Coding Interview, Solved
//https://leetcode.com/problems/average-of-levels-in-binary-tree/description/
class Task637 {
    fun averageOfLevels(root: IntTreeNode?): DoubleArray {
        if (root == null) return doubleArrayOf()
        val result = mutableListOf<Double>()
        val queue = ArrayDeque<IntTreeNode>()
        queue.addLast(root)
        while (queue.isNotEmpty()) {
            val nodesAmount = queue.size
            var sum = 0L
            //explore each level
            repeat(nodesAmount) {
                val item = queue.removeFirst()
                sum += item.`val`
                if (item.left != null) {
                    queue.addLast(requireNotNull(item.left))
                }
                if (item.right != null) {
                    queue.addLast(requireNotNull(item.right))
                }
            }
            result.add(sum.toDouble() / nodesAmount)
        }
        return result.toDoubleArray()
    }
}

private class Task637Test {
    private val task = Task637()

    @Test
    fun test1() {
        val tree = IntTreeNode(3).apply {
            left = IntTreeNode(9)
            right = IntTreeNode(20).apply {
                left = IntTreeNode(15)
                right = IntTreeNode(7)
            }
        }
        val expected = doubleArrayOf(3.0, 14.5, 11.0)
        Assertions.assertArrayEquals(expected, task.averageOfLevels(tree))
    }

    @Test
    fun test2() {
        val tree = IntTreeNode(3).apply {
            left = IntTreeNode(9).apply {
                left = IntTreeNode(15)
                right = IntTreeNode(7)
            }
            right = IntTreeNode(20)
        }
        val expected = doubleArrayOf(3.0, 14.5, 11.0)
        Assertions.assertArrayEquals(expected, task.averageOfLevels(tree))
    }

    @Test
    fun test3() {
        val tree = IntTreeNode(3).apply {
            left = IntTreeNode(1).apply {
                left = IntTreeNode(0)
                right = IntTreeNode(2)
            }
            right = IntTreeNode(5).apply {
                left = IntTreeNode(4)
                right = IntTreeNode(6)
            }
        }
        val expected = doubleArrayOf(3.0, 3.0, 3.0)
        Assertions.assertArrayEquals(expected, task.averageOfLevels(tree))
    }

    @Test
    fun test4() {
        val tree = IntTreeNode(0)
        val expected = doubleArrayOf(0.0)
        Assertions.assertArrayEquals(expected, task.averageOfLevels(tree))
    }

    @Test
    fun test5() {
        val tree = IntTreeNode(2147483647).apply {
            left = IntTreeNode(2147483647)
            right = IntTreeNode(2147483647)
        }
        val expected = doubleArrayOf(2147483647.0, 2147483647.0)
        Assertions.assertArrayEquals(expected, task.averageOfLevels(tree))
    }
}