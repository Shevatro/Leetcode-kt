package lc1000_1999.lc1400_1499

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.*

//Monotonic Stack, Not Solved
//https://leetcode.com/problems/final-prices-with-a-special-discount-in-a-shop/description/
class Task1475() {
    fun finalPrices(prices: IntArray): IntArray {
        val stack = ArrayDeque<Int>()
        val result = prices.copyOf()
        for (i in prices.indices) {
            while (stack.isNotEmpty() && prices[i] <= prices[stack.peek()]) {
                val prevIndex = stack.pop()
                result[prevIndex] = prices[prevIndex] - prices[i]
            }
            stack.push(i)
        }
        return result
    }
}

private class Task1475Test {
    private val task = Task1475()

    @Test
    fun test1() {
        val input = intArrayOf(8, 4, 6, 2, 3)
        val output = intArrayOf(4, 2, 4, 2, 3)
        Assertions.assertArrayEquals(output, task.finalPrices(input))
    }

    @Test
    fun test2() {
        val input = intArrayOf(1, 2, 3, 4, 5)
        val output = intArrayOf(1, 2, 3, 4, 5)
        Assertions.assertArrayEquals(output, task.finalPrices(input))
    }

    @Test
    fun test3() {
        val input = intArrayOf(10, 1, 1, 6)
        val output = intArrayOf(9, 0, 1, 6)
        Assertions.assertArrayEquals(output, task.finalPrices(input))
    }

    @Test
    fun test4() {
        val input = intArrayOf(2, 3, 4, 5, 1)
        val output = intArrayOf(1, 2, 3, 4, 1)
        Assertions.assertArrayEquals(output, task.finalPrices(input))
    }
}