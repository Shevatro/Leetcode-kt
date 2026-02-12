package bctci.two_pointers

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.collections.toList

class Task27_15 {

    fun quicksortPartition(arr: IntArray, pivot: Int): IntArray {
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
        return arr
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
                    listOf(intArrayOf(1, 2, 3, 3, 3, 7, 5), intArrayOf(1, 3, 2, 3, 3, 5, 7))
                ),
                Arguments.of(intArrayOf(3, 3, 3, 3), 3, listOf(intArrayOf(3, 3, 3, 3))),
                Arguments.of(intArrayOf(1, 2, 4, 5), 3, listOf(intArrayOf(1, 2, 4, 5))),
                Arguments.of(intArrayOf(1, 2), 3, listOf(intArrayOf(1, 2))),
                Arguments.of(intArrayOf(4, 5), 3, listOf(intArrayOf(4, 5))),
                Arguments.of(intArrayOf(), 1, listOf(intArrayOf())),
                Arguments.of(intArrayOf(1), 1, listOf(intArrayOf(1))),
                Arguments.of(
                    intArrayOf(3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5), 3,
                    listOf(intArrayOf(3, 1, 3, 1, 2, 9, 5, 6, 5, 4, 5))
                ),
                Arguments.of(intArrayOf(1, 2, 3), 4, listOf(intArrayOf(1, 2, 3))),
                Arguments.of(intArrayOf(5, 6, 7), 4, listOf(intArrayOf(5, 6, 7)))
            )
        }
    }
}