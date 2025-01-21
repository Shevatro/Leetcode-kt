package lc1_999.lc200_299

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

//From Cracking The Coding Interview, Solved
//https://leetcode.com/problems/search-a-2d-matrix-ii/
class Task240 {
    fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
        for (midI in matrix.indices) {
            var startJ = 0
            var endJ = matrix[0].size - 1
            while (startJ <= endJ) {
                val midJ = (startJ + endJ) / 2
                if (matrix[midI][midJ] == target) return true
                if (matrix[midI][midJ] > target) {
                    endJ = midJ - 1
                } else {
                    startJ = midJ + 1
                }
            }
        }
        return false
    }
}

private class Task240Test {
    private val task = Task240()

    @Test
    fun test1() {
        val input = arrayOf(
            intArrayOf(1, 4, 7, 11, 15), intArrayOf(2, 5, 8, 12, 19), intArrayOf(3, 6, 9, 16, 22),
            intArrayOf(10, 13, 14, 17, 24), intArrayOf(18, 21, 23, 26, 30)
        )
        val actualResult = task.searchMatrix(input, 5)
        Assertions.assertEquals(true, actualResult)
    }

    @Test
    fun test2() {
        val input = arrayOf(
            intArrayOf(1, 4, 7, 11, 15), intArrayOf(2, 5, 8, 12, 19), intArrayOf(3, 6, 9, 16, 22),
            intArrayOf(10, 13, 14, 17, 24), intArrayOf(18, 21, 23, 26, 30)
        )
        val actualResult = task.searchMatrix(input, 20)
        Assertions.assertEquals(false, actualResult)
    }

    @Test
    fun test3() {
        val input = arrayOf(intArrayOf(-1, 3))
        val actualResult = task.searchMatrix(input, 3)
        Assertions.assertEquals(true, actualResult)
    }

    @Test
    fun test4() {
        val input = arrayOf(
            intArrayOf(1, 4, 7, 11, 15), intArrayOf(2, 5, 8, 12, 19), intArrayOf(3, 6, 9, 16, 22),
            intArrayOf(10, 13, 14, 17, 24), intArrayOf(18, 21, 23, 26, 30)
        )
        val actualResult = task.searchMatrix(input, 8)
        Assertions.assertEquals(true, actualResult)
    }
}