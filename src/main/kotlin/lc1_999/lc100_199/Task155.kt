package lc1_999.lc100_199

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.collections.ArrayDeque
import kotlin.math.min


//From a learning section, Solved but with a hint
//https://leetcode.com/problems/min-stack/
class Task155 {
    class Solution1 {
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
    }

    class Solution2 {
        private val stack = ArrayDeque<Node>()
        fun push(`val`: Int) {
            val previousTop = stack.firstOrNull()
            val min = min(previousTop?.min ?: Int.MAX_VALUE, `val`)
            stack.addFirst(Node(`val`, min, previousTop))
        }

        fun pop() {
            stack.removeFirst()
        }

        fun top(): Int {
            return stack.first().`val`
        }

        fun getMin(): Int {
            return stack.first().min
        }
    }


    private class Node(
        val `val`: Int,
        val min: Int,
        val next: Node?
    )
}

private class Task155Test {
    @Test
    fun minStackTest() {
        val task = Task155.Solution1()
        task.push(-2)
        task.push(0)
        task.push(-3)
        Assertions.assertEquals(-3, task.getMin())
        task.pop()
        Assertions.assertEquals(0, task.top())
        Assertions.assertEquals(-2, task.getMin())
    }

    @Test
    fun minStackTest2() {
        val task = Task155.Solution2()
        task.push(-2)
        task.push(0)
        task.push(-3)
        Assertions.assertEquals(-3, task.getMin())
        task.pop()
        Assertions.assertEquals(0, task.top())
        Assertions.assertEquals(-2, task.getMin())
    }
}