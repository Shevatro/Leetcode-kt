package lc1_999.lc1_99

import common.IntSinglyNode
import common.equals
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import toIntSinglyNode

//From Cracking The Coding Interview, Solved
//https://leetcode.com/problems/partition-list/
class Task86 {
    fun partition(head: IntSinglyNode?, x: Int): IntSinglyNode? {
        val lessHead = IntSinglyNode(Int.MIN_VALUE)
        val greaterHead = IntSinglyNode(Int.MIN_VALUE)
        var lessPointer = lessHead
        var greaterPointer = greaterHead
        var node: IntSinglyNode? = head
        while (node != null) {
            if (node.`val` >= x) {
                greaterPointer.next = IntSinglyNode(node.`val`)
                greaterPointer = requireNotNull(greaterPointer.next)
            } else {
                lessPointer.next = IntSinglyNode(node.`val`)
                lessPointer = requireNotNull(lessPointer.next)
            }
            node = node.next
        }

        lessPointer.next = greaterHead.next
        return lessHead.next
    }
}

private class Task86Test {
    private val task = Task86()

    @Test
    fun partition() {
        partition(intArrayOf(1, 4, 3, 2, 5, 2).toIntSinglyNode(), 3, intArrayOf(1, 2, 2, 4, 3, 5).toIntSinglyNode())
        partition(intArrayOf(2, 1).toIntSinglyNode(), 2, intArrayOf(1, 2).toIntSinglyNode())
        partition(intArrayOf().toIntSinglyNode(), 4, intArrayOf().toIntSinglyNode())
    }

    private fun partition(actualInp: IntSinglyNode?, actualX: Int, expected: IntSinglyNode?) {
        val actual = task.partition(actualInp, actualX)
        Assertions.assertTrue(actual.equals(expected))
    }
}