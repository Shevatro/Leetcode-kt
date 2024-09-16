package lc1000_1999.lc1100_1199

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.LinkedList
import java.util.TreeSet

//From Cracking The Coding Interview, Solved
//https://leetcode.com/problems/dinner-plate-stacks/
class Task1172(capacity: Int) {
    private val capacity = capacity
    private val stacks = ArrayList<LinkedList<Int>>()
    private val availableStacks = TreeSet<Int>()
    fun push(`val`: Int) {
        val firstEmptyPos = availableStacks.firstOrNull()
        if (firstEmptyPos == null) {
            val position = stacks.size
            val newStack = LinkedList<Int>().apply { addFirst(`val`) }
            stacks.add(newStack)
            if (capacity > 1) {
                availableStacks.add(position)
            }
        } else {
            val stack = stacks[firstEmptyPos]
            stack.addFirst(`val`)
            if (stack.size == capacity) {
                availableStacks.remove(firstEmptyPos)
            }
        }
    }

    fun pop(): Int {
        return popAtStack(stacks.size - 1)
    }

    fun popAtStack(index: Int): Int {
        val stack = stacks.getOrNull(index) ?: return -1
        if (stack.isEmpty()) return -1
        if (!availableStacks.contains(index)) availableStacks.add(index)
        val item = stack.pop()
        cleanUp()
        return item
    }

    private fun cleanUp() {
        for (i in stacks.size - 1 downTo 0) {
            if (stacks[i].isEmpty()) {
                stacks.removeAt(i)
                availableStacks.remove(i)
            } else {
                break
            }
        }
    }

    fun getStackForTest(): ArrayList<LinkedList<Int>> = stacks
}

private class Task1172Test {
    @Test
    fun minStack() {
        val task = Task1172(2)
        task.push(1)
        task.push(2)
        task.push(3)
        task.push(4)
        task.push(5)
        //The stacks are now: 0: [2, 1]; 1: [4, 3]; 2: [5]
        Assertions.assertEquals(createLList(listOf(2, 1), listOf(4, 3), listOf(5)), task.getStackForTest())
        Assertions.assertEquals(2, task.popAtStack(0))
        //The stacks are now: 0: [1]; 1: [4, 3]; 2: [5]
        Assertions.assertEquals(createLList(listOf(1), listOf(4, 3), listOf(5)), task.getStackForTest())
        task.push(20)
        //The stacks are now: 0: [20, 1]; 1: [4, 3]; 2: [5]
        Assertions.assertEquals(createLList(listOf(20, 1), listOf(4, 3), listOf(5)), task.getStackForTest())
        task.push(21)
        //The stacks are now: 0: [20, 1]; 1: [4, 3]; 2: [21, 5]
        Assertions.assertEquals(createLList(listOf(20, 1), listOf(4, 3), listOf(21, 5)), task.getStackForTest())
        Assertions.assertEquals(20, task.popAtStack(0))
        //The stacks are now: 0: [1]; 1: [4, 3]; 2: [21, 5]
        Assertions.assertEquals(createLList(listOf(1), listOf(4, 3), listOf(21, 5)), task.getStackForTest())
        Assertions.assertEquals(21, task.popAtStack(2))
        //The stacks are now: 0: [1]; 1: [4, 3]; 2: [5]
        Assertions.assertEquals(createLList(listOf(1), listOf(4, 3), listOf(5)), task.getStackForTest())
        Assertions.assertEquals(5, task.pop())
        //The stacks are now: 0: [1]; 1: [4, 3], 2:[]
        Assertions.assertEquals(createLList(listOf(1), listOf(4, 3)), task.getStackForTest())
        Assertions.assertEquals(4, task.pop())
        //The stacks are now: 0: [1]; 1: [3], 2:[]
        Assertions.assertEquals(createLList(listOf(1), listOf(3)), task.getStackForTest())
        Assertions.assertEquals(3, task.pop())
        //The stacks are now: 0: [1], 1:[], 2:[]
        Assertions.assertEquals(createLList(listOf(1)), task.getStackForTest())
        Assertions.assertEquals(1, task.pop())
        //The stacks are now: 0: []; 1: []; 2: []
        Assertions.assertEquals(createLList(), task.getStackForTest())
        Assertions.assertEquals(-1, task.pop())
        //The stacks are now: 0: []; 1: []; 2: []
        Assertions.assertEquals(createLList(), task.getStackForTest())
    }

    private fun createLList(vararg list: List<Int>): List<LinkedList<Int>> {
        return list.map { LinkedList(it) }
    }
}