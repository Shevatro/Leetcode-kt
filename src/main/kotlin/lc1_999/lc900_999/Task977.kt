package lc1_999.lc900_999

import toString2
import kotlin.math.abs

//From a learning section, Solved
//https://leetcode.com/problems/squares-of-a-sorted-array/
class Task977 {
    fun sortedSquares(nums: IntArray): IntArray {
        val result = IntArray(nums.size)
        var startPos = 0
        var endPos = nums.lastIndex
        for (i in nums.lastIndex downTo 0) {
            val startItem = nums[startPos]
            val endItem = nums[endPos]
            if (abs(endItem) >= abs(startItem)) {
                result[i] = endItem * endItem
                endPos--
            } else {
                result[i] = startItem * startItem
                startPos++
            }
        }
        return result
    }
}


fun main() {
    val obj = Task977()
    println(obj.sortedSquares(intArrayOf(-4, -1, 0, 3, 10)).toString2())
    println(obj.sortedSquares(intArrayOf(-7, -3, 2, 3, 11)).toString2())
    println(obj.sortedSquares(intArrayOf(-5, -3, -2, -1)).toString2())
}