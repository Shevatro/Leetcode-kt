package lc1_999.lc300_399

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.math.min

//From Beyond Cracking The Coding Interview, Solved
//https://leetcode.com/problems/integer-replacement/
class Task397 {
    fun integerReplacement(n: Int): Int {
        return Solution(n).integerReplacement()
    }

    private class Solution(private val n: Int) {
        private val memo = mutableMapOf<Long, Int>()

        fun integerReplacement(): Int {
            return dp(n.toLong())
        }

        private fun dp(num: Long): Int {
            if (num == 1L || num > n + 1L) return 0
            if (memo[num] != null) return memo[num]!!
            val steps = if (num % 2L == 0L) {
                dp(num / 2)
            } else {
                min(dp(num - 1), dp(num + 1))
            }
            memo[num] = steps + 1
            return memo[num]!!
        }
    }
}

private class Task397Test {
    private val task = Task397()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input: Int, expected: Int) {
        Assertions.assertEquals(expected, task.integerReplacement(input))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(8, 3),
                Arguments.of(7, 4),
                Arguments.of(4, 2),
                Arguments.of(100000000, 31),
                Arguments.of(2147483647, 32)
            )
        }
    }
}