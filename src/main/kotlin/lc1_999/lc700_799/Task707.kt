package lc1_999.lc700_799

import common.IntSinglyNode

//From a learning section, Solved
//https://leetcode.com/problems/design-linked-list/

class Task707 {
    private var head: IntSinglyNode? = null
    private var size = 0

    private fun getNode(index: Int): IntSinglyNode? {
        var curIndex = 0
        var node = head
        while (node != null) {
            if (index == curIndex) {
                return node
            }
            node = node.next
            curIndex++
        }
        return null
    }

    fun get(index: Int): Int {
        return getNode(index)?.`val` ?: -1
    }

    fun addAtHead(`val`: Int) {
        head = (IntSinglyNode(`val`).apply { next = head })
        size++
        head?.print()
    }

    fun addAtTail(`val`: Int) {
        if (head == null) {
            addAtHead(`val`)
            return
        }
        var node = head
        while (node?.next != null) {
            node = node.next
        }
        node?.next = IntSinglyNode(`val`)
        size++
        head?.print()
    }

    private fun addBeforeNode(node: IntSinglyNode?, `val`: Int) {
        node?.next = IntSinglyNode(`val`).apply { next = node?.next }
        size++
        head?.print()
    }

    fun addAtIndex(index: Int, `val`: Int) {
        if (index == 0) {
            addAtHead(`val`)
            return
        }
        if (index < size) {
            addBeforeNode(getNode(index - 1), `val`)
        } else if (index == size) {
            addAtTail(`val`)
        }
    }

    fun deleteAtIndex(index: Int) {
        if (index == 0) {
            head = head?.next
        } else {
            val prev = getNode(index - 1) ?: return
            val node = prev.next ?: return
            prev.next = node.next
        }
        size--
        head?.print()
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
}

