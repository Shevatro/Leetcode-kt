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
        private var result: IntArray? = null
        fun twoSum(): IntArray {
            calculateDiff()
            //println(diffs)
            if (result == null) {
                findAnotherDiff()
            }
            return result!!
        }

        private fun calculateDiff() {
            for (i in nums.indices) {
                val num = nums[i]
                val diff = target - num
                //if (diff>0){
                if (diffs[diff] != null && diff == num) {
                    result = intArrayOf(diffs[diff]!!, i)
                    return
                } else {
                    diffs[diff] = i
                }
                //}
            }
        }

        private fun findAnotherDiff() {
            for (diff in diffs.keys) {
                val secondDiff = target - diff
                if (diffs[secondDiff] != null && secondDiff != diff) {
                    result = intArrayOf(diffs[diff]!!, diffs[secondDiff]!!)
                    return
                }
            }
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
}