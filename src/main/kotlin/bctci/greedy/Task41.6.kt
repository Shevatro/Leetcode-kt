package bctci.greedy

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.PriorityQueue
import java.util.stream.Stream

class Task41_6 {

    fun maxTimeWhereTimeTravelerCanGo(jumpingPoints: IntArray, k: Int, maxAging: Int): Int {
        val queue = PriorityQueue<Int>()
        var ageLeft = maxAging
        for (i in 1..jumpingPoints.lastIndex) {
            val diff = jumpingPoints[i] - jumpingPoints[i - 1]
            queue.add(diff)
            //if we reach max jumps -> we have to pass the smallest year's gap naturally
            if (queue.size > k) {
                val smallestItem = queue.poll()
                ageLeft -= smallestItem
                if (ageLeft < 0) return jumpingPoints[i - 1] + ageLeft + smallestItem
            }
        }
        return jumpingPoints.last() + ageLeft
    }

}


private class Task41_6Test {
    private val task = Task41_6()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input1: IntArray, input2: Int, input3: Int, expected: Int) {
        Assertions.assertEquals(expected, task.maxTimeWhereTimeTravelerCanGo(input1, input2, input3))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(intArrayOf(2020, 2024), 0, 2, 2022),
                Arguments.of(intArrayOf(2020, 2024), 1, 1, 2025),
                Arguments.of(intArrayOf(1803, 1861, 1863, 1865, 1920, 1929, 1941, 1964, 2001, 2021), 4, 45, 2021),
                Arguments.of(intArrayOf(1, 10, 30), 1, 5, 15),
                Arguments.of(intArrayOf(1, 3, 6, 7, 11, 16, 17, 19), 2, 4, 12),
                Arguments.of(intArrayOf(1803, 1861, 1863, 1865, 1920, 1929, 1941, 1962, 2000, 2021), 4, 45, 2020),
                Arguments.of(intArrayOf(2000, 2001, 2002), 0, 2, 2002),
                Arguments.of(intArrayOf(2000, 2005, 2010), 0, 4, 2004)
            )
        }
    }
}