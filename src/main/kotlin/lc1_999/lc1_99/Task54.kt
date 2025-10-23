package lc1_999.lc1_99

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

// Solved
//https://leetcode.com/problems/spiral-matrix/
class Task54() {
    fun spiralOrder(matrix: Array<IntArray>): List<Int> {
        return Solution(matrix).spiralOrder()
    }

    class Solution(private val matrix: Array<IntArray>) {
        private var i = 0
        private var j = 0
        private var iTimes = matrix.size - 1
        private var jTimes = matrix[0].size
        private var count = 0
        private var direction = 0
        private val allDirections = listOf(0 to 1, 1 to 0, 0 to -1, -1 to 0)
        fun spiralOrder(): List<Int> {
            val result = mutableListOf<Int>()
            val expectedSize = matrix.size * matrix[0].size

            while (result.size != expectedSize) {
                result.add(matrix[i][j])
                count++
                shift()
            }
            return result
        }

        private fun shift() {
            val isRightOrLeft = direction == 0 || direction == 2
            val shouldChangeDirection = if (isRightOrLeft) count == jTimes else count == iTimes
            if (shouldChangeDirection) {
                direction++
                if (direction == 4) direction = 0
                count = 0
                if (isRightOrLeft) jTimes-- else iTimes--
            }
            val (iDiff, jDiff) = allDirections[direction]
            i += iDiff
            j += jDiff
        }
    }
}

private class Task54Test {
    private val task = Task54()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(brackets: Array<IntArray>, expected: List<Int>) {
        Assertions.assertEquals(expected, task.spiralOrder(brackets))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    arrayOf(intArrayOf(1, 2, 3), intArrayOf(4, 5, 6), intArrayOf(7, 8, 9)),
                    listOf(1, 2, 3, 6, 9, 8, 7, 4, 5)
                ),
                Arguments.of(
                    arrayOf(intArrayOf(1, 2, 3, 4), intArrayOf(5, 6, 7, 8), intArrayOf(9, 10, 11, 12)),
                    listOf(1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7)
                ),
                Arguments.of(
                    arrayOf(
                        intArrayOf(1, 2, 3, 4), intArrayOf(5, 6, 7, 8), intArrayOf(9, 10, 11, 12),
                        intArrayOf(13, 14, 15, 16)
                    ), listOf(1, 2, 3, 4, 8, 12, 16, 15, 14, 13, 9, 5, 6, 7, 11, 10)
                )
            )
        }
    }
}