package lc1000_1999.lc1800_1899

import common.IntSinglyNode
import common.equals
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import toIntSinglyNode

//From Cracking The Coding Interview, Solved
//https://leetcode.com/problems/remove-duplicates-from-an-unsorted-linked-list/
class Task1836 {
    fun deleteDuplicatesUnsortedAlternative(head: IntSinglyNode?): IntSinglyNode? {
        val newHead = IntSinglyNode(-1).apply { next = head }
        var node: IntSinglyNode? = newHead
        while (node?.next != null) {
            if (removeDuplicates(node.next)) {
                node.next = node.next?.next
            } else {
                node = node.next
            }
        }
        return newHead.next
    }

    private fun removeDuplicates(node: IntSinglyNode?): Boolean {
        var p = node
        var hasDuplicates = false
        while (p?.next != null) {
            val next = p.next
            if (next?.`val` == node?.`val`) {
                hasDuplicates = true
                p.next = p.next?.next
            } else {
                p = p.next
            }
        }
        return hasDuplicates
    }

    fun deleteDuplicatesUnsorted(head: IntSinglyNode?): IntSinglyNode? {
        val newHead = IntSinglyNode(-1).apply { next = head }
        deleteDuplicates(newHead)
        return newHead.next
    }

    private fun getDuplicatesMark(head: IntSinglyNode?): Map<Int, Boolean> {
        val duplicatesMark = mutableMapOf<Int, Boolean>()
        var node = head
        while (node != null) {
            duplicatesMark[node.`val`] = duplicatesMark[node.`val`] != null
            node = node.next
        }
        return duplicatesMark
    }

    private fun deleteDuplicates(head: IntSinglyNode) {
        val duplicatesMark = getDuplicatesMark(head)
        var node: IntSinglyNode? = head
        while (node?.next != null) {
            val next = requireNotNull(node.next)
            if (duplicatesMark[next.`val`] == true) {
                node.next = node.next?.next
            } else {
                node = node.next
            }
        }
    }
}

private class Task1836Test {
    private val task = Task1836()

    @Test
    fun deleteDuplicatesUnsorted() {
        deleteDuplicatesUnsorted(intArrayOf(1, 2, 3, 2), intArrayOf(1, 3))
        deleteDuplicatesUnsorted(intArrayOf(2, 1, 1, 2), intArrayOf())
        deleteDuplicatesUnsorted(intArrayOf(3, 2, 2, 1, 3, 2, 4), intArrayOf(1, 4))
        deleteDuplicatesUnsorted(intArrayOf(3), intArrayOf(3))
    }

    private fun deleteDuplicatesUnsorted(actualInp: IntArray, expectedInp: IntArray) {
        val actual = task.deleteDuplicatesUnsorted(actualInp.toIntSinglyNode())
        actual?.print() ?: println("[]")
        val expected = expectedInp.toIntSinglyNode()
        Assertions.assertEquals(true, actual.equals(expected))
    }
}