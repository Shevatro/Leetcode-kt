package lc1_999.lc500_599

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

//Solved
//https://leetcode.com/problems/reshape-the-matrix/description/
class Task566 {
    fun matrixReshape(mat: Array<IntArray>, r: Int, c: Int): Array<IntArray> {
        if (r * c != mat.size * mat[0].size) return mat
        val result = Array(r) { IntArray(c) }

        var k = 0
        for (i in mat.indices) {
            for (j in mat[0].indices) {
                val curR = k / c
                val curC = k % c
                result[curR][curC] = mat[i][j]
                k++
            }
        }
        return result
    }
}

private class Task566Test {
    private val task = Task566()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input1: Array<IntArray>, input2: Int, input3: Int, expected: Array<IntArray>) {
        Assertions.assertArrayEquals(expected, task.matrixReshape(input1, input2, input3))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(arrayOf(intArrayOf(1, 2), intArrayOf(3, 4)), 1, 4, arrayOf(intArrayOf(1, 2, 3, 4))),
                Arguments.of(
                    arrayOf(intArrayOf(1, 2), intArrayOf(3, 4)), 2, 4,
                    arrayOf(intArrayOf(1, 2), intArrayOf(3, 4))
                ),
                Arguments.of(arrayOf(intArrayOf(1, 2, 3, 4)), 2, 2, arrayOf(intArrayOf(1, 2), intArrayOf(3, 4)))
            )
        }
    }
}