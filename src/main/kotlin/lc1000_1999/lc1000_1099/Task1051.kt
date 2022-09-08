package lc1000_1999.lc1000_1099

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

//From a learning section, Solved
//https://leetcode.com/problems/height-checker/
class Task1051 {
    fun heightChecker(heights: IntArray): Int {
        val expected = heights.sorted()
        var amount = 0
        for (i in heights.indices) {
            if (expected[i] != heights[i]) amount++
        }
        return amount
    }
}

private class Task1051Test {
    private val task = Task1051()

    @Test
    fun heightChecker() {
        heightChecker(intArrayOf(1, 1, 4, 2, 1, 3), 3)
        heightChecker(intArrayOf(5, 1, 2, 3, 4), 5)
        heightChecker(intArrayOf(1, 2, 3, 4, 5), 0)
    }

    private fun heightChecker(actualInp: IntArray, expected: Int) {
        val actual = task.heightChecker(actualInp)
        println(actual)
        Assertions.assertEquals(expected, actual)
    }
}