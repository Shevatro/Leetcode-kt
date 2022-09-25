package lc1_999.lc1_99

import common.IntSinglyNode
import common.equals
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import toIntSinglyNode

//From a learning section, Solved
//https://leetcode.com/problems/remove-nth-node-from-end-of-list/
class Task19 {
    fun removeNthFromEnd(head: IntSinglyNode?, n: Int): IntSinglyNode? {
        val newHead = IntSinglyNode(0).apply { next = head }
        var size = 0
        var fastPointer: IntSinglyNode? = newHead
        var slowPointer: IntSinglyNode? = newHead
        while (fastPointer != null) {
            if (size > n) {
                slowPointer = slowPointer?.next
            }
            size++
            fastPointer = fastPointer.next
        }
        slowPointer?.next=slowPointer?.next?.next
        return newHead.next
    }
}

private class Task19Test {
    private val task = Task19()

    @Test
    fun removeNthFromEnd() {
        removeNthFromEnd(intArrayOf(1, 2, 3, 4, 5).toIntSinglyNode(), 2, intArrayOf(1, 2, 3, 5).toIntSinglyNode())
        removeNthFromEnd(intArrayOf(1).toIntSinglyNode(), 1, intArrayOf().toIntSinglyNode())
        removeNthFromEnd(intArrayOf(1, 2).toIntSinglyNode(), 1, intArrayOf(1).toIntSinglyNode())
        removeNthFromEnd(intArrayOf(1, 2, 3).toIntSinglyNode(), 1, intArrayOf(1, 2).toIntSinglyNode())
        removeNthFromEnd(intArrayOf(1, 2).toIntSinglyNode(), 2, intArrayOf(2).toIntSinglyNode())
    }

    private fun removeNthFromEnd(actualInp1: IntSinglyNode?, actualInp2: Int, expected: IntSinglyNode?) {
        val actual = task.removeNthFromEnd(actualInp1, actualInp2)
        actual?.print() ?: println("[]")
        Assertions.assertEquals(true, actual.equals(expected))
    }
}