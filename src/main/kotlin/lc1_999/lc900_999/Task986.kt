package lc1_999.lc900_999

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.math.max
import kotlin.math.min

//From Beyond Cracking The Coding Interview, Solved
//https://leetcode.com/problems/interval-list-intersections/
class Task986 {
    fun intervalIntersection(firstList: Array<IntArray>, secondList: Array<IntArray>): Array<IntArray> {
        val result = mutableListOf<IntArray>()
        var firstPos = 0
        var secondPos = 0
        while (firstPos < firstList.size && secondPos < secondList.size) {
            val firstItem = firstList[firstPos]
            val secondItem = secondList[secondPos]
            val start = max(firstItem[0], secondItem[0])
            val end = min(firstItem[1], secondItem[1])
            // if a new interval is valid
            if (start <= end) result.add(intArrayOf(start, end))
            //move a pointer base on the smaller ending
            if (firstItem[1] < secondItem[1]) firstPos++ else secondPos++
        }
        return result.toTypedArray()
    }
}

private class Task986Test {
    private val task = Task986()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input1: Array<IntArray>, input2: Array<IntArray>, expected: Array<IntArray>) {
        Assertions.assertArrayEquals(expected, task.intervalIntersection(input1, input2))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    arrayOf(intArrayOf(0, 2), intArrayOf(5, 10), intArrayOf(13, 23), intArrayOf(24, 25)),
                    arrayOf(intArrayOf(1, 5), intArrayOf(8, 12), intArrayOf(15, 24), intArrayOf(25, 26)),
                    arrayOf(intArrayOf(1, 2), intArrayOf(5, 5), intArrayOf(8, 10), intArrayOf(15, 23), intArrayOf(24, 24), intArrayOf(25, 25))
                ),
                Arguments.of(arrayOf(intArrayOf(1, 3), intArrayOf(5, 9)), emptyArray<IntArray>(), emptyArray<IntArray>())
            )
        }
    }
}