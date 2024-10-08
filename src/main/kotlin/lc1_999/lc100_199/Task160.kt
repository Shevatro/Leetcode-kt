package lc1_999.lc100_199

import common.IntSinglyNode
import common.addAtTail
import common.get
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import toIntSinglyNode

//From a learning section, Solved but Not optimal
//https://leetcode.com/problems/intersection-of-two-linked-lists/
class Task160 {
    fun getIntersectionNode(headA: IntSinglyNode?, headB: IntSinglyNode?): IntSinglyNode? {
        var nodeA = headA
        var nodeB = headB
        while (nodeA != nodeB) {
            nodeA = if (nodeA == null) headB else nodeA.next
            nodeB = if (nodeB == null) headA else nodeB.next
        }
        return nodeA
    }
}

private class Task160Test {
    private val task = Task160()

    @Test
    fun getIntersectionNode() {
        val input1 = createInput(intArrayOf(4, 1), intArrayOf(5, 6, 1), intArrayOf(8, 4, 5))
        getIntersectionNode(input1, input1.first?.get(2))
        val input2 = createInput(intArrayOf(1, 9, 1), intArrayOf(3), intArrayOf(2, 4))
        getIntersectionNode(input2, input2.first?.get(3))
        val input3 = createInput(intArrayOf(2, 6, 4), intArrayOf(1, 5), null)
        getIntersectionNode(input3, null)
    }

    private fun createInput(
        inp1: IntArray, inp2: IntArray, commonInp: IntArray?
    ): Pair<IntSinglyNode?, IntSinglyNode?> {
        val inp1Head = inp1.toIntSinglyNode()
        val inp2Head = inp2.toIntSinglyNode()
        val inpCommonHead = commonInp?.toIntSinglyNode() ?: return inp1Head to inp2Head
        return inp1Head?.apply { addAtTail(inpCommonHead) } to inp2Head?.apply { addAtTail(inpCommonHead) }
    }

    private fun getIntersectionNode(actualInp: Pair<IntSinglyNode?, IntSinglyNode?>, expected: IntSinglyNode?) {
        val (actualInp1, actualInp2) = actualInp
        val actual = task.getIntersectionNode(actualInp1, actualInp2)
        println(actual?.`val`.toString())
        Assertions.assertEquals(expected, actual)
    }
}