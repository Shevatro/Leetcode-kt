package bctci.two_pointers

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Task27_7 {
    fun twoSumSorted(arr: IntArray): Boolean {
        var startPos = 0
        var endPos = arr.lastIndex
        while (startPos < endPos) {
            val sum = arr[startPos] + arr[endPos]
            when {
                sum == 0 -> return true
                sum < 0 -> startPos++
                else -> endPos--
            }
        }
        return false
    }
}


private class Task27_7Test {
    private val task = Task27_7()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input: IntArray, expected: Boolean) {
        Assertions.assertEquals(expected, task.twoSumSorted(input))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(intArrayOf(-5, -2, -1, 1, 1, 10), true),
                Arguments.of(intArrayOf(-3, 0, 0, 1, 2), true),
                Arguments.of(intArrayOf(-5, -3, -1, 0, 2, 4, 6), false),
                Arguments.of(intArrayOf(), false),
                Arguments.of(intArrayOf(0), false),
                Arguments.of(intArrayOf(-1, 1), true),
                Arguments.of(intArrayOf(-2, -1, 0, 1), true),
                Arguments.of(intArrayOf(1, 2, 3, 4), false)
            )
        }
    }
}