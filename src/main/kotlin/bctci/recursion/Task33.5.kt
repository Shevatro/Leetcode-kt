package bctci.recursion

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Task33_5() {
    fun calculateMaxLaminalArray(arr: IntArray): Long {
        return Solution(arr).calculateMaxLaminalArray()
    }

    class Solution(private val arr: IntArray) {
        private var max = Long.MIN_VALUE
        fun calculateMaxLaminalArray(): Long {
            calculateMaxLaminalArrayRec(0, arr.lastIndex)
            return max
        }

        private fun calculateMaxLaminalArrayRec(startPos: Int, endPos: Int): Long {
            val sum = if (startPos == endPos) {
                arr[startPos].toLong()
            } else {
                val half = (startPos + endPos) / 2
                calculateMaxLaminalArrayRec(startPos, half) + calculateMaxLaminalArrayRec(half + 1, endPos)
            }
            if (sum > max) max = sum
            return sum
        }
    }
}


private class Task33_5Test {
    private val task = Task33_5()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input: IntArray, expected: Long) {
        Assertions.assertEquals(expected, task.calculateMaxLaminalArray(input))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(intArrayOf(3, -9, 2, 4, -1, 5, 5, -4), 6),
                Arguments.of(intArrayOf(1), 1),
                Arguments.of(intArrayOf(-1, -2), -1),
                Arguments.of(intArrayOf(1, 2, 3, 4), 10),
                Arguments.of(intArrayOf(-2, -1, -4, -3), -1),
                Arguments.of(
                    intArrayOf(
                        1, -2, 3, -4, 5, -6, 7, -8, 9, -10, 11, -
                        12, 13, -14, 15, -16
                    ), 15
                ),
            )
        }
    }
}