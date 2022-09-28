package lc1_999.lc700_799

import common.IntSinglyNode
import common.equals
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import toIntSinglyNode

//From a learning section, Solved
//https://leetcode.com/problems/design-linked-list/

class Task707 {
    private val head = IntSinglyNode(0)
    private var size = 0

    private fun getNode(index: Int): IntSinglyNode? {
        var node: IntSinglyNode? = head
        for (i in 0..index) {
            node = node?.next
            if (node == null) return null
        }
        return node
    }

    fun get(index: Int): Int {
        return getNode(index)?.`val` ?: -1
    }

    fun addAtHead(`val`: Int) {
        addAtIndex(0, `val`)
    }

    fun addAtTail(`val`: Int) {
        addAtIndex(size, `val`)
    }

    fun addAtIndex(index: Int, `val`: Int) {
        val prev = getNode(index - 1) ?: return
        val new = IntSinglyNode(`val`).apply { next = prev.next }
        prev.next = new
        size++
    }

    fun deleteAtIndex(index: Int) {
        val prev = getNode(index - 1) ?: return
        val cur = prev.next ?: return
        prev.next = cur.next
        size--
    }

    fun getHead() = head.next
}

private class Task707Test {

    @Test
    fun test1() {
        Task707().apply {
            addAtHead(1)
            compare(intArrayOf(1), getHead())
            addAtTail(3)
            compare(intArrayOf(1, 3), getHead())
            addAtIndex(1, 2)
            compare(intArrayOf(1, 2, 3), getHead())
            compareGet(2, get(1))
            deleteAtIndex(1)
            compare(intArrayOf(1, 3), getHead())
            compareGet(3, get(1))
        }
    }

    @Test
    fun test2() {
        Task707().apply {
            addAtHead(7)
            compare(intArrayOf(7), getHead())
            addAtHead(2)
            compare(intArrayOf(2, 7), getHead())
            addAtHead(1)
            compare(intArrayOf(1, 2, 7), getHead())
            addAtIndex(3, 0)
            compare(intArrayOf(1, 2, 7, 0), getHead())
            deleteAtIndex(2)
            compare(intArrayOf(1, 2, 0), getHead())
            addAtHead(6)
            compare(intArrayOf(6, 1, 2, 0), getHead())
            addAtTail(4)
            compare(intArrayOf(6, 1, 2, 0, 4), getHead())
            compareGet(4, get(4))
            addAtHead(4)
            compare(intArrayOf(4, 6, 1, 2, 0, 4), getHead())
            addAtIndex(5, 0)
            compare(intArrayOf(4, 6, 1, 2, 0, 0, 4), getHead())
            addAtHead(6)
            compare(intArrayOf(6, 4, 6, 1, 2, 0, 0, 4), getHead())
        }
    }

    @Test
    fun test3() {
        Task707().apply {
            addAtTail(1)
            compare(intArrayOf(1), getHead())
            compareGet(1, get(0))
        }
    }

    @Test
    fun test4() {
        Task707().apply {
            addAtHead(2)
            compare(intArrayOf(2), getHead())
            deleteAtIndex(1)
            compare(intArrayOf(2), getHead())
            addAtHead(2)
            compare(intArrayOf(2, 2), getHead())
            addAtHead(7)
            compare(intArrayOf(7, 2, 2), getHead())
            addAtHead(3)
            compare(intArrayOf(3, 7, 2, 2), getHead())
            addAtHead(2)
            compare(intArrayOf(2, 3, 7, 2, 2), getHead())
            addAtHead(5)
            compare(intArrayOf(5, 2, 3, 7, 2, 2), getHead())
            addAtTail(5)
            compare(intArrayOf(5, 2, 3, 7, 2, 2, 5), getHead())
            compareGet(2, get(5))
            deleteAtIndex(6)
            compare(intArrayOf(5, 2, 3, 7, 2, 2), getHead())
            deleteAtIndex(4)
            compare(intArrayOf(5, 2, 3, 7, 2), getHead())
        }
    }

    private fun compare(expected: IntArray, actual: IntSinglyNode?) {
        actual?.print()
        Assertions.assertEquals(true, actual.equals(expected.toIntSinglyNode()))
    }

    private fun compareGet(expected: Int, actual: Int) {
        println(actual)
        Assertions.assertEquals(expected, actual)
    }
}

