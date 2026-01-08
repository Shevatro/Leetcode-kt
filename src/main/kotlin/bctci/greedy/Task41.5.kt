package bctci.greedy

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.math.min

class Task41_5 {

    fun minScriptRuns(meetings: Array<IntArray>): Int {
        if (meetings.isEmpty()) return 0
        meetings.sortBy { it[0] }
        var endPos = meetings[0][1]
        var count = 1
        for (i in 1 until meetings.size) {
            //if a new interval overlaps the previous one
            if (meetings[i][0] <= endPos) {
                //narrow down the current interval, ex. [1, 5], [2, 6] -> [2, 5]
                endPos = min(endPos, meetings[i][1])
            } else {
                count++
                endPos = meetings[i][1]
            }
        }
        return count
    }
}


private class Task41_5Test {
    private val task = Task41_5()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input: Array<IntArray>, expected: Int) {
        Assertions.assertEquals(expected, task.minScriptRuns(input))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    arrayOf(intArrayOf(2, 3), intArrayOf(1, 4), intArrayOf(2, 3), intArrayOf(3, 6), intArrayOf(8, 10)), 2
                ),
                Arguments.of(
                    arrayOf(
                        intArrayOf(1, 3), intArrayOf(2, 5), intArrayOf(3, 6), intArrayOf(4, 7), intArrayOf(5, 8), intArrayOf(7, 9)
                    ), 2
                ),
                Arguments.of(emptyArray<IntArray>(), 0),
                //All meetings overlap
                Arguments.of(arrayOf(intArrayOf(1, 5), intArrayOf(2, 6), intArrayOf(3, 7)), 1),
                //Non-overlapping meetings
                Arguments.of(arrayOf(intArrayOf(1, 2), intArrayOf(3, 4), intArrayOf(5, 6)), 3),
                Arguments.of(arrayOf(intArrayOf(1, 2)), 1),
            )
        }
    }
}