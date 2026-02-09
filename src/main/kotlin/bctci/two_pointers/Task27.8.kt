package bctci.two_pointers

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Task27_8 {
    fun threeWayMergeWithoutDuplicates(arr1: IntArray, arr2: IntArray, arr3: IntArray): IntArray {
        return Solution(arr1, arr2, arr3).threeWayMergeWithoutDuplicates()
    }

    private class Solution(
        private val arr1: IntArray,
        private val arr2: IntArray,
        private val arr3: IntArray
    ) {
        private val result = mutableListOf<Int>()
        private var previousItem = -1
        private var p1 = 0
        private var p2 = 0
        private var p3 = 0
        fun threeWayMergeWithoutDuplicates(): IntArray {
            while (p1 < arr1.size || p2 < arr2.size || p3 < arr3.size) {
                val items = createList()
                val (min, index) = items.minBy { it.first }
                addIntoResult(min)
                when (index) {
                    0 -> p1++
                    1 -> p2++
                    2 -> p3++
                }
            }
            return result.toIntArray()
        }

        private fun addIntoResult(item: Int) {
            if (item != previousItem) {
                result.add(item)
                previousItem = item
            }
        }

        //item and position
        private fun createList(): List<Pair<Int, Int>> {
            val list = mutableListOf<Pair<Int, Int>>()
            if (p1 < arr1.size) {
                list.add(arr1[p1] to 0)
            }
            if (p2 < arr2.size) {
                list.add(arr2[p2] to 1)
            }
            if (p3 < arr3.size) {
                list.add(arr3[p3] to 2)
            }
            return list
        }
    }

    fun threeWayMergeWithoutDuplicatesSimplified(arr1: IntArray, arr2: IntArray, arr3: IntArray): IntArray {
        return arr1.union(arr2.toSet()).union(arr3.toSet()).toIntArray()
    }
}


private class Task27_8Test {
    private val task = Task27_8()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input1: IntArray, input2: IntArray, input3: IntArray, expected: IntArray) {
        Assertions.assertArrayEquals(expected, task.threeWayMergeWithoutDuplicates(input1, input2, input3))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    intArrayOf(2, 3, 3, 4, 5, 7), intArrayOf(3, 3, 9), intArrayOf(3, 3, 9),
                    intArrayOf(2, 3, 4, 5, 7, 9)
                ),
                Arguments.of(intArrayOf(), intArrayOf(), intArrayOf(), intArrayOf()),
                Arguments.of(intArrayOf(1), intArrayOf(), intArrayOf(), intArrayOf(1)),
                Arguments.of(intArrayOf(1), intArrayOf(1), intArrayOf(1), intArrayOf(1)),
                Arguments.of(intArrayOf(1, 2, 3), intArrayOf(2, 3, 4), intArrayOf(3, 4, 5), intArrayOf(1, 2, 3, 4, 5)),
                Arguments.of(intArrayOf(1, 1, 1), intArrayOf(1, 1), intArrayOf(1), intArrayOf(1)),
                Arguments.of(
                    intArrayOf(1, 2, 3), intArrayOf(4, 5, 6), intArrayOf(7, 8, 9),
                    intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
                )
            )
        }
    }
}