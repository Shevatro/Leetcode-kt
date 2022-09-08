package lc1_999.lc900_999

import toString2

//From a learning section, Solved
//https://leetcode.com/problems/sort-array-by-parity/
class Task905 {
    fun sortArrayByParity(nums: IntArray): IntArray {
        var startPos = 0
        var endPos = nums.lastIndex
        while (startPos <= endPos) {
            val isStarOdd = nums[startPos] % 2 != 0
            val isEndEven = nums[endPos] % 2 == 0
            if (isStarOdd && isEndEven) {
                val temp = nums[startPos]
                nums[startPos] = nums[endPos]
                nums[endPos] = temp
                startPos++
                endPos--
            } else {
                if (!isStarOdd) startPos++
                if (!isEndEven) endPos--
            }

        }
        return nums
    }
}


fun main() {
    val obj = Task905()
    println(obj.sortArrayByParity(intArrayOf(3, 1, 2, 4)).toString2())
    println(obj.sortArrayByParity(intArrayOf(0)).toString2())
    println(obj.sortArrayByParity(intArrayOf(3, 1, 5, 7)).toString2())
    println(obj.sortArrayByParity(intArrayOf(2, 4, 8, 10)).toString2())
    println(obj.sortArrayByParity(intArrayOf(7, 4, 5, 7)).toString2())
    println(obj.sortArrayByParity(intArrayOf(7, 6, 4)).toString2())
}

