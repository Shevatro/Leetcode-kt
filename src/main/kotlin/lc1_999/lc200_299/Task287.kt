package lc1_999.lc200_299

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

//From Cracking The Coding Interview, Not Solved
//https://leetcode.com/problems/find-the-duplicate-number/
class Task287 {
    fun findDuplicate(nums: IntArray): Int {
//        return LessOrEqualBinarySearchSolution(nums).findDuplicate()
        return LinkedListBasedSolution(nums).findDuplicate()
    }

    private class LessOrEqualBinarySearchSolution(private val nums: IntArray) {
        private var low = 1
        private var high = nums.size - 1
        fun findDuplicate(): Int {
            while (low <= high) {
                val mid = (low + high) / 2
                val amount = nums.count { it <= mid }
                if (amount > mid) {
                    high = mid - 1
                } else {
                    low = mid + 1
                }
            }
            return high + 1
        }
    }

    private class LinkedListBasedSolution(private val nums: IntArray) {
        private var fastP = nums[0]
        private var slowP = nums[0]
        fun findDuplicate(): Int {
            findCycle()
            findEntranceInCycle()
            return fastP
        }

        private fun findCycle() {
            do {
                fastP = nums[nums[fastP]]
                slowP = nums[slowP]
            } while (fastP != slowP)
        }

        private fun findEntranceInCycle() {
            fastP = nums[0]
            while (fastP != slowP) {
                fastP = nums[fastP]
                slowP = nums[slowP]
            }
        }
    }
}

private class Task287Test {
    private val task = Task287()

    @Test
    fun test1() {
        val input = intArrayOf(1, 3, 4, 2, 2)
        val actualResult = task.findDuplicate(input)
        Assertions.assertEquals(2, actualResult)
    }

    @Test
    fun test2() {
        val input = intArrayOf(3, 1, 3, 4, 2)
        val actualResult = task.findDuplicate(input)
        Assertions.assertEquals(3, actualResult)
    }

    @Test
    fun test3() {
        val input = intArrayOf(3, 3, 3, 3, 3)
        val actualResult = task.findDuplicate(input)
        Assertions.assertEquals(3, actualResult)
    }

    @Test
    fun test4() {
        val input = intArrayOf(9, 4, 9, 5, 7, 2, 8, 9, 3, 9)
        val actualResult = task.findDuplicate(input)
        Assertions.assertEquals(9, actualResult)
    }

    @Test
    fun test5() {
        val input = intArrayOf(2, 5, 9, 6, 9, 3, 8, 9, 7, 1)
        val actualResult = task.findDuplicate(input)
        Assertions.assertEquals(9, actualResult)
    }
}