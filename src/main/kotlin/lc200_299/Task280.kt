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
}

fun main() {
    Task280().wiggleSortSimple(intArrayOf(3, 5, 2, 1, 6, 4))
}