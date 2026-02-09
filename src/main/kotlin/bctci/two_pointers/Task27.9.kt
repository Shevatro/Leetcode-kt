package bctci.two_pointers

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Task27_9 {
    fun sortValleyShapedArray(arr: IntArray): IntArray {
        val result = IntArray(arr.size)
        var startPos = 0
        var endPos = arr.lastIndex
        for (k in arr.lastIndex downTo 0) {
            if (arr[startPos] > arr[endPos]) {
                result[k] = arr[startPos]
                startPos++
            } else {
                result[k] = arr[endPos]
                endPos--
            }
        }
        return result
    }
}


private class Task27_9Test {
    private val task = Task27_9()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input: IntArray, expected: IntArray) {
        Assertions.assertArrayEquals(expected, task.sortValleyShapedArray(input))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(intArrayOf(8, 4, 2, 6), intArrayOf(2, 4, 6, 8)),
                Arguments.of(intArrayOf(1, 2), intArrayOf(1, 2)),
                Arguments.of(intArrayOf(2, 2, 1, 1), intArrayOf(1, 1, 2, 2)),
                Arguments.of(intArrayOf(), intArrayOf()),
                Arguments.of(intArrayOf(1), intArrayOf(1)),
                Arguments.of(intArrayOf(3, 2, 1, 4), intArrayOf(1, 2, 3, 4)),
                Arguments.of(intArrayOf(5, 4, 3, 2, 1, 2, 3), intArrayOf(1, 2, 2, 3, 3, 4, 5)),
                Arguments.of(intArrayOf(1, 1, 1, 1), intArrayOf(1, 1, 1, 1))
            )
        }
    }
}