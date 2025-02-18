package lc1_999.lc1_99

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.math.max
import kotlin.math.min

//Solved
//https://leetcode.com/problems/merge-intervals/description/
class Task56 {
    fun merge(intervals: Array<IntArray>): Array<IntArray> {
        val result = mutableListOf<IntArray>()
        val sortedIntervals = intervals.sortedBy { it[0] }
        var previousInterval = sortedIntervals[0]
        for (i in 1 until sortedIntervals.size) {
            val currentInterval = sortedIntervals[i]
            if (currentInterval[0] <= previousInterval[1]) {
                previousInterval[0] = min(currentInterval[0], previousInterval[0])
                previousInterval[1] = max(currentInterval[1], previousInterval[1])
            } else {
                result.add(previousInterval)
                previousInterval = currentInterval
            }
        }
        result.add(previousInterval)
        return result.toTypedArray()
    }
}

private class Task56Test {
    private val task = Task56()

    @Test
    fun test1() {
        val input = arrayOf(intArrayOf(1, 3), intArrayOf(2, 6), intArrayOf(8, 10), intArrayOf(15, 18))
        val expectedResult = arrayOf(intArrayOf(1, 6), intArrayOf(8, 10), intArrayOf(15, 18))
        Assertions.assertArrayEquals(expectedResult, task.merge(input))
    }

    @Test
    fun test2() {
        val input = arrayOf(intArrayOf(1, 4), intArrayOf(4, 5))
        val expectedResult = arrayOf(intArrayOf(1, 5))
        Assertions.assertArrayEquals(expectedResult, task.merge(input))
    }

    @Test
    fun test3() {
        val input = arrayOf(
            intArrayOf(15, 18), intArrayOf(1, 3), intArrayOf(2, 6), intArrayOf(8, 10), intArrayOf(15, 18)
        )
        val expectedResult = arrayOf(intArrayOf(1, 6), intArrayOf(8, 10), intArrayOf(15, 18))
        Assertions.assertArrayEquals(expectedResult, task.merge(input))
    }

    @Test
    fun test4() {
        val input = arrayOf(intArrayOf(15, 18))
        val expectedResult = arrayOf(intArrayOf(15, 18))
        Assertions.assertArrayEquals(expectedResult, task.merge(input))
    }

    @Test
    fun test5() {
        val input = arrayOf(intArrayOf(1, 4), intArrayOf(2, 3))
        val expectedResult = arrayOf(intArrayOf(1, 4))
        Assertions.assertArrayEquals(expectedResult, task.merge(input))
    }
}