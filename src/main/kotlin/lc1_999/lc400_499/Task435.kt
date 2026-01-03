package lc1_999.lc400_499

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

//From Beyond Cracking The Coding Interview, Solved
//https://leetcode.com/problems/non-overlapping-intervals/description/
class Task435 {
    fun eraseOverlapIntervals(intervals: Array<IntArray>): Int {
        intervals.sortBy { it[1] }
        var count = 0
        var prevEnd = Int.MIN_VALUE
        for (interval in intervals) {
            if (interval[0] >= prevEnd) {
                prevEnd = interval[1]
                count++
            }
        }
        return intervals.size - count
    }
}

private class Task435Test {
    private val task = Task435()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input: Array<IntArray>, expected: Int) {
        Assertions.assertEquals(expected, task.eraseOverlapIntervals(input))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(arrayOf(intArrayOf(1, 2), intArrayOf(2, 3), intArrayOf(3, 4), intArrayOf(1, 3)), 1),
                Arguments.of(arrayOf(intArrayOf(1, 2), intArrayOf(1, 2), intArrayOf(1, 2)), 2),
                Arguments.of(arrayOf(intArrayOf(1, 2), intArrayOf(2, 3)), 0),
                Arguments.of(arrayOf(intArrayOf(1, 100), intArrayOf(11, 22), intArrayOf(1, 11), intArrayOf(2, 12)), 2)
            )
        }
    }
}