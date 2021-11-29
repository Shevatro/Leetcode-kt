package lc500_599

//Not solved, repeat
//https://leetcode.com/problems/shortest-unsorted-continuous-subarray/
class Task581 {

    //optimize
    fun findUnsortedSubarray(nums: IntArray): Int {
        var max = nums[0]
        var min = nums[nums.lastIndex]
        var right = -1
        var left = -1
        for (i in 1 until nums.size) {
            if (nums[i] >= max) max = nums[i] else right = i
            if (nums[nums.lastIndex - i] <= min) min = nums[nums.lastIndex - i] else left = i
        }
        return Math.max(0, right + left - nums.lastIndex + 1)
    }
    //my
    // fun findUnsortedSubarray(nums: IntArray): Int {
    //     if (nums.size<=1) return 0
    //     val sortedNums = nums.clone()
    //     sortedNums.sort()
    //     val startIndex = findStartIndexOfSubArray(nums, sortedNums)
    //     val endIndex = findEndIndexOfSubArray(nums, sortedNums)
    //     if (startIndex == null || endIndex == null) return 0
    //     return endIndex - startIndex + 1
    // }

    private fun findEndIndexOfSubArray(nums: IntArray, sortedNums: IntArray): Int? {
        for (i in nums.lastIndex downTo 0) {
            if (nums[i] != sortedNums[i]) return i
        }
        return null
    }

    private fun findStartIndexOfSubArray(nums: IntArray, sortedNums: IntArray): Int? {
        for (i in nums.indices) {
            if (nums[i] != sortedNums[i]) return i
        }
        return null
    }
}