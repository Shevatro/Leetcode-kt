package lc1_999.lc1_99

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import toString2

//From Cracking The Coding Interview, Not Solved, Repeat
//https://leetcode.com/problems/rotate-image/

class Task48 {
    fun rotate(matrix: Array<IntArray>): Unit {
        transpose(matrix)
        reflect(matrix)
    }

    private fun transpose(matrix: Array<IntArray>) {
        for (i in matrix.indices) {
            for (j in i + 1 until matrix[0].size) {
                val temp = matrix[i][j]
                matrix[i][j] = matrix[j][i]
                matrix[j][i] = temp
            }
        }
    }

    private fun reflect(matrix: Array<IntArray>) {
        for (i in matrix[0].indices) {
            var k = matrix.lastIndex
            for (j in 0 until matrix.size / 2) {
                val temp = matrix[i][j]
                matrix[i][j] = matrix[i][k]
                matrix[i][k] = temp
                k--
            }
        }
    }
}

private class Task48Test {
    private val task = Task48()

    @Test
    fun rotate() {
        rotate(
            arrayOf(intArrayOf(1, 2, 3), intArrayOf(4, 5, 6), intArrayOf(7, 8, 9)),
            arrayOf(intArrayOf(7, 4, 1), intArrayOf(8, 5, 2), intArrayOf(9, 6, 3))
        )
        rotate(
            arrayOf(
                intArrayOf(5, 1, 9, 11), intArrayOf(2, 4, 8, 10), intArrayOf(13, 3, 6, 7), intArrayOf(15, 14, 12, 16)
            ), arrayOf(
                intArrayOf(15, 13, 2, 5), intArrayOf(14, 3, 4, 1), intArrayOf(12, 6, 8, 9), intArrayOf(16, 7, 10, 11)
            )
        )
    }

    private fun rotate(actualInp: Array<IntArray>, expected: Array<IntArray>) {
        task.rotate(actualInp)
        println(actualInp.toString2())
        Assertions.assertArrayEquals(expected, actualInp)
    }
}