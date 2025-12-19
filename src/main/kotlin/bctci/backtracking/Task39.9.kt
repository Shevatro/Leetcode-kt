package bctci.backtracking

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Task39_9 {
    fun countUniqueSubmultisetsWithSumZero(multiset: List<Int>): Int {
        return Solution(multiset).countUniqueSubmultisetsWithSumZero()
    }

    private class Solution(
        private val multiset: List<Int>
    ) {
        private val frequencyMap: Map<Int, Int> by lazy {
            multiset.groupingBy { it }.eachCount()
        }
        private val uniqueItems = frequencyMap.keys.toList()
        private var count = 0
        fun countUniqueSubmultisetsWithSumZero(): Int {
            backtrack(0, 0)
            return count
        }

        private fun backtrack(i: Int, sum: Int) {
            if (i == uniqueItems.size) {
                if (sum == 0) count++
                return
            }
            val uniqueItem = uniqueItems[i]
            val frequency = frequencyMap[uniqueItem]!!
            repeat(frequency + 1) { curRepeat ->
                backtrack(i + 1, sum + (frequency - curRepeat) * uniqueItem)
            }
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