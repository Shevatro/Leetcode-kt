package lc2000_2999.lc2400_2499

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.math.min
import kotlin.math.max

// Solved
//https://leetcode.com/problems/count-days-spent-together/description/
class Task2409 {
    fun countDaysTogether(arriveAlice: String, leaveAlice: String, arriveBob: String, leaveBob: String): Int {
        return Solution(arriveAlice, leaveAlice, arriveBob, leaveBob).countDaysTogether()
    }

    private class Solution(
        private val arriveAlice: String,
        private val leaveAlice: String,
        private val arriveBob: String,
        private val leaveBob: String
    ) {
        private val monthLength = intArrayOf(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
        fun countDaysTogether(): Int {
            val aliceStart = convertDateStrToInt(arriveAlice)
            val aliceEnd = convertDateStrToInt(leaveAlice)
            val bobStart = convertDateStrToInt(arriveBob)
            val bobEnd = convertDateStrToInt(leaveBob)
            val diff = min(aliceEnd, bobEnd) - max(aliceStart, bobStart)
            return if (diff < 0) 0 else diff + 1
        }

        private fun convertDateStrToInt(date: String): Int {
            val month = date.substring(0, 2).toInt() - 1
            val day = date.substring(3).toInt()
            var sum = 0
            for (i in 0 until month) {
                sum += monthLength[i]
            }
            return sum + day
        }
    }

}

private class Task2409Test {
    private val task = Task2409()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input1: String, input2: String, input3: String, input4: String, expected: Int) {
        Assertions.assertEquals(expected, task.countDaysTogether(input1, input2, input3, input4))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of("08-15", "08-18", "08-16", "08-19", 3),
                Arguments.of("10-01", "10-31", "11-01", "12-31", 0),
                Arguments.of("01-15", "12-18", "01-16", "11-19", 308),
                Arguments.of("08-15", "08-18", "08-15", "08-18", 4),
                Arguments.of("10-20", "12-22", "06-21", "07-05", 0)
            )
        }
    }
}