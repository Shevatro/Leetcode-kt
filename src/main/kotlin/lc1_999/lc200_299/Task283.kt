package lc1_999.lc200_299

import toString2

//From a learning section, Solved
//https://leetcode.com/problems/move-zeroes/
class Task283 {
    fun moveZeroes(nums: IntArray): Unit {
        if (nums.size == 1) return
        var writePos = 0
        for (readPos in nums.indices) {
            if (nums[readPos] != 0) {
                nums[writePos] = nums[readPos]
                writePos++
            }
        }
        for (i in writePos until nums.size) {
            nums[i] = 0
        }
        println(nums.toString2())
    }
}


fun main() {
    val obj = Task283()
    obj.moveZeroes(intArrayOf(0, 1, 0, 3, 12))
    obj.moveZeroes(intArrayOf(0))
    obj.moveZeroes(intArrayOf(2, 1, 6, 3, 12))
    obj.moveZeroes(intArrayOf(0, 0, 0, 0, 0))
    obj.moveZeroes(intArrayOf(0, 0, 0, 0, 1))
    obj.moveZeroes(intArrayOf(-10, 0, 0, 0, 1))
}

