package lc1_999.lc200_299

import common.IntSinglyNode
import common.equals
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import toIntSinglyNode

//From a learning section, Solved
//https://leetcode.com/problems/reverse-linked-list/
class Task206 {
    fun reverseList(head: IntSinglyNode?): IntSinglyNode? {
        var node = head
        var newHead: IntSinglyNode? = null
        while (node != null) {
            val curNode = node
            node = node.next
            newHead = curNode.apply { next = newHead }
        }
        return newHead
    }
}

private class Task206Test {
    private val task = Task206()

    @Test
    fun reverseList() {
        reverseList(intArrayOf(1, 2, 3, 4, 5).toIntSinglyNode(), intArrayOf(5, 4, 3, 2, 1).toIntSinglyNode())
        reverseList(intArrayOf(1, 2).toIntSinglyNode(), intArrayOf(2, 1).toIntSinglyNode())
        reverseList(intArrayOf().toIntSinglyNode(), intArrayOf().toIntSinglyNode())
    }

    private fun reverseList(actualInp: IntSinglyNode?, expected: IntSinglyNode?) {
        val actual = task.reverseList(actualInp)
        actual?.print() ?: println("[]")
        Assertions.assertEquals(true, actual.equals(expected))
    }
}