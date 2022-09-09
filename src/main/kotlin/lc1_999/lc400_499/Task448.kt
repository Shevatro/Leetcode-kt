package lc1_999.lc400_499

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.math.abs

//From a learning section, Solved
//https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/
class Task448 {
    fun findDisappearedNumbers(nums: IntArray): List<Int> {
        fillAppeared(nums)
        return getDisappearedList(nums)
    }

    private fun fillAppeared(nums: IntArray) {
        for (num in nums) {
            val index = abs(num) - 1
            if (nums[index] > 0) nums[index] *= -1
        }
    }

    private fun getDisappearedList(nums: IntArray): List<Int> {
        val list = mutableListOf<Int>()
        for (i in nums.indices) {
            if (nums[i] > 0) list.add(i + 1)
        }
        return list
    }
}

private class Task448Test {
    private val task = Task448()

    @Test
    fun findDisappearedNumbers() {
        findDisappearedNumbers(intArrayOf(4, 3, 2, 7, 8, 2, 3, 1), listOf(5, 6))
        findDisappearedNumbers(intArrayOf(1, 1), listOf(2))
        findDisappearedNumbers(intArrayOf(1), emptyList())
    }

    private fun findDisappearedNumbers(actualInp: IntArray, expected: List<Int>) {
        val actual = task.findDisappearedNumbers(actualInp)
        println(actual)
        Assertions.assertEquals(expected, actual)
    }
}