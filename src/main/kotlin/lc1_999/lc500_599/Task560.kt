package lc1_999.lc500_599

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

//Not Solved
//https://leetcode.com/problems/subarray-sum-equals-k/
class Task560 {
    fun subarraySum(nums: IntArray, k: Int): Int {
        val map = hashMapOf(0 to 1)
        var amount = 0
        var sum = 0
        for (num in nums) {
            sum += num
            amount += map.getOrDefault(sum - k, 0)
            map[sum] = map.getOrDefault(sum, 0) + 1
        }
        return amount
    }
}

private class Task560Test {
    private val task = Task560()

    @Test
    fun subarraySumTest() {
        Assertions.assertEquals(2, task.subarraySum(intArrayOf(1, 1, 1), 2))
        Assertions.assertEquals(2, task.subarraySum(intArrayOf(1, 2, 3), 3))
        Assertions.assertEquals(1, task.subarraySum(intArrayOf(2), 2))
        Assertions.assertEquals(11, task.subarraySum(intArrayOf(0, 2, -2, -3, 5, 10, -10, 2), 2))
        Assertions.assertEquals(4, task.subarraySum(intArrayOf(1, 2, 1, 2, 1), 3))
    }
}