package bctci.two_pointers

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Task27_3 {
    fun intersect(arr1: IntArray, arr2: IntArray): List<Int> {
        val result = mutableListOf<Int>()
        var p1 = 0
        var p2 = 0
        while (p1 < arr1.size && p2 < arr2.size) {
            if (arr1[p1] == arr2[p2]) {
                result.add(arr1[p1])
                p1++
                p2++
            } else if (arr1[p1] < arr2[p2]) {
                p1++
            } else {
                p2++
            }
        }
        return result
    }
}


private class Task27_3Test {
    private val task = Task27_3()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input1: IntArray, input2: IntArray, expected: List<Int>) {
        Assertions.assertEquals(expected, task.intersect(input1, input2))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(intArrayOf(1, 2, 3), intArrayOf(1, 3, 5), listOf(1, 3)),
                Arguments.of(intArrayOf(1, 1, 1), intArrayOf(1, 1), listOf(1, 1)),
                Arguments.of(intArrayOf(), intArrayOf(), emptyList<Int>()),
                Arguments.of(intArrayOf(1), intArrayOf(), emptyList<Int>()),
                Arguments.of(intArrayOf(), intArrayOf(1), emptyList<Int>()),
                Arguments.of(intArrayOf(1, 2, 3), intArrayOf(4, 5, 6), emptyList<Int>()),
                Arguments.of(intArrayOf(1, 2, 2, 3), intArrayOf(2, 2, 3), listOf(2, 2, 3))
            )
        }
    }
}