package lc1_999.lc200_299

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

//From Cracking The Coding Interview, Solved
//https://leetcode.com/problems/find-the-duplicate-number/
class Task287 {

    fun findDuplicate(nums: IntArray): Int {
        nums.sort()
        var prev = nums[0]
        for (i in 1 until nums.size) {
            if (nums[i] == prev) return prev
            prev = nums[i]
        }
        return -1
    }
}

private class Task287Test {
    private val task = Task287()

    @Test
    fun test1() {
        val input = intArrayOf(1, 3, 4, 2, 2)
        val actualResult = task.findDuplicate(input)
        Assertions.assertEquals(2, actualResult)
    }

    @Test
    fun test2() {
        val input = intArrayOf(3, 1, 3, 4, 2)
        val actualResult = task.findDuplicate(input)
        Assertions.assertEquals(3, actualResult)
    }

    @Test
    fun test3() {
        val input = intArrayOf(3, 3, 3, 3, 3)
        val actualResult = task.findDuplicate(input)
        Assertions.assertEquals(3, actualResult)
    }
}