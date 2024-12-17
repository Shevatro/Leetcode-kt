package lc1000_1999.lc1100_1199

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

//From Cracking The Coding Interview, Solved
//https://leetcode.com/problems/n-th-tribonacci-number/
class Task1137 {
    fun tribonacci(n: Int): Int {
//        return TopDownSolution(n).tribonacci()
        return BottomUpSolution(n).tribonacci()
    }

    private class TopDownSolution(private val n: Int) {
        private val cache = IntArray(n + 1)
        fun tribonacci(i: Int = n): Int {
            if (i == 0) return 0
            if (i == 1 || i == 2) return 1
            if (cache[i] == 0) {
                cache[i] = tribonacci(i - 1) + tribonacci(i - 2) + tribonacci(i - 3)
            }
            return cache[i]
        }
    }

    private class BottomUpSolution(private val n: Int) {
        fun tribonacci(): Int {
            if (n == 0) return 0
            if (n == 1 || n == 2) return 1
            var `n-3` = 0
            var `n-2` = 1
            var `n-1` = 1
            for (i in 3 until n + 1) {
                val n = `n-3` + `n-2` + `n-1`
                `n-3` = `n-2`
                `n-2` = `n-1`
                `n-1` = n
            }
            return `n-1`
        }
    }
}

private class Task1137Test {
    private val task = Task1137()

    @Test
    fun tribonacciTest1() {
        tribonacci(4, 4)
    }

    @Test
    fun tribonacciTest2() {
        tribonacci(25, 1389537)
    }

    private fun tribonacci(actualInp: Int, expected: Int) {
        val actual = task.tribonacci(actualInp)
        Assertions.assertEquals(expected, actual)
    }
}