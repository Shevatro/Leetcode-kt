package lc1_999.lc200_299

import common.IntTreeNode
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.math.abs

//From Beyond Cracking The Coding Interview, Solved
//https://leetcode.com/problems/closest-binary-search-tree-value/
class Task270 {
    fun closestValue(root: IntTreeNode?, target: Double): Int {
        return Solution(target).closestValue(root)
    }

    private class Solution(private val target: Double) {
        private var closestTarget = -1
        private var minDiff = Double.MAX_VALUE
        fun closestValue(root: IntTreeNode?): Int {
            var p = root
            while (p != null) {
                val diff = abs(p.`val` - target)
                if (diff < minDiff || (diff == minDiff && p.`val` < closestTarget)) {
                    minDiff = diff
                    closestTarget = p.`val`
                }
                p = if (target > p.`val`) p.right else p.left
            }
            return closestTarget
        }
    }
}

private class Task270Test {
    private val task = Task270()

    @Test
    fun test1() {
        val tree = IntTreeNode(4).apply {
            left = IntTreeNode(2).apply {
                left = IntTreeNode(1)
                right = IntTreeNode(3)
            }
            right = IntTreeNode(5)
        }
        Assertions.assertEquals(4, task.closestValue(tree, 3.714286))
    }

    @Test
    fun test2() {
        val tree = IntTreeNode(1)
        Assertions.assertEquals(1, task.closestValue(tree, 4.428571))
    }

    @Test
    fun test3() {
        val tree = IntTreeNode(8).apply {
            left = IntTreeNode(1)
        }
        Assertions.assertEquals(8, task.closestValue(tree, 6.0))
    }
}