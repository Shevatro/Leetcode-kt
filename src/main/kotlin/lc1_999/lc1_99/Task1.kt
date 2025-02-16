package lc1_999.lc1_99

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

//Solved
//https://leetcode.com/problems/two-sum/description/
class Task1 {
    fun twoSum(nums: IntArray, target: Int): IntArray {
        return Solution2(nums, target).twoSum()
    }

    private class Solution2(
        private val nums: IntArray,
        private val target: Int
    ) {
        private val diffs = mutableMapOf<Int, Int>()
        fun twoSum(): IntArray {
            for (i in nums.indices) {
                val firstIndex = diffs[nums[i]]
                if (firstIndex != null) {
                    return intArrayOf(firstIndex, i)
                }
                val diff = target - nums[i]
                diffs[diff] = i
            }
            return intArrayOf()
        }
    }
}

private class Task1Test {
    private val task = Task1()

    @Test
    fun test1() {
        val result = task.twoSum(intArrayOf(2, 7, 11, 15), 9)
        Assertions.assertArrayEquals(intArrayOf(0, 1), result)
    }

    @Test
    fun test2() {
        val result = task.twoSum(intArrayOf(3, 2, 4), 6)
        Assertions.assertArrayEquals(intArrayOf(1, 2), result)
    }

    @Test
    fun test3() {
        val result = task.twoSum(intArrayOf(3, 3), 6)
        Assertions.assertArrayEquals(intArrayOf(0, 1), result)
    }

    @Test
    fun test4() {
        val result = task.twoSum(intArrayOf(0, 4, 3, 0), 0)
        Assertions.assertArrayEquals(intArrayOf(0, 3), result)
    }

    @Test
    fun test5() {
        val result = task.twoSum(intArrayOf(1, 1, 1, 1, 1, 4, 1, 1, 1, 1, 1, 7, 1, 1, 1, 1, 1), 11)
        Assertions.assertArrayEquals(intArrayOf(5, 11), result)
    }

    @Test
    fun test6() {
        val result = task.twoSum(intArrayOf(-1, -4, 3, 0), 2)
        Assertions.assertArrayEquals(intArrayOf(0, 2), result)
    }

    @Test
    fun test7() {
        val result = task.twoSum(intArrayOf(-1, 3, 0, -4), -5)
        Assertions.assertArrayEquals(intArrayOf(0, 3), result)
    }
}