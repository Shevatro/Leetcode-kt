package bctci.two_pointers

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.math.max
import kotlin.math.min

class Task27_11 {

    fun findIntervalIntersection(arr1: Array<IntArray>, arr2: Array<IntArray>): List<Pair<Int, Int>> {
        val result = mutableListOf<Pair<Int, Int>>()
        var firstPos = 0
        var secondPos = 0
        while (firstPos < arr1.size && secondPos < arr2.size) {
            val firstItem = arr1[firstPos]
            val secondItem = arr2[secondPos]
            val start = max(firstItem[0], secondItem[0])
            val end = min(firstItem[1], secondItem[1])
            // if a new interval is valid
            if (start <= end) result.add(start to end)
            //move a pointer base on the smaller ending
            if (firstItem[1] < secondItem[1]) firstPos++ else secondPos++
        }
        return result
    }
}


private class Task27_11Test {
    private val task = Task27_11()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input1: Array<IntArray>, input2: Array<IntArray>, expected: List<Pair<Int, Int>>) {
        Assertions.assertEquals(expected, task.findIntervalIntersection(input1, input2))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    arrayOf(intArrayOf(0, 1), intArrayOf(4, 6), intArrayOf(7, 8)),
                    arrayOf(intArrayOf(2, 3), intArrayOf(5, 9), intArrayOf(10, 11)),
                    listOf(5 to 6, 7 to 8)
                ),
                Arguments.of(
                    arrayOf(intArrayOf(2, 4), intArrayOf(5, 8)),
                    arrayOf(intArrayOf(3, 3), intArrayOf(4, 7)),
                    listOf(3 to 3, 4 to 4, 5 to 7)
                ),
                // Empty inputs
                Arguments.of(arrayOf<IntArray>(), arrayOf<IntArray>(), emptyList<Pair<Int, Int>>()),
                // One empty input
                Arguments.of(arrayOf(intArrayOf(1, 2)), arrayOf<IntArray>(), emptyList<Pair<Int, Int>>()),
                // Overlapping intervals 1
                Arguments.of(arrayOf(intArrayOf(1, 3)), arrayOf(intArrayOf(2, 4)), listOf(2 to 3)),
                // Overlapping intervals 2
                Arguments.of(arrayOf(intArrayOf(1, 5)), arrayOf(intArrayOf(2, 3)), listOf(2 to 3)),
                // Multi-interval overlap
                Arguments.of(
                    arrayOf(intArrayOf(1, 2), intArrayOf(3, 4)), arrayOf(intArrayOf(2, 3)),
                    listOf(2 to 2, 3 to 3)
                )
            )
        }
    }
}