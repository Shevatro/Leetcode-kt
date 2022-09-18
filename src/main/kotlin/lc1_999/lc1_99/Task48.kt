package lc1_999.lc1_99

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import toString2

//From Cracking The Coding Interview, Solved but Not in Place
//https://leetcode.com/problems/rotate-image/

class Task48 {
    fun rotate(matrix: Array<IntArray>): Unit {
        val newMatrix = rotateInNewMatrix(matrix)
        copy(newMatrix, matrix)
    }

    private fun rotateInNewMatrix(matrix: Array<IntArray>): Array<IntArray> {
        val newMatrix = Array(matrix.size) { IntArray(matrix[0].size) }
        var k = 0
        for (i in matrix.lastIndex downTo 0) {
            for (j in matrix[0].indices) {
                newMatrix[j][k] = matrix[i][j]
            }
            k++
        }
        return newMatrix
    }

    private fun copy(matrixFrom: Array<IntArray>, matrixTo: Array<IntArray>) {
        for (i in matrixFrom.indices) {
            for (j in matrixFrom[0].indices) {
                matrixTo[i][j] = matrixFrom[i][j]
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