package lc1_999.lc100_199

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.math.max

//Not Solved
//https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
class Task122 {
    fun maxProfitShort(prices: IntArray): Int {
        return prices.toList().zipWithNext { a, b -> max(0, b - a) }.sum()
    }

    fun maxProfit(prices: IntArray): Int {
        var profit = 0
        for (i in 1 until prices.size) {
            profit += max(prices[i] - prices[i - 1], 0)
        }
        return profit
    }
}

private class Task122Test {
    private val task = Task122()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(prices: IntArray, expected: Int) {
        Assertions.assertEquals(expected, task.maxProfit(prices))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(intArrayOf(7, 1, 5, 3, 6, 4), 7),
                Arguments.of(intArrayOf(1, 2, 3, 4, 5), 4),
                Arguments.of(intArrayOf(7, 6, 4, 3, 1), 0),
                Arguments.of(intArrayOf(7), 0),
                Arguments.of(intArrayOf(1, 2, 1, 2, 5), 5),
                Arguments.of(intArrayOf(1, 4, 3, 5, 6), 6)
            )
        }
    }
}