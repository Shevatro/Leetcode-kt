package lc1_999.lc700_799

import common.IntSinglyNode

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
        head.print(shouldPrintRoot = false)
    }

    fun deleteAtIndex(index: Int) {
        val prev = getNode(index - 1) ?: return
        val cur = prev.next ?: return
        prev.next = cur.next
        size--
        head.print(shouldPrintRoot = false)
    }
}


fun main() {
    Task707().apply {
        addAtHead(1)
        addAtTail(3)
        addAtIndex(1, 2)
        println(get(1))
        deleteAtIndex(1)
        println(get(1))
    }

    Task707().apply {
        addAtHead(7)
        addAtHead(2)
        addAtHead(1)
        addAtIndex(3, 0)
        deleteAtIndex(2)
        addAtHead(6)
        addAtTail(4)
        println(get(4))
        addAtHead(4)
        addAtIndex(5, 0)
        addAtHead(6)
    }

    Task707().apply {
        addAtTail(1)
        println(get(0))
    }

    Task707().apply {
        addAtHead(2)
        deleteAtIndex(1)
        addAtHead(2)
        addAtHead(7)
        addAtHead(3)
        addAtHead(2)
        addAtHead(5)
        addAtTail(5)
        println(get(5))
        deleteAtIndex(6)
        deleteAtIndex(4)
    }
}

