package bctci.two_pointers

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Task27_6 {
    fun mergeTwoSortedArrays(arr1: IntArray, arr2: IntArray): IntArray {
        val result = IntArray(arr1.size + arr2.size)
        var arr1Pos = 0
        var arr2Pos = 0
        for (i in result.indices) {
            if (arr2Pos >= arr2.size || (arr1Pos < arr1.size && arr1[arr1Pos] < arr2[arr2Pos])) {
                result[i] = arr1[arr1Pos]
                arr1Pos++
            } else {
                result[i] = arr2[arr2Pos]
                arr2Pos++
            }
        }
        return result
    }
}


private class Task27_6Test {
    private val task = Task27_6()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input1: IntArray, input2: IntArray, expected: IntArray) {
        Assertions.assertArrayEquals(expected, task.mergeTwoSortedArrays(input1, input2))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(intArrayOf(1, 3, 4, 5), intArrayOf(2, 4, 4), intArrayOf(1, 2, 3, 4, 4, 4, 5)),
                Arguments.of(intArrayOf(-1), intArrayOf(), intArrayOf(-1)),
                Arguments.of(intArrayOf(), intArrayOf(), intArrayOf()),
                Arguments.of(intArrayOf(1), intArrayOf(), intArrayOf(1)),
                Arguments.of(intArrayOf(), intArrayOf(1), intArrayOf(1)),
                Arguments.of(intArrayOf(1, 3, 5), intArrayOf(2, 4, 6), intArrayOf(1, 2, 3, 4, 5, 6)),
                Arguments.of(intArrayOf(1, 1, 1), intArrayOf(1, 1, 1), intArrayOf(1, 1, 1, 1, 1, 1))
            )
        }
    }
}