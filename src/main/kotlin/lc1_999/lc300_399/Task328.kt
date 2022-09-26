package lc1_999.lc300_399

import common.IntSinglyNode
import common.equals
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import toIntSinglyNode

//From a learning section, Solved
//https://leetcode.com/problems/odd-even-linked-list/
class Task328 {
    fun oddEvenList(head: IntSinglyNode?): IntSinglyNode? {
        val oddHead = IntSinglyNode(-1)
        val evenHead = IntSinglyNode(-1)
        var oddNode: IntSinglyNode? = oddHead
        var evenNode: IntSinglyNode? = evenHead
        var node = head
        var index = 0
        while (node != null) {
            if (index % 2 == 0) {
                oddNode?.next = node
                oddNode = node
            } else {
                evenNode?.next = node
                evenNode = node
            }
            index++
            node = node.next
        }
        oddNode?.next = evenHead.next
        evenNode?.next = null
        return oddHead.next
    }

    fun oddEvenListAlternative(head: IntSinglyNode?): IntSinglyNode? {
        if (head?.next?.next == null) return head
        val tailAndSize = getTailAndSize(head)
        var tail = tailAndSize.first
        val size = tailAndSize.second
        var prev = head
        for (i in 0 until size / 2) {
            val node = prev?.next
            prev?.next = prev?.next?.next
            prev = prev?.next
            tail?.next = node
//            tail?.next = node?.apply { next = null }
            tail = tail?.next
        }
        tail?.next = null //this or uncomment tail?.next = node?.apply { next = null }
        return head
    }

    private fun getTailAndSize(head: IntSinglyNode?): Pair<IntSinglyNode?, Int> {
        var node = head
        var amount = 0
        while (node?.next != null) {
            amount++
            node = node.next
        }
        return node to amount + 1
    }
}

private class Task328Test {
    private val task = Task328()

    @Test
    fun oddEvenList() {
        oddEvenList(intArrayOf(1, 2, 3, 4, 5).toIntSinglyNode(), intArrayOf(1, 3, 5, 2, 4).toIntSinglyNode())
        oddEvenList(intArrayOf(1, 2, 3, 4).toIntSinglyNode(), intArrayOf(1, 3, 2, 4).toIntSinglyNode())
        oddEvenList(
            intArrayOf(2, 1, 3, 5, 6, 4, 7).toIntSinglyNode(), intArrayOf(2, 3, 6, 7, 1, 5, 4).toIntSinglyNode()
        )
        oddEvenList(intArrayOf().toIntSinglyNode(), intArrayOf().toIntSinglyNode())
        oddEvenList(intArrayOf(1).toIntSinglyNode(), intArrayOf(1).toIntSinglyNode())
        oddEvenList(intArrayOf(1, 2).toIntSinglyNode(), intArrayOf(1, 2).toIntSinglyNode())
    }

    private fun oddEvenList(actualInp: IntSinglyNode?, expected: IntSinglyNode?) {
        val actual = task.oddEvenList(actualInp)
        actual?.print() ?: println("[]")
        Assertions.assertEquals(true, actual.equals(expected))
    }
}