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
    private val monthLength = intArrayOf(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)

    //[0] January = 31, [1] Feb = 31+28=59 and so on
    private val daysInMonthCache = IntArray(12) { -1 }
    fun countDaysTogether(arriveAlice: String, leaveAlice: String, arriveBob: String, leaveBob: String): Int {
        val aliceStart = getDaysFromJanuary1(arriveAlice)
        val aliceEnd = getDaysFromJanuary1(leaveAlice)
        val bobStart = getDaysFromJanuary1(arriveBob)
        val bobEnd = getDaysFromJanuary1(leaveBob)
        val daysTogether = min(aliceEnd, bobEnd) - max(aliceStart, bobStart)
        //interval is  inclusive, so +1
        return max(0, daysTogether + 1)
    }

    private fun getDaysFromJanuary1(date: String): Int {
        //months start from 1
        val month = date.substring(0, 2).toInt() - 1
        val day = date.substring(3).toInt()
        return getDaysFromJanuary1(month) + day
    }

    private fun getDaysFromJanuary1(month: Int): Int {
        if (daysInMonthCache[month] != -1) return daysInMonthCache[month]
        var sum = 0
        for (i in 0 until month) {
            sum += monthLength[i]
        }
        daysInMonthCache[month] = sum
        return sum
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