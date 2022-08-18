package lc1_999.lc1_99

import toString2

//From a learning section, Solved
//https://leetcode.com/problems/remove-duplicates-from-sorted-array/
class Task26 {
    fun removeDuplicates(nums: IntArray): Int {
        var fillPos = 1
        var previousItem = nums[0]
        for (i in 1 until nums.size) {
            val item = nums[i]
            if (item != previousItem) {
                nums[fillPos] = item
                fillPos++
                previousItem = item
            }
        }
        println(nums.toString2())
        return fillPos
    }
}


fun main() {
    val obj = Task26()
    println(obj.removeDuplicates(intArrayOf(1, 1, 2)))
    println(obj.removeDuplicates(intArrayOf(0, 0, 1, 1, 1, 2, 2, 3, 3, 4)))
    println(obj.removeDuplicates(intArrayOf(1)))
    println(obj.removeDuplicates(intArrayOf(1, 1, 1, 1)))
    println(obj.removeDuplicates(intArrayOf(1, 2, 3, 4)))
}

