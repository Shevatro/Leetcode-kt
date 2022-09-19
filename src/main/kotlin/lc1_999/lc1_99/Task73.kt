package lc1_999.lc1_99

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import toString2

//From Cracking The Coding Interview, Solved but Not in Place
//https://leetcode.com/problems/set-matrix-zeroes/
class Task73 {
    fun setZeroes(matrix: Array<IntArray>): Unit {
        val (rowArray, columnArray) = fillFlagsArrays(matrix)
        for (i in matrix.indices) {
            for (j in matrix[0].indices) {
                if (rowArray[i] || columnArray[j]) {
                    matrix[i][j] = 0
                }
            }
        }
    }

    private fun fillFlagsArrays(matrix: Array<IntArray>): Pair<BooleanArray, BooleanArray> {
        val rowArray = BooleanArray(matrix.size)
        val columnArray = BooleanArray(matrix[0].size)
        for (i in matrix.indices) {
            for (j in matrix[0].indices) {
                if (matrix[i][j] == 0) {
                    rowArray[i] = true
                    columnArray[j] = true
                }
            }
        }
        return rowArray to columnArray
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
    }

    private fun setZeroes(actualInp: Array<IntArray>, expected: Array<IntArray>) {
        task.setZeroes(actualInp)
        println(actualInp.toString2())
        Assertions.assertArrayEquals(expected, actualInp)
    }
}