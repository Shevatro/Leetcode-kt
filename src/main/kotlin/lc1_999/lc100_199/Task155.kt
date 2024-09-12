package lc1_999.lc100_199

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.math.min


//From a learning section, Solved but with a hint
//https://leetcode.com/problems/min-stack/
class Task155 {
    private var top: Node? = null
    fun push(`val`: Int) {
        val previousTop = top
        val min = min(previousTop?.min ?: Int.MAX_VALUE, `val`)
        top = Node(`val`, min, previousTop)
    }

    fun pop() {
        top = top?.next
    }

    fun top(): Int {
        return requireNotNull(top).`val`
    }

    fun getMin(): Int {
        return requireNotNull(top).min
    }

    private class Node(
        val `val`: Int,
        val min: Int,
        val next: Node?
    )
}

private class Task155Test {
    private val task = Task155()

    @Test
    fun minStack() {
        task.push(-2)
        task.push(0)
        task.push(-3)
        Assertions.assertEquals(-3, task.getMin())
        task.pop()
        Assertions.assertEquals(0, task.top())
        Assertions.assertEquals(-2, task.getMin())
    }
}