package lc1_999.lc1_99

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.math.max

//Solved
//https://leetcode.com/problems/insert-interval/description/
class Task57 {
    fun insert(intervals: Array<IntArray>, newInterval: IntArray): Array<IntArray> {
        val allIntervals = insertInterval(intervals, newInterval)
        return mergeOverlaps(allIntervals)
    }

    private fun insertInterval(intervals: Array<IntArray>, newInterval: IntArray): List<IntArray> {
        val allIntervals = intervals.toMutableList()
        for (i in allIntervals.indices) {
            if (allIntervals[i][0] >= newInterval[0]) {
                allIntervals.add(i, newInterval)
                break
            }
        }
        if (allIntervals.size != intervals.size + 1) {
            allIntervals.add(newInterval)
        }
        return allIntervals
    }

    private fun mergeOverlaps(intervals: List<IntArray>): Array<IntArray> {
        val result = mutableListOf<IntArray>()
        var prev = intervals[0]
        for (i in 1 until intervals.size) {
            val cur = intervals[i]
            if (prev[1] >= cur[0]) {
                prev[1] = max(prev[1], cur[1])
            } else {
                result.add(prev)
                prev = cur
            }
        }
        result.add(prev)
        return result.toTypedArray()
    }
}

private class Task57Test {
    private val task = Task57()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input1: Array<IntArray>, input2: IntArray, expected: Array<IntArray>) {
        Assertions.assertArrayEquals(expected, task.insert(input1, input2))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    arrayOf(intArrayOf(1, 3), intArrayOf(6, 9)), intArrayOf(2, 5),
                    arrayOf(intArrayOf(1, 5), intArrayOf(6, 9))
                ),
                Arguments.of(
                    arrayOf(intArrayOf(1, 2), intArrayOf(3, 5), intArrayOf(6, 7), intArrayOf(8, 10), intArrayOf(12, 16)),
                    intArrayOf(4, 8),
                    arrayOf(intArrayOf(1, 2), intArrayOf(3, 10), intArrayOf(12, 16))
                ),
                Arguments.of(emptyArray<IntArray>(), intArrayOf(5, 7), arrayOf(intArrayOf(5, 7))),
                Arguments.of(
                    arrayOf(intArrayOf(1, 5)), intArrayOf(6, 8),
                    arrayOf(intArrayOf(1, 5), intArrayOf(6, 8))
                )
            )
        }
    }
}