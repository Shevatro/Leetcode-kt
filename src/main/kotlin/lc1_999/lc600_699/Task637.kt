package lc1_999.lc600_699

import common.IntTreeNode
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

//Similar to Beyond Cracking The Coding Interview, Solved
//https://leetcode.com/problems/average-of-levels-in-binary-tree/description/
class Task637 {
    fun averageOfLevels(root: IntTreeNode?): DoubleArray {
        if (root == null) return doubleArrayOf()
        var curLevel = 0
        var sum = 0L
        var amount = 0
        val result = mutableListOf<Double>()
        val queue = ArrayDeque<Data>()
        queue.addLast(Data(root, 0))
        while (queue.isNotEmpty()) {
            val item = queue.removeFirst()
            if (curLevel == item.level) {
                sum += item.node.`val`
                amount++
            } else {
                result.add(sum * 1.0 / amount)
                sum = item.node.`val`.toLong()
                amount = 1
                curLevel++
            }
            if (item.node.left != null) {
                queue.addLast(Data(requireNotNull(item.node.left), item.level + 1))
            }
            if (item.node.right != null) {
                queue.addLast(Data(requireNotNull(item.node.right), item.level + 1))
            }
        }
        result.add(sum * 1.0 / amount)
        return result.toDoubleArray()
    }

    private data class Data(
        val node: IntTreeNode,
        val level: Int
    )
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