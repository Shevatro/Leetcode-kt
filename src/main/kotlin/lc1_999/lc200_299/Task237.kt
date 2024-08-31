package lc1_999.lc200_299

import common.IntSinglyNode
import common.equals
import common.getByValue
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import toIntSinglyNode

//From Cracking The Coding Interview, Solved
//https://leetcode.com/problems/delete-node-in-a-linked-list/
class Task237 {
    fun deleteNode(node: IntSinglyNode?) {
        node?.`val` = requireNotNull(node?.next?.`val`)
        node?.next = node?.next?.next
    }
}

private class Task237Test {
    private val task = Task237()

    @Test
    fun deleteNode() {
        deleteNode(intArrayOf(4, 5, 1, 9).toIntSinglyNode(), 5, intArrayOf(4, 1, 9).toIntSinglyNode())
        deleteNode(intArrayOf(4, 5, 1, 9).toIntSinglyNode(), 1, intArrayOf(4, 5, 9).toIntSinglyNode())
        deleteNode(intArrayOf(4, 5, 1, 9).toIntSinglyNode(), 4, intArrayOf(5, 1, 9).toIntSinglyNode())
    }

    private fun deleteNode(actualRoot: IntSinglyNode?, actualInp: Int, expected: IntSinglyNode?) {
        val actNode = actualRoot?.getByValue(actualInp)
        task.deleteNode(actNode)
        Assertions.assertTrue(actualRoot.equals(expected))
    }
}