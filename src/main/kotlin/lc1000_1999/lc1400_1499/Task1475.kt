package lc1000_1999.lc1400_1499

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.*
import java.util.stream.Stream

//Monotonic Stack, Not Solved
//https://leetcode.com/problems/final-prices-with-a-special-discount-in-a-shop/description/
class Task1475() {
    fun finalPrices(prices: IntArray): IntArray {
        val result = prices.copyOf()
        val stack = ArrayDeque<Int>()
        for (i in prices.indices){
            while (stack.isNotEmpty() && prices[stack.peek()]>= prices[i]){
                val index = stack.pop()
                result[index] -= prices[i]
            }
            stack.push(i)
        }
        return result
    }
}

private class Task1475Test {
    private val task = Task1475()

    @ParameterizedTest
    @MethodSource("intArraysProvider")
    fun test(input: IntArray, expected: IntArray) {
        Assertions.assertArrayEquals(expected, task.finalPrices(input))
    }

    companion object {
        @JvmStatic
        private fun intArraysProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(intArrayOf(8, 4, 6, 2, 3), intArrayOf(4, 2, 4, 2, 3)),
                Arguments.of(intArrayOf(1, 2, 3, 4, 5), intArrayOf(1, 2, 3, 4, 5)),
                Arguments.of(intArrayOf(10, 1, 1, 6), intArrayOf(9, 0, 1, 6)),
                Arguments.of(intArrayOf(2, 3, 4, 5, 1), intArrayOf(1, 2, 3, 4, 1))
            )
        }
    }
}