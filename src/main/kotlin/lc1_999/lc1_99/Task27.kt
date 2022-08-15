package lc1_999.lc1_99

import toString2

//From a learning section, Solved
//https://leetcode.com/problems/remove-element/
class Task27 {

    fun removeElement(nums: IntArray, `val`: Int): Int {
        var startPos = 0
        var endPos = nums.lastIndex
        while (endPos >= startPos) {
            if (nums[startPos] == `val`) {
                nums[startPos] = nums[endPos]
                endPos--
            } else {
                startPos++
            }
        }
        println(nums.toString2())
        return endPos + 1
    }
}


fun main() {
    val obj = Task27()
    println(obj.removeElement(intArrayOf(3, 2, 2, 3), 3))
    println(obj.removeElement(intArrayOf(0, 1, 2, 2, 3, 0, 4, 2), 2))
    println(obj.removeElement(intArrayOf(0, 1, 2, 2, 3, 0, 4, 1), 2))
    println(obj.removeElement(intArrayOf(0, 1, 2, 2, 3, 0, 4, 2), 5))
    println(obj.removeElement(intArrayOf(2, 2, 2), 2))
    println(obj.removeElement(intArrayOf(), 5))
}

