package lc1_999.lc500_599

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

//Solved
//https://leetcode.com/problems/fibonacci-number/

class Task509 {
    fun fib(n: Int): Int {
//        return BottomUpSolution(n).fib()
        return TopDownSolution(n).fib()
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

    private class TopDownSolutionWithCache(private val n: Int) {
        private val cache = IntArray(n + 1)
        fun fib(): Int {
            if (n == 0 || n == 1) return n
            cache[1] = 1
            for (i in 2 until n + 1) {
                cache[i] = cache[i - 1] + cache[i - 2]
            }
            return cache[n]
        }
    }

    private class TopDownSolution(private val n: Int) {
        fun fib(): Int {
            if (n == 0 || n == 1) return n
            var `n-2` = 0
            var `n-1` = 1
            for (i in 2 until n + 1) {
                val n = `n-2` + `n-1`
                `n-2` = `n-1`
                `n-1` = n
            }
            return `n-1`
        }
    }
}

private class Task509Test {
    private val task = Task509()

    @Test
    fun fibTest1() {
        fib(2, 1)
    }

    @Test
    fun fibTest2() {
        fib(3, 2)
    }

    @Test
    fun fibTest3() {
        fib(4, 3)
    }

    @Test
    fun fibTest4() {
        fib(10, 55)
    }

    private fun fib(actualInp: Int, expected: Int) {
        val actual = task.fib(actualInp)
        Assertions.assertEquals(expected, actual)
    }
}