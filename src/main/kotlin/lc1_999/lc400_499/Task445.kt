package lc1_999.lc400_499

import common.IntSinglyNode
import common.equals
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import toIntSinglyNode

//From Cracking The Coding Interview, Solved
//https://leetcode.com/problems/add-two-numbers-ii/
class Task445 {
    fun addTwoNumbers(l1: IntSinglyNode?, l2: IntSinglyNode?): IntSinglyNode? {
        val l1Reversed = reverse(l1)
        val l2Reversed = reverse(l2)
        return reverse(addNumbers(l1Reversed, l2Reversed))
    }

    private fun reverse(l: IntSinglyNode?): IntSinglyNode? {
        var head: IntSinglyNode? = null
        var node: IntSinglyNode? = l
        while (node != null) {
            val oldHead = head
            head = node
            node = node.next
            head.next = oldHead
        }
        return head
    }

    private fun addNumbers(l1: IntSinglyNode?, l2: IntSinglyNode?): IntSinglyNode? {
        var p1: IntSinglyNode? = l1
        var p2: IntSinglyNode? = l2
        var overflow = 0
        while (p1?.next != null || p2?.next != null) {
            var sum = (p1?.`val` ?: 0) + (p2?.`val` ?: 0) + overflow
            if (sum >= 10) {
                overflow = 1
                sum -= 10
            } else {
                overflow = 0
            }
            if (p1 != null) {
                p1.`val` = sum
                p1 = p1.next
            }
            if (p2 != null) {
                p2.`val` = sum
                p2 = p2.next
            }
        }
        val p1Null = p1 == null
        var sum = (p1?.`val` ?: 0) + (p2?.`val` ?: 0) + overflow
        if (sum >= 10) {
            sum -= 10
            if (p1Null) requireNotNull(p2).next = IntSinglyNode(1) else requireNotNull(p1).next = IntSinglyNode(1)
        }
        if (p1Null) p2?.`val` = sum else p1?.`val` = sum
        return if (p1Null) l2 else l1
    }
}

private class Task445Test {
    private val task = Task445()

    @Test
    fun addTwoNumbers() {
        addTwoNumbers(intArrayOf(7, 2, 4, 3), intArrayOf(5, 6, 4), intArrayOf(7, 8, 0, 7))
        addTwoNumbers(intArrayOf(2, 4, 3), intArrayOf(5, 6, 4), intArrayOf(8, 0, 7))
        addTwoNumbers(intArrayOf(0), intArrayOf(0), intArrayOf(0))

    }

    private fun addTwoNumbers(actualInp1: IntArray, actualInp2: IntArray, expectedInp: IntArray) {
        val actual = task.addTwoNumbers(actualInp1.toIntSinglyNode(), actualInp2.toIntSinglyNode())
        actual?.print() ?: println("[]")
        val expected = expectedInp.toIntSinglyNode()
        Assertions.assertEquals(true, actual.equals(expected))
    }
}