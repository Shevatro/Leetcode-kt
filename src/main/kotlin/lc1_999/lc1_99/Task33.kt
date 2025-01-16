package lc1_999.lc1_99

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

//From Cracking The Coding Interview, Solved
//https://leetcode.com/problems/search-in-rotated-sorted-array/
class Task33 {

    fun searchFirstSolution(nums: IntArray, target: Int): Int {
        var start = 0
        var end = nums.size - 1
        while (start <= end) {
            if (nums[start] == target) return start
            if (nums[end] == target) return end
            start++
            end--
        }
        return -1
    }

    fun search(nums: IntArray, target: Int): Int {
        var start = 0
        var end = nums.size - 1
        while (start < end){
            val mid = (end + start) / 2
            // When middle element is on the non-rotated portion of the array.
            if (nums[0] <= nums[mid]) {
                // Check if the target is also on the non-rotated portion and adjust end accordingly.
                if (nums[0] <= target && target <= nums[mid]) {
                    end = mid
                } else {
                    start = mid + 1
                }
                // When middle element is on the rotated portion of the array.
            } else {
                // Check if the target is also on the rotated portion and adjust start accordingly.
                if (nums[mid] < target && target <= nums[nums.size - 1]) {
                    start = mid + 1
                } else {
                    end = mid
                }
            }
        }
        // After narrowing down to one element, check if it's the target.
        // If nums[start] is the target, return its index, otherwise return -1.
        return if (nums[start] == target) start else -1
    }
}

private class Task33Test {
    private val task = Task33()

    @Test
    fun test1() {
        val input = intArrayOf(4, 5, 6, 7, 0, 1, 2)
        val actualResult = task.search(input, 0)
        Assertions.assertEquals(4, actualResult)
    }

    @Test
    fun test2() {
        val input = intArrayOf(4, 5, 6, 7, 0, 1, 2)
        val actualResult = task.search(input, 3)
        Assertions.assertEquals(-1, actualResult)
    }

    @Test
    fun test3() {
        val input = intArrayOf(1)
        val actualResult = task.search(input, 0)
        Assertions.assertEquals(-1, actualResult)
    }

    @Test
    fun test4() {
        val input = intArrayOf(1)
        val actualResult = task.search(input, 1)
        Assertions.assertEquals(0, actualResult)
    }
}