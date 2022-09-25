package lc1_999.lc200_299

import common.IntSinglyNode
import common.equals
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import toIntSinglyNode

//From a learning section, Solved
//https://leetcode.com/problems/remove-linked-list-elements/
class Task203 {
    fun removeElements(head: IntSinglyNode?, `val`: Int): IntSinglyNode? {
        val newHead = IntSinglyNode(-1).apply { next = head }
        var node: IntSinglyNode? = newHead
        while (node?.next != null) {
            if (node.next?.`val` == `val`) {
                node.next = node.next?.next
            } else {
                node = node.next
            }
        }
        return newHead.next
    }
}

private class Task203Test {
    private val task = Task203()

    @Test
    fun removeElements() {
        removeElements(
            intArrayOf(1, 2, 6, 3, 4, 5, 6).toIntSinglyNode(), 6, intArrayOf(1, 2, 3, 4, 5).toIntSinglyNode()
        )
        removeElements(intArrayOf().toIntSinglyNode(), 1, intArrayOf().toIntSinglyNode())
        removeElements(intArrayOf(7, 7, 7, 7).toIntSinglyNode(), 7, intArrayOf().toIntSinglyNode())
    }

    private fun removeElements(actualInp1: IntSinglyNode?, actualInp2: Int, expected: IntSinglyNode?) {
        val actual = task.removeElements(actualInp1, actualInp2)
        actual?.print() ?: println("[]")
        Assertions.assertEquals(true, actual.equals(expected))
    }
}