package lc1000_1999.lc1200_1299

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

//Solved
//https://leetcode.com/problems/intersection-of-three-sorted-arrays/description/
class Task1213 {
    fun arraysIntersection(arr1: IntArray, arr2: IntArray, arr3: IntArray): List<Int> {
        val result = mutableListOf<Int>()
        var p1 = 0
        var p2 = 0
        var p3 = 0
        while (p1 < arr1.size && p2 < arr2.size && p3 < arr3.size) {
            if (arr1[p1] == arr2[p2] && arr2[p2] == arr3[p3]) {
                result.add(arr1[p1])
                p1++
                p2++
                p3++
                //arr1 is the smallest
            } else if (arr1[p1] <= arr2[p2] && arr1[p1] <= arr3[p3]) {
                p1++
                //arr2 is the smallest
            } else if (arr2[p2] <= arr1[p1] && arr2[p2] <= arr3[p3]) {
                p2++
            } else {
                p3++
            }
        }
        return result
    }

    fun arraysIntersectionSimplified(arr1: IntArray, arr2: IntArray, arr3: IntArray): List<Int> {
        return arr1.intersect(arr2.toSet()).intersect(arr3.toSet()).toList()
    }
}

private class Task1213Test {
    private val task = Task1213()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input1: IntArray, input2: IntArray, input3: IntArray, expected: List<Int>) {
        Assertions.assertEquals(expected, task.arraysIntersection(input1, input2, input3))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(intArrayOf(1, 2, 3, 4, 5), intArrayOf(1, 2, 5, 7, 9), intArrayOf(1, 3, 4, 5, 8), listOf(1, 5)),
                Arguments.of(
                    intArrayOf(197, 418, 523, 876, 1356), intArrayOf(501, 880, 1593, 1710, 1870),
                    intArrayOf(521, 682, 1337, 1395, 1764), emptyList<Int>()
                )
            )
        }
    }
}