package lc1_999.lc700_799

import common.IntDoublyNode
import common.equals
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import toIntDoublyNode

//From a learning section, Solved
//https://leetcode.com/problems/design-linked-list/

class Task707_2 {
    private var head: IntDoublyNode? = null
    private var tail: IntDoublyNode? = null
    private var size = 0

    private fun getNode(index: Int): IntDoublyNode? {
        if (index > size) return null
        var node: IntDoublyNode? = head
        for (i in 1..index) {
            node = node?.next
        }
        return node
    }

    fun get(index: Int): Int {
        return getNode(index)?.`val` ?: -1
    }

    fun addAtHead(`val`: Int) {
        val newHead = IntDoublyNode(`val`)
        head?.prev = newHead
        head = newHead.apply { next = head }
        if (tail == null) tail = head
        size++
    }

    fun addAtTail(`val`: Int) {
        if (head == null) {
            addAtHead(`val`)
            return
        }
        val newTail = IntDoublyNode(`val`).apply { prev = tail }
        tail?.next = newTail
        tail = newTail
        size++
    }

    fun addAtIndex(index: Int, `val`: Int) {
        if (index == 0) {
            addAtHead(`val`)
        } else if (index == size) {
            addAtTail(`val`)
        } else {
            val prevNode = getNode(index - 1)
            val newNode = IntDoublyNode(`val`).apply { prev = prevNode; next = prevNode?.next }
            prevNode?.next?.prev = newNode
            prevNode?.next = newNode
            size++
        }
    }

    fun deleteAtIndex(index: Int) {
        if (index >= size) return
        if (index == 0) {
            val nextNode = head?.next
            head = nextNode?.apply { prev = null }
            if (size == 1) tail = null
        } else if (index == size - 1) {
            val newTail = tail?.prev
            tail = newTail
            newTail?.next = null
        } else {
            val prevNode = getNode(index - 1) ?: return
            val nextNode = prevNode.next?.next
            prevNode.next = nextNode
            nextNode?.prev = prevNode
        }

        size--
    }

    fun getHead() = head
    fun getTail() = tail
}

private class Task707_2Test {

    @Test
    fun test1() {
        Task707_2().apply {
            addAtHead(1)
            compare(intArrayOf(1), getHead(), true)
            compare(intArrayOf(1), getTail())
            addAtTail(3)
            compare(intArrayOf(1, 3), getHead(), true)
            compare(intArrayOf(3), getTail())
            addAtIndex(1, 2)
            compare(intArrayOf(1, 2, 3), getHead(), true)
            compare(intArrayOf(3), getTail())
            compareGet(2, get(1))
            deleteAtIndex(1)
            compare(intArrayOf(1, 3), getHead(), true)
            compare(intArrayOf(3), getTail())
            compareGet(3, get(1))
        }
    }

    @Test
    fun test2() {
        Task707_2().apply {
            addAtHead(7)
            compare(intArrayOf(7), getHead(), true)
            compare(intArrayOf(7), getTail())
            addAtHead(2)
            compare(intArrayOf(2, 7), getHead(), true)
            compare(intArrayOf(7), getTail())
            addAtHead(1)
            compare(intArrayOf(1, 2, 7), getHead(), true)
            compare(intArrayOf(7), getTail())
            addAtIndex(3, 0)
            compare(intArrayOf(1, 2, 7, 0), getHead(), true)
            compare(intArrayOf(0), getTail())
            deleteAtIndex(2)
            compare(intArrayOf(1, 2, 0), getHead(), true)
            compare(intArrayOf(0), getTail())
            addAtHead(6)
            compare(intArrayOf(6, 1, 2, 0), getHead(), true)
            compare(intArrayOf(0), getTail())
            addAtTail(4)
            compare(intArrayOf(6, 1, 2, 0, 4), getHead(), true)
            compare(intArrayOf(4), getTail())
            compareGet(4, get(4))
            addAtHead(4)
            compare(intArrayOf(4, 6, 1, 2, 0, 4), getHead(), true)
            compare(intArrayOf(4), getTail())
            addAtIndex(5, 0)
            compare(intArrayOf(4, 6, 1, 2, 0, 0, 4), getHead(), true)
            compare(intArrayOf(4), getTail())
            addAtHead(6)
            compare(intArrayOf(6, 4, 6, 1, 2, 0, 0, 4), getHead(), true)
            compare(intArrayOf(4), getTail())
        }
    }

    @Test
    fun test3() {
        Task707_2().apply {
            addAtTail(1)
            compare(intArrayOf(1), getHead(), true)
            compare(intArrayOf(1), getTail())
            compareGet(1, get(0))
        }
    }

    @Test
    fun test4() {
        Task707_2().apply {
            addAtHead(2)
            compare(intArrayOf(2), getHead(), true)
            compare(intArrayOf(2), getTail())
            deleteAtIndex(1)
            compare(intArrayOf(2), getHead(), true)
            compare(intArrayOf(2), getTail())
            addAtHead(2)
            compare(intArrayOf(2, 2), getHead(), true)
            compare(intArrayOf(2), getTail())
            addAtHead(7)
            compare(intArrayOf(7, 2, 2), getHead(), true)
            compare(intArrayOf(2), getTail())
            addAtHead(3)
            compare(intArrayOf(3, 7, 2, 2), getHead(), true)
            compare(intArrayOf(2), getTail())
            addAtHead(2)
            compare(intArrayOf(2, 3, 7, 2, 2), getHead(), true)
            compare(intArrayOf(2), getTail())
            addAtHead(5)
            compare(intArrayOf(5, 2, 3, 7, 2, 2), getHead(), true)
            compare(intArrayOf(2), getTail())
            addAtTail(5)
            compare(intArrayOf(5, 2, 3, 7, 2, 2, 5), getHead(), true)
            compare(intArrayOf(5), getTail())
            compareGet(2, get(5))
            deleteAtIndex(6)
            compare(intArrayOf(5, 2, 3, 7, 2, 2), getHead(), true)
            compare(intArrayOf(2), getTail())
            deleteAtIndex(4)
            compare(intArrayOf(5, 2, 3, 7, 2), getHead(), true)
            compare(intArrayOf(2), getTail())
        }
    }

    @Test
    fun test5() {
        Task707_2().apply {
            addAtHead(31)
            compare(intArrayOf(31), getHead(), true)
            compare(intArrayOf(31), getTail())
            deleteAtIndex(1)
            compare(intArrayOf(31), getHead(), true)
            compare(intArrayOf(31), getTail())
            addAtIndex(1, 30)
            compare(intArrayOf(31, 30), getHead(), true)
            compare(intArrayOf(30), getTail())
            addAtIndex(1, 13)
            compare(intArrayOf(31, 13, 30), getHead(), true)
            compare(intArrayOf(30), getTail())
            deleteAtIndex(2)
            compare(intArrayOf(31, 13), getHead(), true)
            compare(intArrayOf(13), getTail())

        }
    }

    private fun compare(expected: IntArray, actual: IntDoublyNode?, shouldPrint: Boolean = false) {
        if (shouldPrint) actual?.print()
        Assertions.assertEquals(true, actual.equals(expected.toIntDoublyNode()))
    }

    private fun compareGet(expected: Int, actual: Int) {
        println(actual)
        Assertions.assertEquals(expected, actual)
    }
}

