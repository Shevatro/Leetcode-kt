package lc1_999.lc100_199

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

//Solved but Not optimal
//https://leetcode.com/problems/majority-element/
class Task169 {
    fun majorityElement(nums: IntArray): Int {
        var candidate = 0
        var count = 0
        for (num in nums) {
            if (count == 0) candidate = num
            count += if (candidate == num) 1 else -1
        }
        return candidate
    }

    fun majorityElementMy(nums: IntArray): Int {
        val majority = nums.size / 2
        val map = mutableMapOf<Int, Int>()
        var max = 1
        var item = nums[0]
        for (num in nums) {
            if (map[num] == null) map[num] = 0
            map[num] = map[num]!! + 1
            if (map[num]!! > max) {
                max = map[num]!!
                item = num
            }
            if (max > majority) return item
        }
        return item
    }
}

private class Task169Test {
    private val task = Task169()

    @Test
    fun test1() {
        val result = task.majorityElement(intArrayOf(3, 2, 3))
        Assertions.assertEquals(3, result)
    }

    @Test
    fun test2() {
        val result = task.majorityElement(intArrayOf(2, 2, 1, 1, 1, 2, 2))
        Assertions.assertEquals(2, result)
    }

    @Test
    fun test3() {
        val result = task.majorityElement(intArrayOf(3))
        Assertions.assertEquals(3, result)
    }

    @Test
    fun test4() {
        val result = task.majorityElement(intArrayOf(2, 2))
        Assertions.assertEquals(2, result)
    }
}