package lc1_999.lc400_499

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.math.max
import kotlin.math.min

//Similar to Beyond Cracking The Coding Interview, Solved
//https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/description/
class Task452 {
    fun findMinArrowShots(points: Array<IntArray>): Int {
        points.sortBy { it[0] }
        var startPos = points[0][0]
        var endPos = points[0][1]
        var count = 1
        for (i in 1 until points.size) {
            val curInt = points[i][0]..points[i][1]
            if (curInt.first in startPos..endPos) {
                startPos = max(startPos, curInt.first)
                endPos = min(endPos, curInt.last)
            } else {
                count++
                startPos = curInt.first
                endPos = curInt.last
            }
        }
        return count
    }
}

private class Task452Test {
    private val task = Task452()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input: Array<IntArray>, expected: Int) {
        Assertions.assertEquals(expected, task.findMinArrowShots(input))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(arrayOf(intArrayOf(10, 16), intArrayOf(2, 8), intArrayOf(1, 6), intArrayOf(7, 12)), 2),
                Arguments.of(arrayOf(intArrayOf(1, 2), intArrayOf(3, 4), intArrayOf(5, 6), intArrayOf(7, 8)), 4),
                Arguments.of(arrayOf(intArrayOf(1, 2), intArrayOf(2, 3), intArrayOf(3, 4), intArrayOf(4, 5)), 2),
                Arguments.of(arrayOf(intArrayOf(1, 2), intArrayOf(3, 4), intArrayOf(3, 4), intArrayOf(5, 6), intArrayOf(7, 8)), 4),
                Arguments.of(arrayOf(intArrayOf(1, 12)), 1),
                Arguments.of(arrayOf(intArrayOf(1, 2), intArrayOf(2, 3), intArrayOf(2, 3)), 1),
                Arguments.of(arrayOf(intArrayOf(1, 2147483647)), 1),
                Arguments.of(
                    arrayOf(
                        intArrayOf(3, 9), intArrayOf(7, 12), intArrayOf(3, 8), intArrayOf(6, 8), intArrayOf(9, 10), intArrayOf(2, 9),
                        intArrayOf(0, 9), intArrayOf(3, 9), intArrayOf(0, 6), intArrayOf(2, 8)
                    ), 2
                )
            )
        }
    }
}