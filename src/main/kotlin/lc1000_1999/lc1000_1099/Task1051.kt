package lc1000_1999.lc1000_1099

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

//From a learning section, Solved (Optimization needs to repeat)
//https://leetcode.com/problems/height-checker/
class Task1051 {
    fun heightChecker(heights: IntArray): Int {
        val buckets = generateBuckets(heights)
        var amount = 0
        var currentBucket = 0
        for (item in heights) {
            while (buckets[currentBucket] == 0) {
                currentBucket++
            }
            if (currentBucket != item) {
                amount++
            }
            buckets[currentBucket]--
        }
        return amount
    }

    private fun generateBuckets(heights: IntArray): IntArray {
        val buckets = IntArray(101)
        for (item in heights) {
            buckets[item]++
        }
        return buckets
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