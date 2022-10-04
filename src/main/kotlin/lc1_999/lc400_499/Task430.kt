package lc1_999.lc400_499

import common.IntTriplyNode
import common.simpleEquals
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import toSimpleIntTriplyNode

//From a learning section, Solved
//https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/
class Task430 {
    fun flatten(root: IntTriplyNode?): IntTriplyNode? {
        var node = root
        while (node != null) {
            if (node.child != null) {
                val next = node.next
                node.next = node.child
                node.child?.prev = node
                val tailChild = findTail(requireNotNull(node.child))
                tailChild?.next = next
                next?.prev = tailChild
                node.child = null
            }
            node = node.next
        }
        return root
    }

    private fun findTail(head: IntTriplyNode): IntTriplyNode? {
        var node: IntTriplyNode? = head
        while (node?.next != null) {
            node = node.next
        }
        return node
    }

}

private class Task430Test {
    private val task = Task430()

    @Test
    fun flatten() {
        flatten(createTest1(), intArrayOf(1, 2, 3, 7, 8, 11, 12, 9, 10, 4, 5, 6).toSimpleIntTriplyNode())
        flatten(createTest2(), intArrayOf(1, 3, 2).toSimpleIntTriplyNode())
        flatten(null, null)
    }

    private fun flatten(actualInp: IntTriplyNode?, expected: IntTriplyNode?) {
        val actual = task.flatten(actualInp)
        actual?.printSimple() ?: println("[]")
        Assertions.assertEquals(true, expected.simpleEquals(actual))
    }

    private fun createTest1(): IntTriplyNode? {
        val nodes = intArrayOf(1, 2, 3, 4, 5, 6).toSimpleIntTriplyNode()
        val node3Items = intArrayOf(7, 8, 9, 10).toSimpleIntTriplyNode()
        nodes?.next?.next?.child = node3Items
        val node8Items = intArrayOf(11, 12).toSimpleIntTriplyNode()
        node3Items?.next?.child = node8Items
        return nodes
    }

    private fun createTest2(): IntTriplyNode? {
        val nodes = intArrayOf(1, 2).toSimpleIntTriplyNode()
        nodes?.child = IntTriplyNode(3)
        return nodes
    }
}