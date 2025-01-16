package lc1_999.lc1_99

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

//From Cracking The Coding Interview, Solved
//https://leetcode.com/problems/search-in-rotated-sorted-array-ii/
class Task81 {

    fun search(nums: IntArray, target: Int): Boolean {
        var start = 0
        var end = nums.size - 1
        while (start <= end) {
            if (nums[start] == target || nums[end] == target) return true
            start++
            end--
        }
        return false
    }
}

private class Task81Test {
    private val task = Task81()

    @Test
    fun test1() {
        val input = intArrayOf(2, 5, 6, 0, 0, 1, 2)
        val actualResult = task.search(input, 0)
        Assertions.assertEquals(true, actualResult)
    }

    @Test
    fun test2() {
        val input = intArrayOf(2, 5, 6, 0, 0, 1, 2)
        val actualResult = task.search(input, 3)
        Assertions.assertEquals(false, actualResult)
    }

    @Test
    fun test3() {
        val input = intArrayOf(2)
        val actualResult = task.search(input, 2)
        Assertions.assertEquals(true, actualResult)
    }

    @Test
    fun test4() {
        val input = intArrayOf(2)
        val actualResult = task.search(input, 3)
        Assertions.assertEquals(false, actualResult)
    }
}