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
        val size = getSize(head)
        val index = size - n
        if (index == 0) {
            return head?.next
        }
        val prevNode = get(requireNotNull(head), index - 1)
        prevNode.next = prevNode.next?.next
        return head
    }

    private fun getSize(head: IntSinglyNode?): Int {
        var node = head
        var size = 0
        while (node != null) {
            size++
            node = node.next
        }
        return size
    }

    private fun get(head: IntSinglyNode, index: Int): IntSinglyNode {
        var node = head
        for (i in 0 until index) {
            node = requireNotNull(node.next)
        }
        return node
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