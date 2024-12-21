package lc1_999.lc1_99

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.math.abs

//From Cracking The Coding Interview, Solved
//https://leetcode.com/problems/divide-two-integers/
class Task29 {
    fun divide(dividend: Int, divisor: Int): Int {
        return Solution(dividend.toLong(), divisor.toLong()).divide()
    }

    private class Solution(
        private val dividend: Long,
        private val divisor: Long
    ) {
        private val absDividend = abs(dividend)
        private val absDivisor = abs(divisor)
        private val cache = mutableListOf<Data>()
        fun divide(): Int {
            if (dividend == 0L) return 0
            if (dividend == divisor) return 1
            if (divisor == 1L) return dividend.toInt()
            if (absDivisor > absDividend) return 0
            fillCache()
            val result = backTrack().amount
            //if overflow
            if (result < 0) return Int.MAX_VALUE
            return if (isResultNegative()) -result else result
        }

        private fun isResultNegative(): Boolean {
            return dividend < 0 && divisor > 0 || dividend > 0 && divisor < 0
        }

        private fun fillCache() {
            var data = Data(absDivisor, 1)
            cache.add(data)
            while (data.sum < absDividend) {
                data = Data(data.sum + data.sum, data.amount + data.amount)
                cache.add(data)
            }
        }

        private fun backTrack(): Data {
            if (cache.size == 1) return cache[0]
            val lastIndex = cache.lastIndex - 1
            var data = cache[lastIndex]
            var i = lastIndex - 1
            while (absDividend - data.sum >= absDivisor) {
                if (data.sum + cache[i].sum <= absDividend) {
                    data = Data(data.sum + cache[i].sum, data.amount + cache[i].amount)
                } else {
                    i--
                }
            }
            return data
        }

        private data class Data(
            val sum: Long,
            val amount: Int
        )
    }
}

private class Task29Test {
    private val task = Task29()

    @Test
    fun divideTest1() {
        val actual = task.divide(10, 3)
        Assertions.assertEquals(3, actual)
    }

    @Test
    fun divideTest2() {
        val actual = task.divide(7, -3)
        Assertions.assertEquals(-2, actual)
    }

    @Test
    fun divideTest3() {
        val actual = task.divide(-7, -3)
        Assertions.assertEquals(2, actual)
    }

    @Test
    fun divideTest4() {
        val actual = task.divide(300000, 2)
        Assertions.assertEquals(150000, actual)
    }

    @Test
    fun divideTest5() {
        val actual = task.divide(0, 2)
        Assertions.assertEquals(0, actual)
    }

    @Test
    fun divideTest6() {
        val actual = task.divide(300000, 300000)
        Assertions.assertEquals(1, actual)
    }

    @Test
    fun divideTest7() {
        val actual = task.divide(1, -1)
        Assertions.assertEquals(-1, actual)
    }

    @Test
    fun divideTest8() {
        val actual = task.divide(Int.MAX_VALUE, 2)
        Assertions.assertEquals(Int.MAX_VALUE / 2, actual)
    }

    @Test
    fun divideTest9() {
        val actual = task.divide(1, 2)
        Assertions.assertEquals(0, actual)
    }

    @Test
    fun divideTest10() {
        val actual = task.divide(Int.MIN_VALUE, -1)
        Assertions.assertEquals(Int.MAX_VALUE, actual)
    }

    @Test
    fun divideTest11() {
        val actual = task.divide(Int.MAX_VALUE, 1)
        Assertions.assertEquals(Int.MAX_VALUE, actual)
    }

    @Test
    fun divideTest12() {
        val actual = task.divide(Int.MIN_VALUE, 1)
        Assertions.assertEquals(Int.MIN_VALUE, actual)
    }

    @Test
    fun divideTest13() {
        val actual = task.divide(Int.MIN_VALUE, 2)
        Assertions.assertEquals(-1073741824, actual)
    }

    @Test
    fun divideTest14() {
        val actual = task.divide(-231, 3)
        Assertions.assertEquals(-77, actual)
    }
}