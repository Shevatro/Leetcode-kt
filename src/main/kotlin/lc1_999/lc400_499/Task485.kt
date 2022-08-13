package lc1_999.lc400_499

import kotlin.math.max

//From a learning section, Solved
//https://leetcode.com/problems/max-consecutive-ones/
class Task485 {
    fun findMaxConsecutiveOnes(nums: IntArray): Int {
        var maxCount = 0
        var currentCount = 0
        for (i in nums.indices) {
            if (nums[i] == 1) {
                currentCount++
            } else {
                maxCount = max(maxCount, currentCount)
                currentCount = 0
            }
        }
        return max(maxCount, currentCount)
    }
}


fun main() {
    val obj = Task485()
    println(obj.findMaxConsecutiveOnes(intArrayOf(1, 1, 0, 1, 1, 1)))
    println(obj.findMaxConsecutiveOnes(intArrayOf(1, 0, 1, 1, 0, 1)))
    println(obj.findMaxConsecutiveOnes(intArrayOf(1, 1, 1, 1, 1, 1)))
    println(obj.findMaxConsecutiveOnes(intArrayOf(1, 1, 1, 1, 1, 0)))
    println(obj.findMaxConsecutiveOnes(intArrayOf(0, 1, 1, 1, 1, 0)))
    println(obj.findMaxConsecutiveOnes(intArrayOf(1, 1, 0, 1)))
    println(obj.findMaxConsecutiveOnes(intArrayOf(1)))
}