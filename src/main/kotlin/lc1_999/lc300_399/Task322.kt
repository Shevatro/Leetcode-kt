package lc1_999.lc300_399

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.math.min

//Solved
//https://leetcode.com/problems/coin-change/description/
class Task322 {
    fun coinChange(coins: IntArray, amount: Int): Int {
        return Solution(coins, amount).coinChange()
    }

    private class Solution(
        private val coins: IntArray,
        private val amount: Int
    ) {
        private val memo = mutableMapOf<Long, Int>()
        fun coinChange(): Int {
            val result = dp(0L)
            return result
        }

        private fun dp(sum: Long): Int {
            if (sum == amount.toLong()) return 0
            memo[sum]?.let { return it }
            var min: Int? = null
            for (coin in coins) {
                if (sum + coin <= amount) {
                    val coinAmount = dp(sum + coin)
                    if (coinAmount < 0) continue
                    min = if (min != null) min(min, coinAmount) else coinAmount
                }
            }
            memo[sum] = if (min == null) -1 else min + 1
            return memo[sum]!!
        }
    }
}

private class Task322Test {
    private val task = Task322()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input1: IntArray, input2: Int, expected: Int) {
        Assertions.assertEquals(expected, task.coinChange(input1, input2))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(intArrayOf(1, 2, 5), 11, 3),
                Arguments.of(intArrayOf(2), 3, -1),
                Arguments.of(intArrayOf(1), 0, 0),
                Arguments.of(intArrayOf(1, 2147483647), 2, 2),
                Arguments.of(intArrayOf(186, 419, 83, 408), 6249, 20),
                Arguments.of(intArrayOf(2, 5, 10, 1), 27, 4)
            )
        }
    }
}