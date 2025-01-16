package lc1_999.lc1_99

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

//From Cracking The Coding Interview, Solved
//https://leetcode.com/problems/search-in-rotated-sorted-array-ii/
class Task81 {

    fun searchFirstSolution(nums: IntArray, target: Int): Boolean {
        var start = 0
        var end = nums.size - 1
        while (start <= end) {
            if (nums[start] == target || nums[end] == target) return true
            start++
            end--
        }
        return false
    }

    fun search(nums: IntArray, target: Int): Boolean {
        var start = 0
        var end = nums.size - 1
        // Continue searching while the window is valid
        while (start < end) {
            val mid = start + (end - start) / 2 // Avoid potential overflow of (left + right)
            // If middle element is greater than the rightmost element, the pivot is in the right half
            if (nums[mid] > nums[end]) {
                // If target lies within the left sorted portion
                if (nums[start] <= target && target <= nums[mid]) {
                    end = mid // Narrow down to left half
                } else {
                    start = mid + 1 // Search in the right half
                }
            } else if (nums[mid] < nums[end]) {
                // If target lies within the right sorted portion
                if (nums[mid] < target && target <= nums[end]) {
                    start = mid + 1 // Narrow down to right half
                } else {
                    end = mid // Search in the left half
                }
            } else {
                end--
            }
        }
        // After the loop ends, left == right,
        // checking if we have found the target
        return nums[start] == target
    }
}

private class Task81Test {
    private val task = Task81()

    @Test
    fun test1() {
        val input = intArrayOf(2, 5, 6, 0, 0, 1, 2)
        val actualResult = task.search(input, 0)
        Assertions.assertEquals(true, actualResult)
    }

    @Test
    fun test2() {
        val input = intArrayOf(2, 5, 6, 0, 0, 1, 2)
        val actualResult = task.search(input, 3)
        Assertions.assertEquals(false, actualResult)
    }

    @Test
    fun test3() {
        val input = intArrayOf(2)
        val actualResult = task.search(input, 2)
        Assertions.assertEquals(true, actualResult)
    }

    @Test
    fun test4() {
        val input = intArrayOf(2)
        val actualResult = task.search(input, 3)
        Assertions.assertEquals(false, actualResult)
    }
}