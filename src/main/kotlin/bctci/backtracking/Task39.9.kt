package bctci.backtracking

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.LinkedList
import java.util.stream.Stream

class Task39_9 {
    fun countUniqueSubmultisetsWithSumZero(multiset: List<Int>): Int {
        return Solution(multiset).countUniqueSubmultisetsWithSumZero()
    }

    private class Solution(
        private val multiset: List<Int>
    ) {
        private var allCombinations = mutableSetOf<List<Int>>()
        private var curCombination = LinkedList<Int>()
        fun countUniqueSubmultisetsWithSumZero(): Int {
            backtrack(0, 0)
            return allCombinations.size
        }

        private fun backtrack(i: Int, sum: Int) {
            if (i == multiset.size) {
                if (sum == 0) {
                    val sortedCombination = curCombination.sorted()
                    if (!allCombinations.contains(sortedCombination)) allCombinations.add(sortedCombination)
                }
                return
            }
            curCombination.add(multiset[i]) //do
            backtrack(i + 1, multiset[i] + sum) //pick
            curCombination.remove(multiset[i]) //undo

            backtrack(i + 1, sum) //skip
        }
    }
}


private class Task39_9Test {
    private val task = Task39_9()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input: List<Int>, expected: Int) {
        Assertions.assertEquals(expected, task.countUniqueSubmultisetsWithSumZero(input))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(listOf(1, 1, -1, -1), 3),
                Arguments.of(emptyList<Int>(), 1),
                Arguments.of(listOf(-1, 2, 1, 0, 3), 4),
                Arguments.of(listOf(1, 2, 3), 1),
                Arguments.of(listOf(0, 0, 0), 4),
                Arguments.of(listOf(1, -1, -1, 1), 3),
                Arguments.of(listOf(0), 2),
            )
        }
    }
}