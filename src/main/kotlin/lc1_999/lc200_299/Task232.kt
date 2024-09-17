package lc1_999.lc200_299

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.*

//From Cracking The Coding Interview, Not Solved
//https://leetcode.com/problems/implement-queue-using-stacks/
class Task232() {
    private var writeStack = ArrayDeque<Int>()
    private var readStack = ArrayDeque<Int>()
    fun push(x: Int) {
        writeStack.push(x)
    }

    fun pop(): Int {
        regenerateReadStack()
        return readStack.pop()
    }

    fun peek(): Int {
        regenerateReadStack()
        return readStack.peek()
    }

    private fun regenerateReadStack(){
        if (!readStack.isEmpty())return
        for (i in 0 until  writeStack.size){
            readStack.push(writeStack.pop())

        }
    }

    fun empty(): Boolean {
        return writeStack.isEmpty() && readStack.isEmpty()
    }
    fun getReadStackForTest() = readStack
    fun getWriteStackForTest() = writeStack
}

private class Task232Test {
    @Test
    fun minStack() {
        val task = Task232()
        task.push(1)
        Assertions.assertEquals(listOf(1), task.getWriteStackForTest().toList())
        Assertions.assertEquals(emptyList<Int>(), task.getReadStackForTest().toList())
        task.push(2)
        Assertions.assertEquals(listOf(2,1), task.getWriteStackForTest().toList())
        Assertions.assertEquals(emptyList<Int>(), task.getReadStackForTest().toList())
        task.push(3)
        Assertions.assertEquals(listOf(3,2,1), task.getWriteStackForTest().toList())
        Assertions.assertEquals(emptyList<Int>(), task.getReadStackForTest().toList())
        Assertions.assertEquals(1, task.peek())
        Assertions.assertEquals(emptyList<Int>().toList(), task.getWriteStackForTest().toList())
        Assertions.assertEquals(listOf(1,2,3), task.getReadStackForTest().toList())
        Assertions.assertEquals(1, task.pop())
        Assertions.assertEquals(emptyList<Int>().toList(), task.getWriteStackForTest().toList())
        Assertions.assertEquals(listOf(2,3), task.getReadStackForTest().toList())
        Assertions.assertEquals(2, task.pop())
        Assertions.assertEquals(emptyList<Int>().toList(), task.getWriteStackForTest().toList())
        Assertions.assertEquals(listOf(3), task.getReadStackForTest().toList())
        Assertions.assertEquals(false, task.empty())
        }
}