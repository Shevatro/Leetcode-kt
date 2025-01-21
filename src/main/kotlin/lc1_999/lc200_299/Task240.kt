package lc1_999.lc200_299

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

//From Cracking The Coding Interview, Solved
//https://leetcode.com/problems/search-a-2d-matrix-ii/
class Task240 {
    fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
        var i = 0
        var j = matrix[0].size - 1
        while (i < matrix.size && j >= 0) {
            if (matrix[i][j] == target) return true
            if (matrix[i][j] > target) {
                j--
            } else {
                i++
            }
        }
        return false
    }

    fun searchMatrixHalfBSearch(matrix: Array<IntArray>, target: Int): Boolean {
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

    @Test
    fun test5() {
        val input = arrayOf(intArrayOf(-5))
        val actualResult = task.searchMatrix(input, -2)
        Assertions.assertEquals(false, actualResult)
    }

    @Test
    fun test6() {
        val input = arrayOf(intArrayOf(1, 1))
        val actualResult = task.searchMatrix(input, 0)
        Assertions.assertEquals(false, actualResult)
    }
}