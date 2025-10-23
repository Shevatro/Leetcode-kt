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
        val result = mutableListOf<Int>()
        var iCur = 0
        var jCur = 0
        var iTimes = matrix.size - 1
        var jTimes = matrix[0].size
        val size = matrix.size * matrix[0].size
        val directions = listOf(0 to 1, 1 to 0, 0 to -1, -1 to 0)
        var curDirection = 0
        result.add(matrix[iCur][jCur])
        while (result.size != size) {
            val (iDiff, jDiff) = directions[curDirection]
            if (curDirection == 0 || curDirection == 2) {
                repeat(jTimes) {
                    if (jCur + jDiff < matrix[0].size) {
                        iCur += iDiff
                        jCur += jDiff
                        result.add(matrix[iCur][jCur])
                    }
                }
                jTimes--
            } else {
                repeat(iTimes) {
                    iCur += iDiff
                    jCur += jDiff
                    result.add(matrix[iCur][jCur])
                }
                iTimes--
            }
            curDirection++
            if (curDirection == 4) curDirection = 0
        }
        return result
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