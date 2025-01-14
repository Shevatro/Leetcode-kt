package lc1_999.lc1_99

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.math.abs

//From Cracking The Coding Interview, Not Solved
//https://leetcode.com/problems/n-queens/description/
class Task51 {
    fun solveNQueens(n: Int): List<List<String>> {
        return Solution2(n).solveNQueens()
    }

    private class Solution2(
        private val n: Int
    ) {
        private val results = mutableListOf<List<String>>()
        private val columns = IntArray(n)

        fun solveNQueens(): List<List<String>> {
            placeQueens(0)
            return results
        }

        private fun placeQueens(row: Int) {
            if (row == n) { //Found valid placement
                results.add(generateResult())
            } else {
                for (col in 0 until n) {
                    if (checkValid(row, col)) {
                        columns[row] = col // Place queen
                        placeQueens(row + 1)
                    }
                }
            }
        }

        private fun generateResult(): List<String> {
            val result = mutableListOf<String>()
            for (colum in columns) {
                val sb = StringBuilder()
                for (i in 0 until n) {
                    val ch = if (i == colum) 'Q' else '.'
                    sb.append(ch)
                }
                result.add(sb.toString())
            }
            return result
        }

        private fun checkValid(row1: Int, column1: Int): Boolean {
            for (row2 in 0 until row1) {
                val column2 = columns[row2]
                /* Check if (row2, column2) invalidates (row1, column1) as a queen spot. */
                /* Check if rows have a queen in the same column */
                if (column1 == column2) {
                    return false
                }
                /* Check diagonals: if the distance between the columns equals the distance between the rows, then they're in the same diagonal. */
                val columnDistance = abs(column2 - column1)

                /* row1 > row2, so no need for abs */
                val rowDistance = row1 - row2;
                if (columnDistance == rowDistance) {
                    return false
                }
            }
            return true
        }
    }
}

private class Task51Test {
    private val task = Task51()

    @Test
    fun test1() {
        val actualResult = task.solveNQueens(4)
        val expectedResult = listOf(listOf(".Q..", "...Q", "Q...", "..Q."), listOf("..Q.", "Q...", "...Q", ".Q.."))
        Assertions.assertEquals(expectedResult, actualResult)
    }

    @Test
    fun test2() {
        val actualResult = task.solveNQueens(1)
        val expectedResult = listOf(listOf("Q"))
        Assertions.assertEquals(expectedResult, actualResult)
    }
}