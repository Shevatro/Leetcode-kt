package lc1_999.lc1_99

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import toString2

//From Cracking The Coding Interview, Not Solved, Repeat
//https://leetcode.com/problems/set-matrix-zeroes/
class Task73 {
    fun setZeroes(matrix: Array<IntArray>): Unit {
        val hasFirstRowZero = hasFirstRowZero(matrix[0])
        val hasFirstColumnZero = hasFirstColumnZero(matrix)
        setZeroesAtFirstPosition(matrix)
        setOtherZeroes(matrix)
        if (hasFirstRowZero) setZerosForFirstRow(matrix)
        if (hasFirstColumnZero) setZerosForFirstColumn(matrix)
    }

    private fun hasFirstRowZero(row: IntArray): Boolean {
        return row.any { it == 0 }
    }

    private fun hasFirstColumnZero(matrix: Array<IntArray>): Boolean {
        for (i in matrix.indices) {
            if (matrix[i][0] == 0) {
                return true
            }
        }
        return false
    }

    private fun setZeroesAtFirstPosition(matrix: Array<IntArray>) {
        for (i in 1 until matrix.size) {
            for (j in 1 until matrix[0].size) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0
                    matrix[i][0] = 0
                }
            }
        }
    }

    private fun setOtherZeroes(matrix: Array<IntArray>) {
        for (i in matrix.lastIndex downTo 1) {
            for (j in matrix[0].lastIndex downTo 1) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0
                }
            }
        }
    }

    private fun setZerosForFirstRow(matrix: Array<IntArray>) {
        for (i in matrix[0].indices) {
            matrix[0][i] = 0
        }
    }

    private fun setZerosForFirstColumn(matrix: Array<IntArray>) {
        for (i in matrix.indices) {
            matrix[i][0] = 0
        }
    }
}

private class Task73Test {
    private val task = Task73()

    @Test
    fun setZeroes() {
        setZeroes(
            arrayOf(intArrayOf(1, 1, 1), intArrayOf(1, 0, 1), intArrayOf(1, 1, 1)),
            arrayOf(intArrayOf(1, 0, 1), intArrayOf(0, 0, 0), intArrayOf(1, 0, 1))
        )
        setZeroes(
            arrayOf(intArrayOf(0, 1, 2, 0), intArrayOf(3, 4, 5, 2), intArrayOf(1, 3, 1, 5)),
            arrayOf(intArrayOf(0, 0, 0, 0), intArrayOf(0, 4, 5, 0), intArrayOf(0, 3, 1, 0))
        )
        setZeroes(
            arrayOf(intArrayOf(1, 1, 2, 6), intArrayOf(0, 4, 5, 0), intArrayOf(1, 3, 1, 5)),
            arrayOf(intArrayOf(0, 1, 2, 0), intArrayOf(0, 0, 0, 0), intArrayOf(0, 3, 1, 0))
        )
    }

    private fun setZeroes(actualInp: Array<IntArray>, expected: Array<IntArray>) {
        task.setZeroes(actualInp)
        println(actualInp.toString2())
        Assertions.assertArrayEquals(expected, actualInp)
    }
}