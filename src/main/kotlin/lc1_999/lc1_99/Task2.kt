package lc1_999.lc1_99

import common.IntSinglyNode
import common.equals
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import toIntSinglyNode

//From a learning section, Solved
//https://leetcode.com/problems/add-two-numbers/
class Task2 {
    fun addTwoNumbers(l1: IntSinglyNode?, l2: IntSinglyNode?): IntSinglyNode? {
        if (l1 == null || l2 == null) return null
        val head = IntSinglyNode(-1)
        var node: IntSinglyNode? = head
        var p1 = l1
        var p2 = l2
        var overflow = 0
        while (p1 != null || p2 != null || overflow > 0) {
            var sum = (p1?.`val` ?: 0) + (p2?.`val` ?: 0) + overflow
            if (sum >= 10) {
                sum -= 10
                overflow = 1
            } else {
                overflow = 0
            }
//            node?.next = p1?.apply { `val` = sum } ?: p2?.apply { `val` = sum } ?: IntSinglyNode(sum)
            node?.next = IntSinglyNode(sum)
            node = node?.next
            p1 = p1?.next
            p2 = p2?.next
        }
        return head.next
    }
}

private class Task2Test {
    private val task = Task2()

    @Test
    fun addTwoNumbers() {
        addTwoNumbers(intArrayOf(2, 4, 3), intArrayOf(5, 6, 4), intArrayOf(7, 0, 8))
        addTwoNumbers(intArrayOf(0), intArrayOf(0), intArrayOf(0))
        addTwoNumbers(intArrayOf(9, 9, 9, 9, 9, 9, 9), intArrayOf(9, 9, 9, 9), intArrayOf(8, 9, 9, 9, 0, 0, 0, 1))
    }

    private fun addTwoNumbers(actualInp1: IntArray, actualInp2: IntArray, expectedInp: IntArray) {
        val actual = task.addTwoNumbers(actualInp1.toIntSinglyNode(), actualInp2.toIntSinglyNode())
        actual?.print() ?: println("[]")
        val expected = expectedInp.toIntSinglyNode()
        Assertions.assertEquals(true, actual.equals(expected))
    }
}