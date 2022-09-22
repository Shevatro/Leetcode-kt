package lc1_999.lc100_199

import common.IntSinglyNode
import common.addAtTail
import common.get
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import toIntSinglyNode

//From a learning section, Solved
//https://leetcode.com/problems/linked-list-cycle/

class Task141 {
    fun hasCycle(head: IntSinglyNode?): Boolean {
        if (head?.next == null) return false
        var slowNode = head
        var fastNode = head
        while (fastNode != null) {
            slowNode = slowNode?.next
            fastNode = fastNode.next?.next
            if (slowNode == fastNode) return true
        }
        return false
    }
}

private class Task141Test {
    private val task = Task141()

    @Test
    fun hasCycle() {
        val input1 = intArrayOf(3, 2, 0, -4).toIntSinglyNode()?.apply { get(1)?.let { addAtTail(it) } }
        hasCycle(input1, true)
        val input2 = intArrayOf(1, 2).toIntSinglyNode()?.apply { get(0)?.let { addAtTail(it) } }
        hasCycle(input2, true)
        val input3 = intArrayOf(1, 2, 3).toIntSinglyNode()?.apply { get(2)?.let { addAtTail(it) } }
        hasCycle(input3, true)
        hasCycle(intArrayOf(1).toIntSinglyNode(), false)
        hasCycle(intArrayOf(1).toIntSinglyNode()?.apply { get(0)?.let { addAtTail(it) } }, true)
    }

    private fun hasCycle(actualInp: IntSinglyNode?, expected: Boolean) {
        val actual = task.hasCycle(actualInp)
        println(actual)
        Assertions.assertEquals(expected, actual)
    }
}