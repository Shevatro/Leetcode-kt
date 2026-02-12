package bctci.two_pointers

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.collections.toList

class Task27_15 {

    fun quicksortPartition(arr: IntArray, pivot: Int): IntArray {
        return Solution(arr, pivot).quicksortPartition()
    }

    private class Solution(
        private val arr: IntArray,
        private val pivot: Int
    ) {
        fun quicksortPartition(): IntArray {
            applyPartitionSorting()
            fixDuplicatesPositions()
            return arr
        }

        private fun applyPartitionSorting() {
            var startPos = 0
            var endPos = arr.lastIndex
            while (startPos < endPos) {
                if (arr[startPos] <= pivot) {
                    startPos++
                    continue
                }
                if (arr[endPos] > pivot) {
                    endPos--
                    continue
                }
                arr[startPos] = arr[endPos].also { arr[endPos] = arr[startPos] }
                startPos++
                endPos--
            }
        }

        private fun findSmallestItemPosInPart2(): Int {
            //if all items are greater than pivot => do not waste time
            if (arr.isEmpty() || arr[0] > pivot) return -1

            for (i in arr.lastIndex downTo 0) {
                if (arr[i] <= pivot) return i
            }
            return -1
        }

        //if we have [1, 3, 2, 3, 3, 5, 7] where pivot=3, we need to change it to [1, 2, 3, 3, 3, 5, 7]
        private fun fixDuplicatesPositions() {
            var startPos = 0
            var endPos = findSmallestItemPosInPart2()
            if (endPos == -1) return
            while (startPos < endPos) {
                if (arr[startPos] < pivot) {
                    startPos++
                    continue
                }
                if (arr[endPos] == pivot) {
                    endPos--
                    continue
                }
                arr[startPos] = arr[endPos].also { arr[endPos] = arr[startPos] }
                startPos++
                endPos--
            }
        }
    }
}


private class Task27_15Test {
    private val task = Task27_15()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input1: IntArray, input2: Int, expected: List<IntArray>) {
        val actual = task.quicksortPartition(input1, input2)
        val isActualCorrect = actual.toList() in expected.map { it.toList() }
        Assertions.assertEquals(true, isActualCorrect)
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    intArrayOf(1, 7, 2, 3, 3, 5, 3), 4,
                    listOf(intArrayOf(1, 2, 3, 3, 3, 7, 5), intArrayOf(1, 3, 2, 3, 3, 5, 7))
                ),
                Arguments.of(
                    intArrayOf(1, 7, 2, 3, 3, 5, 3), 3,
                    listOf(intArrayOf(1, 2, 3, 3, 3, 7, 5), intArrayOf(1, 2, 3, 3, 3, 5, 7))
                ),
                Arguments.of(intArrayOf(3, 3, 3, 3), 3, listOf(intArrayOf(3, 3, 3, 3))),
                Arguments.of(intArrayOf(1, 2, 4, 5), 3, listOf(intArrayOf(1, 2, 4, 5))),
                Arguments.of(intArrayOf(1, 2), 3, listOf(intArrayOf(1, 2))),
                Arguments.of(intArrayOf(4, 5), 3, listOf(intArrayOf(4, 5))),
                Arguments.of(intArrayOf(), 1, listOf(intArrayOf())),
                Arguments.of(intArrayOf(1), 1, listOf(intArrayOf(1))),
                Arguments.of(
                    intArrayOf(3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5), 3,
                    listOf(intArrayOf(2, 1, 1, 3, 3, 9, 5, 6, 5, 4, 5))
                ),
                Arguments.of(intArrayOf(1, 2, 3), 4, listOf(intArrayOf(1, 2, 3))),
                Arguments.of(intArrayOf(5, 6, 7), 4, listOf(intArrayOf(5, 6, 7)))
            )
        }
    }
}