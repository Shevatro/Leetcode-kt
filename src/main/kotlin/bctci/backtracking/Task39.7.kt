package bctci.backtracking

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Task39_7 {
    fun calcStylishIKEAProduct(budget: Int, prices: List<Int>, ratings: List<Double>): List<Int> {
        return Solution(budget, prices, ratings).calcStylishIKEAProduct()
    }

    private class Solution(
        private val budget: Int,
        private val prices: List<Int>,
        private val ratings: List<Double>
    ) {
        private var curBudget = 0
        private var curRating = 0.0
        private var maxPickedRating = 0.0
        private val pickedItems = mutableSetOf<Int>()
        private var maxPickedItems = emptyList<Int>()

        fun calcStylishIKEAProduct(): List<Int> {
            backtrack(0)
            return maxPickedItems
        }

        private fun backtrack(i: Int) {
            if (i == prices.size) {
                if (curRating > maxPickedRating) {
                    maxPickedRating = curRating
                    maxPickedItems = pickedItems.toList()
                }
                return
            }

            val newBudget = curBudget + prices[i]
            if (newBudget <= budget) {
                //do
                curBudget += prices[i]
                curRating += ratings[i]
                pickedItems.add(i)


                backtrack(i + 1) // pick

                //undo
                curBudget -= prices[i]
                curRating -= ratings[i]
                pickedItems.remove(i)
            }

            backtrack(i + 1) // skip
        }
    }
}


private class Task39_7Test {
    private val task = Task39_7()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input1: Int, input2: List<Int>, input3: List<Double>, expected: List<Int>) {
        Assertions.assertEquals(expected, task.calcStylishIKEAProduct(input1, input2, input3))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(20, listOf(10, 5, 15, 8, 3), listOf(7.0, 3.5, 9.0, 6.0, 2.0), listOf(0, 3)),
                Arguments.of(10, listOf(2, 3, 4, 5), listOf(1.0, 2.0, 3.5, 4.0), listOf(2, 3)),
                Arguments.of(0, listOf(1, 2, 3), listOf(1.0, 2.0, 3.0), emptyList<Int>()),
                Arguments.of(10, emptyList<Int>(), emptyList<Double>(), emptyList<Int>()),
                Arguments.of(50, listOf(10, 20, 30), listOf(10.0, 20.0, 30.0), listOf(1, 2))
            )
        }
    }
}