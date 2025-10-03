package lc1_999.lc100_199

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.math.max
import kotlin.math.min

//Solved from the second attempt, tricky
//https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/
class Task121 {
    fun maxProfit(prices: IntArray): Int {
        var maxProfit = 0
        var minPrice = prices[0]
        for (price in prices) {
            maxProfit = max(maxProfit, price - minPrice)
            minPrice = min(minPrice, price)
        }
        return maxProfit
    }
}

private class Task121Test {
    private val task = Task121()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(prices: IntArray, expected: Int) {
        Assertions.assertEquals(expected, task.maxProfit(prices))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(intArrayOf(7, 1, 5, 3, 6, 4), 5),
                Arguments.of(intArrayOf(7, 6, 4, 3, 1), 0),
                Arguments.of(intArrayOf(7), 0),
                Arguments.of(intArrayOf(7, 1), 0),
                Arguments.of(intArrayOf(2, 4, 1), 2)
            )
        }
    }
}