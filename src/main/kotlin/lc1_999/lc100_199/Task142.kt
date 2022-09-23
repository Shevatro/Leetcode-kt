package lc1_999.lc100_199

import common.IntSinglyNode
import common.addAtTail
import common.get
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import toIntSinglyNode

//From a learning section, Solved but Not optimal
//https://leetcode.com/problems/linked-list-cycle-ii/

class Task142 {
    fun detectCycle(head: IntSinglyNode?): IntSinglyNode? {
        if (head?.next == null) return null
        val intersectionNode = getIntersectionNode(head) ?: return null
        return getFirstNodeInCycle(head, intersectionNode)
    }

    private fun getIntersectionNode(head: IntSinglyNode?): IntSinglyNode? {
        var slowPointer = head
        var fastPointer = head
        while (fastPointer != null) {
            slowPointer = slowPointer?.next
            fastPointer = fastPointer.next?.next
            if (slowPointer == fastPointer) return fastPointer
        }
        return null
    }

    private fun getFirstNodeInCycle(head: IntSinglyNode, intersectionNode: IntSinglyNode): IntSinglyNode {
        var node1 = head
        var node2 = intersectionNode
        while (node1 != node2) {
            node1 = requireNotNull(node1.next)
            node2 = requireNotNull(node2.next)
        }
        return node1
    }

    fun detectCycleAlternative(head: IntSinglyNode?): IntSinglyNode? {
        val visited = mutableSetOf<IntSinglyNode>()
        var node = head
        while (node != null) {
            if (visited.contains(node)) return node
            visited.add(node)
            node = node.next
        }
        return null
    }
}

private class Task142Test {
    private val task = Task142()

    @Test
    fun detectCycle() {
        val input1 = intArrayOf(3, 2, 0, -4).toIntSinglyNode()?.apply { get(1)?.let { addAtTail(it) } }
        detectCycle(input1, IntSinglyNode(2))
        val input2 = intArrayOf(1, 2).toIntSinglyNode()?.apply { get(0)?.let { addAtTail(it) } }
        detectCycle(input2, IntSinglyNode(1))
        val input3 = intArrayOf(1, 2, 3).toIntSinglyNode()?.apply { get(2)?.let { addAtTail(it) } }
        detectCycle(input3, IntSinglyNode(3))
        val input4 = intArrayOf(
            -21, 10, 17, 8, 4, 26, 5, 35, 33, -7, -16, 27, -12, 6, 29, -12, 5, 9, 20, 14, 14, 2, 13, -24, 21, 23, -21, 5
        ).toIntSinglyNode()?.apply { get(24)?.let { addAtTail(it) } }
        detectCycle(input4, IntSinglyNode(21))
        detectCycle(intArrayOf(1).toIntSinglyNode(), null)
        detectCycle(intArrayOf(1).toIntSinglyNode()?.apply { get(0)?.let { addAtTail(it) } }, IntSinglyNode(1))
    }

    private fun detectCycle(actualInp: IntSinglyNode?, expected: IntSinglyNode?) {
        val actual = task.detectCycle(actualInp)
        println(actual?.`val`)
        Assertions.assertEquals(expected?.`val`, actual?.`val`)
    }
}