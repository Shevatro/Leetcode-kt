package lc1000_1999.lc1100_1199

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

//From Cracking The Coding Interview, Solved?
//https://leetcode.com/problems/dinner-plate-stacks/
class Task1172(capacity: Int) {
    private val capacity = capacity
    private val stack = ArrayList<Int>()
    fun push(`val`: Int) {
        val emptyPosition = getFirstEmptyPosition()
        if (emptyPosition != -1) {
            stack[emptyPosition] = `val`
        } else {
            stack.add(`val`)
        }
    }

    fun pop(): Int {
        val position = getLastNotEmptyPosition()
        return if (position != -1) {
            val value = stack[position]
            stack[position] = 0
            return value
        } else {
            position
        }
    }

    fun popAtStack(index: Int): Int {
        val startPos = index * capacity
        val endPos = startPos + capacity - 1
        if (startPos > stack.size || endPos > stack.size) return -1
        for (i in endPos downTo startPos) {
            if (stack[i] != 0) {
                val value = stack[i]
                stack[i] = 0
                return value
            }
        }
        return -1
    }

    private fun getFirstEmptyPosition(): Int {
        return stack.indexOfFirst { it == 0 }
    }

    private fun getLastNotEmptyPosition(): Int {
        return stack.indexOfLast { it != 0 }
    }

    fun getStackForTest(): List<Int> = stack
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
        Assertions.assertEquals(listOf(1, 2, 3, 4, 5), task.getStackForTest())
        Assertions.assertEquals(2, task.popAtStack(0))
        //The stacks are now: 0: [0, 1]; 1: [4, 3]; 2: [5]
        Assertions.assertEquals(listOf(1, 0, 3, 4, 5), task.getStackForTest())
        task.push(20)
        //The stacks are now: 0: [20, 1]; 1: [4, 3]; 2: [5]
        Assertions.assertEquals(listOf(1, 20, 3, 4, 5), task.getStackForTest())
        task.push(21)
        //The stacks are now: 0: [20, 1]; 1: [4, 3]; 2: [5, 21]
        Assertions.assertEquals(listOf(1, 20, 3, 4, 5, 21), task.getStackForTest())
        Assertions.assertEquals(20, task.popAtStack(0))
        //The stacks are now: 0: [0, 1]; 1: [4, 3]; 2: [5, 21]
        Assertions.assertEquals(listOf(1, 0, 3, 4, 5, 21), task.getStackForTest())
        Assertions.assertEquals(21, task.popAtStack(2))
        //The stacks are now: 0: [0, 1]; 1: [4, 3]; 2: [5, 0]
        Assertions.assertEquals(listOf(1, 0, 3, 4, 5, 0), task.getStackForTest())
        Assertions.assertEquals(5, task.pop())
        //The stacks are now: 0: [0, 1]; 1: [4, 3]; 2: [21, 5]
        Assertions.assertEquals(listOf(1, 0, 3, 4, 0, 0), task.getStackForTest())
        Assertions.assertEquals(4, task.pop())
        //The stacks are now: 0: [0, 1]; 1: [4, 3]; 2: [21, 5]
        Assertions.assertEquals(listOf(1, 0, 3, 0, 0, 0), task.getStackForTest())
        Assertions.assertEquals(3, task.pop())
        //The stacks are now: 0: [0, 1]; 1: [4, 3]; 2: [21, 5]
        Assertions.assertEquals(listOf(1, 0, 0, 0, 0, 0), task.getStackForTest())
        Assertions.assertEquals(1, task.pop())
        //The stacks are now: 0: [0, 1]; 1: [4, 3]; 2: [21, 5]
        Assertions.assertEquals(listOf(0, 0, 0, 0, 0, 0), task.getStackForTest())
        Assertions.assertEquals(-1, task.pop())
        //The stacks are now: 0: [0, 1]; 1: [4, 3]; 2: [21, 5]
        Assertions.assertEquals(listOf(0, 0, 0, 0, 0, 0), task.getStackForTest())
    }
}