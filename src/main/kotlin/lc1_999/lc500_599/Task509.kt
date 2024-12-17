package lc1_999.lc500_599

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

//Solved
//https://leetcode.com/problems/fibonacci-number/

class Task509 {
    fun fib(n: Int): Int {
        return BottomUpSolution(n).fib()
    }

    private class BottomUpSolution(private val n: Int) {
        private val cache = IntArray(n + 1)
        fun fib(i: Int = n): Int {
            if (i == 0 || i == 1) return i
            if (cache[i] == 0) {
                cache[i] = fib(i - 1) + fib(i - 2)
            }
            return cache[i]
        }
    }
}

private class Task509Test {
    private val task = Task509()

    @Test
    fun fib() {
        fib(2, 1)
        fib(3, 2)
        fib(4, 3)
    }

    private fun fib(actualInp: Int, expected: Int) {
        val actual = task.fib(actualInp)
        println(actual)
        Assertions.assertEquals(expected, actual)
    }
}