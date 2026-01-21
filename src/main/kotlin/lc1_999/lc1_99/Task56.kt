package lc1_999.lc1_99

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.math.max

//Solved
//https://leetcode.com/problems/merge-intervals/description/
class Task56 {
    fun merge(intervals: Array<IntArray>): Array<IntArray> {
        val sortedIntervals = intervals.sortedBy { it[0] }
        return mergeIntervals(sortedIntervals).toTypedArray()
    }

    private fun mergeIntervals(sortedIntervals: List<IntArray>): List<IntArray> {
        val result = mutableListOf<IntArray>()
        var previousInterval = sortedIntervals[0]
        for (i in 1 until sortedIntervals.size) {
            val currentInterval = sortedIntervals[i]
            if (currentInterval[0] <= previousInterval[1]) {
                previousInterval[1] = max(currentInterval[1], previousInterval[1])
            } else {
                result.add(previousInterval)
                previousInterval = currentInterval
            }
        }
        result.add(previousInterval)
        return result
    }
}

private class Task56Test {
    private val task = Task56()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input: Array<IntArray>, expected: Array<IntArray>) {
        Assertions.assertArrayEquals(expected, task.merge(input))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    arrayOf(intArrayOf(1, 3), intArrayOf(2, 6), intArrayOf(8, 10), intArrayOf(15, 18)),
                    arrayOf(intArrayOf(1, 6), intArrayOf(8, 10), intArrayOf(15, 18))
                ),
                Arguments.of(arrayOf(intArrayOf(1, 4), intArrayOf(4, 5)), arrayOf(intArrayOf(1, 5))),
                Arguments.of(
                    arrayOf(intArrayOf(15, 18), intArrayOf(1, 3), intArrayOf(2, 6), intArrayOf(8, 10), intArrayOf(15, 18)),
                    arrayOf(intArrayOf(1, 6), intArrayOf(8, 10), intArrayOf(15, 18))
                ),
                Arguments.of(arrayOf(intArrayOf(15, 18)), arrayOf(intArrayOf(15, 18))),
                Arguments.of(arrayOf(intArrayOf(1, 4), intArrayOf(2, 3)), arrayOf(intArrayOf(1, 4)))
            )
        }
    }
}