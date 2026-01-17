package lc1_999.lc400_499

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

//Not Solved
//https://leetcode.com/problems/find-right-interval/
class Task436 {
    fun findRightInterval(intervals: Array<IntArray>): IntArray {
        val result = IntArray(intervals.size)
        val sortedStartTimes = sortByStartTimeWithInd(intervals)
        for (i in intervals.indices) {
            val end = intervals[i][1]
            //we need to match a pair with and artificial IntArray
            val index = sortedStartTimes.binarySearch(intArrayOf(end, 0), compareBy { it[0] })
            if (index >= 0) {
                result[i] = sortedStartTimes[index][1]
            } else {
                val expectedInd = -index - 1
                //if it expects to be after the last valid intervla
                if (expectedInd == sortedStartTimes.size) {
                    result[i] = -1
                } else {
                    result[i] = sortedStartTimes[expectedInd][1]
                }
            }
        }
        return result
    }

    private fun sortByStartTimeWithInd(intervals: Array<IntArray>): Array<IntArray> {
        val newIntervals = Array(intervals.size) { IntArray(2) }
        for (i in intervals.indices) {
            newIntervals[i][0] = intervals[i][0]
            newIntervals[i][1] = i
        }
        newIntervals.sortBy { it[0] }
        return newIntervals
    }
}

private class Task436Test {
    private val task = Task436()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input: Array<IntArray>, expected: IntArray) {
        Assertions.assertArrayEquals(expected, task.findRightInterval(input))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(arrayOf(intArrayOf(1, 2)), intArrayOf(-1)),
                Arguments.of(arrayOf(intArrayOf(3, 4), intArrayOf(2, 3), intArrayOf(1, 2)), intArrayOf(-1, 0, 1)),
                Arguments.of(arrayOf(intArrayOf(1, 4), intArrayOf(2, 3), intArrayOf(3, 4)), intArrayOf(-1, 2, -1)),
                Arguments.of(
                    arrayOf(
                        intArrayOf(1, 12), intArrayOf(2, 9), intArrayOf(3, 10), intArrayOf(13, 14), intArrayOf(15, 16), intArrayOf(16, 17)
                    ), intArrayOf(3, 3, 3, 4, 5, -1)
                )
            )
        }
    }
}