package lc2000_2999.lc2300_2399

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.math.min

// Solved
//https://leetcode.com/problems/calculate-amount-paid-in-taxes/description/
class Task2303() {
    fun calculateTax(brackets: Array<IntArray>, income: Int): Double {
        var incomeLeft = income
        var intervalStart = 0
        var totalTax = 0.0
        for (bracket in brackets) {
            if (incomeLeft <= 0) break
            val (intervalEnd, taxRate) = bracket
            val curIncome = min(intervalEnd - intervalStart, incomeLeft)
            totalTax += curIncome * taxRate / 100.0
            incomeLeft -= curIncome
            intervalStart = intervalEnd
        }
        return totalTax
    }
}

private class Task2303Test {
    private val task = Task2303()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(brackets: Array<IntArray>, income: Int, expected: Double) {
        Assertions.assertEquals(expected, task.calculateTax(brackets, income))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(arrayOf(intArrayOf(3, 50), intArrayOf(7, 10), intArrayOf(12, 25)), 10, 2.65),
                Arguments.of(arrayOf(intArrayOf(1, 0), intArrayOf(4, 25), intArrayOf(5, 50)), 2, 0.25),
                Arguments.of(arrayOf(intArrayOf(2, 50)), 0, 0)
            )
        }
    }
}