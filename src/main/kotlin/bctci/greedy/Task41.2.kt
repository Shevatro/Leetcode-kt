package bctci.greedy

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Task41_2 {

    fun canTimeTravelerReturn(jumpingPoints: IntArray, k: Int, maxAging: Int): Boolean {
        val returnTime = jumpingPoints.last() - jumpingPoints.first()
        //if we can just pass the time, we don't need to jump
        if (maxAging >= returnTime) return true
        var jumpTime = returnTime - maxAging
        val jumpGaps = jumpingPoints.toList().zipWithNext { a, b -> b - a }.sortedDescending()
        repeat(k) { i ->
            jumpTime -= jumpGaps[i]
            if (jumpTime <= 0) return true
        }
        return false
    }
}


private class Task41_2Test {
    private val task = Task41_2()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input1: IntArray, input2: Int, input3: Int, expected: Boolean) {
        Assertions.assertEquals(expected, task.canTimeTravelerReturn(input1, input2, input3))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(intArrayOf(2020, 2024), 0, 3, false),
                Arguments.of(intArrayOf(2020, 2024), 1, 1, true),
                Arguments.of(intArrayOf(1803, 1861, 1863, 1865, 1920, 1929, 1941, 1964, 2001, 2021), 4, 45, true),
                Arguments.of(intArrayOf(1803, 1861, 1863, 1865, 1920, 1929, 1941, 1962, 2000, 2021), 4, 45, false),
                Arguments.of(intArrayOf(2000, 2001, 2002), 0, 2, true),
                Arguments.of(intArrayOf(2000, 2005, 2010), 0, 4, false)
            )
        }
    }
}