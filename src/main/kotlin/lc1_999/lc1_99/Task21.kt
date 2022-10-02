package lc1_999.lc1_99

import common.IntSinglyNode
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import toIntSinglyNode
import common.equals

//From a learning section, Solved
//https://leetcode.com/problems/merge-two-sorted-lists/
class Task21 {
    fun mergeTwoLists(list1: IntSinglyNode?, list2: IntSinglyNode?): IntSinglyNode? {
        if (list1 == null && list2 == null) return null
        if (list1 == null) return list2
        if (list2 == null) return list1
        val head = if (list1.`val` <= list2.`val`) list1 else list2
        var pNode: IntSinglyNode? = head
        var p1 = head.next
        var p2: IntSinglyNode? = if (head == list1) list2 else list1
        while (pNode != null) {
            if (p1 == null && p2 == null) break
            if (p2 == null || p1 != null && p1.`val` <= p2.`val`) {
                pNode.next = p1
                p1 = p1?.next
            } else {
                pNode.next = p2
                p2 = p2.next
            }
            pNode = pNode.next
        }
        return head
    }
}

private class Task21Test {
    private val task = Task21()

    @Test
    fun mergeTwoLists() {
        mergeTwoLists(intArrayOf(1, 2, 4), intArrayOf(1, 3, 4), intArrayOf(1, 1, 2, 3, 4, 4))
        mergeTwoLists(intArrayOf(), intArrayOf(), intArrayOf())
        mergeTwoLists(intArrayOf(), intArrayOf(0), intArrayOf(0))
        mergeTwoLists(intArrayOf(2), intArrayOf(1, 3, 4), intArrayOf(1, 2, 3, 4))
        mergeTwoLists(intArrayOf(1, 3, 4), intArrayOf(2), intArrayOf(1, 2, 3, 4))
        mergeTwoLists(intArrayOf(1, 1, 1), intArrayOf(1, 2), intArrayOf(1, 1, 1, 1, 2))
    }

    private fun mergeTwoLists(actualInp1: IntArray, actualInp2: IntArray, expectedInp: IntArray) {
        val actual = task.mergeTwoLists(actualInp1.toIntSinglyNode(), actualInp2.toIntSinglyNode())
        actual?.print() ?: println("[]")
        val expected = expectedInp.toIntSinglyNode()
        Assertions.assertEquals(true, actual.equals(expected))
    }
}