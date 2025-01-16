package lc1_999.lc200_299

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

//From Cracking The Coding Interview, Solved
//https://leetcode.com/problems/missing-number/description/
class Task268 {

    fun missingNumber(nums: IntArray): Int {
        nums.sort()
        var prev = -1
        for (cur in nums) {
            if (cur - prev != 1) return cur - 1
            prev = cur
        }
        return nums.size
    }
}

private class Task268Test {
    private val task = Task268()

    @Test
    fun test1() {
        val input = intArrayOf(3, 0, 1)
        val actualResult = task.missingNumber(input)
        Assertions.assertEquals(2, actualResult)
    }

    @Test
    fun test2() {
        val input = intArrayOf(0, 1)
        val actualResult = task.missingNumber(input)
        Assertions.assertEquals(2, actualResult)
    }

    @Test
    fun test3() {
        val input = intArrayOf(9, 6, 4, 2, 3, 5, 7, 0, 1)
        val actualResult = task.missingNumber(input)
        Assertions.assertEquals(8, actualResult)
    }

    @Test
    fun test4() {
        val input = intArrayOf(1)
        val actualResult = task.missingNumber(input)
        Assertions.assertEquals(0, actualResult)
    }

    @Test
    fun test5() {
        val input = intArrayOf(0)
        val actualResult = task.missingNumber(input)
        Assertions.assertEquals(1, actualResult)
    }

    @Test
    fun test6() {
        val input = intArrayOf(1, 2)
        val actualResult = task.missingNumber(input)
        Assertions.assertEquals(0, actualResult)
    }
}