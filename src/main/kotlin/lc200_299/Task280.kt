package lc200_299

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
            if (i % 2 == 0) {
                if (nums[i - 1] < nums[i] && canSwapOddItems(nums, i - 2, i - 1, i)) {
                    swapItems(nums, i - 1, i)
                }
            } else {
                if (nums[i - 1] > nums[i] && canSwapEvenItems(nums, i - 2, i - 1, i)) {
                    swapItems(nums, i - 1, i)
                }
            }

        }
        println(nums.toString2())
    }

    private fun canSwapOddItems(nums: IntArray, pos1: Int, pos2: Int, pos3: Int): Boolean {
        val newPos2 = pos3
        val newPos3 = pos2
        return nums[pos1] <= nums[newPos2] && nums[newPos2] >= nums[newPos3]
    }

    private fun canSwapEvenItems(nums: IntArray, pos1: Int, pos2: Int, pos3: Int): Boolean {
        val newPos2 = pos3
        val newPos3 = pos2
        return nums.getOrNull(pos1) == null || nums[pos1] >= nums[newPos2] && nums[newPos2] <= nums[newPos3]
    }

    private fun swapItems(nums: IntArray, pos2: Int, pos3: Int) {
        val temp = nums[pos3]
        nums[pos3] = nums[pos2]
        nums[pos2] = temp
    }
}

fun main() {
    Task280().wiggleSort(intArrayOf(3, 5, 2, 1, 6, 4))
    Task280().wiggleSort(intArrayOf(6, 6, 5, 6, 3, 8))
    Task280().wiggleSort(intArrayOf(1, 2, 3, 4, 5, 6))
    Task280().wiggleSort(intArrayOf(2, 1))
    Task280().wiggleSort(intArrayOf(1, 3, 4, 2))
}