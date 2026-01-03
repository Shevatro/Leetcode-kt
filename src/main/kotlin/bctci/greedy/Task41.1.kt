package bctci.greedy

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Task41_1 {
    fun findMostNonOverlappingIntervals(intervals: Array<IntArray>): Int {
        return Solution(intervals).findMostNonOverlappingIntervals()
    }

    private class Solution(private val intervals: Array<IntArray>) {

        fun findMostNonOverlappingIntervals(): Int {
            intervals.sortBy { it[1] }
            var count = 0
            var previousEnd = Int.MIN_VALUE
            for (interval in intervals) {
                val curStart = interval[0]
                val curEnd = interval[1]
                if (curStart > previousEnd) {
                    count++
                    previousEnd = curEnd
                }
            }
            return count
        }
    }
}


private class Task41_1Test {
    private val task = Task41_1()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input: Array<IntArray>, expected: Int) {
        Assertions.assertEquals(expected, task.findMostNonOverlappingIntervals(input))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(arrayOf(intArrayOf(2, 3), intArrayOf(1, 4), intArrayOf(2, 3), intArrayOf(3, 6), intArrayOf(8, 9)), 2),
                Arguments.of(arrayOf(intArrayOf(1, 2), intArrayOf(2, 3), intArrayOf(3, 4)), 2),
                Arguments.of(arrayOf(intArrayOf(1, 10), intArrayOf(8, 9), intArrayOf(2, 3)), 2),
                Arguments.of(emptyArray<IntArray>(), 0),
                Arguments.of(arrayOf(intArrayOf(1, 5), intArrayOf(2, 6), intArrayOf(3, 7)), 1),
                Arguments.of(arrayOf(intArrayOf(1, 2), intArrayOf(3, 4), intArrayOf(5, 6)), 3),
                Arguments.of(arrayOf(intArrayOf(1, 2)), 1)
            )
        }
    }
}