package lc1_999.lc100_199

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
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

    @Test
    fun test1() {
        val result = task.maxProfit(intArrayOf(7, 1, 5, 3, 6, 4))
        Assertions.assertEquals(5, result)
    }

    @Test
    fun test2() {
        val result = task.maxProfit(intArrayOf(7, 6, 4, 3, 1))
        Assertions.assertEquals(0, result)
    }

    @Test
    fun test3() {
        val result = task.maxProfit(intArrayOf(7))
        Assertions.assertEquals(0, result)
    }

    @Test
    fun test4() {
        val result = task.maxProfit(intArrayOf(7, 1))
        Assertions.assertEquals(0, result)
    }

    @Test
    fun test5() {
        val result = task.maxProfit(intArrayOf(2, 4, 1))
        Assertions.assertEquals(2, result)
    }
}