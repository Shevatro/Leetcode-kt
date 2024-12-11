package lc1_999.lc100_199

import common.IntTreeNode
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

//Not Solved
//https://leetcode.com/problems/sum-root-to-leaf-numbers/
class Task129 {
    fun sumNumbers(root: IntTreeNode?): Int {
        return Solution(root).sumNumbers()
    }

    private class Solution(private val root: IntTreeNode?) {
        private var sum = 0
        fun sumNumbers(): Int {
            preOrder(root, 0)
            return sum
        }

        private fun preOrder(node: IntTreeNode?, number: Int) {
            if (node == null) return
            val currentNumber = number * 10 + node.`val`
            if (node.left == null && node.right == null) {
                sum += currentNumber
            }
            println(node.`val`.toString() + " " + currentNumber + " " + sum)
            preOrder(node.left, currentNumber)
            preOrder(node.right, currentNumber)
        }
    }
}

private class Task129Test {
    private val task = Task129()

    @Test
    fun pathSumTest1() {
        val root = IntTreeNode(1).apply {
            left = IntTreeNode(2)
            right = IntTreeNode(3)
        }

        val result = task.sumNumbers(root)
        Assertions.assertEquals(25, result)
    }

    @Test
    fun pathSumTest2() {
        val root = IntTreeNode(4).apply {
            left = IntTreeNode(9).apply {
                left = IntTreeNode(5)
                right = IntTreeNode(1)
            }
            right = IntTreeNode(0)
        }

        val result = task.sumNumbers(root)
        Assertions.assertEquals(1026, result)
    }
}