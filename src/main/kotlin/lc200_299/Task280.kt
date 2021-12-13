package lc200_299
//Solved, but repeat
//https://leetcode.com/problems/wiggle-sort/
import toString2

class Task280 {
    fun wiggleSortSimple(nums: IntArray): Unit {
        if (nums.size == 1) return
        val newNums = nums.clone()
        newNums.sort()
        var j = nums.size - 1
        var i = 0
        var k = 0
        while (i <= j) {
            nums[k] = newNums[i]
            if (nums.getOrNull(k + 1) != null) {
                nums[k + 1] = newNums[j]
            }
            k += 2
            i++
            j--
        }
        println(nums.toString2())
    }

    fun wiggleSort(nums: IntArray): Unit {
        if (nums.size == 1) return
        for (i in 1 until nums.size) {
            if ((i % 2 == 0) == (nums[i - 1] < nums[i])) {
                swapItems(nums, i)
            }
        }
        println(nums.toString2())
    }

    private fun swapItems(nums: IntArray, i: Int) {
        val temp = nums[i]
        nums[i] = nums[i - 1]
        nums[i - 1] = temp
    }
}

fun main() {
    Task280().wiggleSort(intArrayOf(3, 5, 2, 1, 6, 4))
    Task280().wiggleSort(intArrayOf(6, 6, 5, 6, 3, 8))
    Task280().wiggleSort(intArrayOf(1, 2, 3, 4, 5, 6))
    Task280().wiggleSort(intArrayOf(2, 1))
    Task280().wiggleSort(intArrayOf(1, 3, 4, 2))
}