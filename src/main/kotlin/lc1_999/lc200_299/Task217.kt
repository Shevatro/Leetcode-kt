package lc1_999.lc200_299

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

//From Cracking The Coding Interview, Solved
//https://leetcode.com/problems/contains-duplicate/
class Task217 {
    fun containsDuplicate(nums: IntArray): Boolean {
        val hash = mutableSetOf<Int>()
        for (num in nums) {
            if (hash.contains(num)) {
                return true
            } else {
                hash.add(num)
            }
        }
        return false
    }

    fun containsDuplicateV2(nums: IntArray): Boolean {
        if (nums.size == 1) return false
        nums.sort()
        for (i in 1 until nums.size) {
            if (nums[i - 1] == nums[i]) {
                return true
            }
        }
        return false
    }
}

private class Task217Test {
    private val task = Task217()

    @Test
    fun containsDuplicate() {
        containsDuplicate(intArrayOf(1, 2, 3, 1), true)
        containsDuplicate(intArrayOf(1, 2, 3, 4), false)
        containsDuplicate(intArrayOf(1, 1, 1, 3, 3, 4, 3, 2, 4, 2), true)
        containsDuplicate(intArrayOf(1), false)
    }

    private fun containsDuplicate(actualInp: IntArray, expected: Boolean) {
        val actual = task.containsDuplicate(actualInp)
        println(actual)
        Assertions.assertEquals(expected, actual)
    }
}