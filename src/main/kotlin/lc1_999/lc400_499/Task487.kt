package lc1_999.lc400_499

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.math.max

//From a learning section, Solved (Optimization needs to repeat)
//https://leetcode.com/problems/max-consecutive-ones-ii/
class Task487 {
    fun findMaxConsecutiveOnes(nums: IntArray): Int {
        var max = 0
        var amountBeforeAndIncludeZero = 0
        var amountAfterZero = 0
        for (item in nums) {
            if (item == 0) {
                // Including the zero number
                amountBeforeAndIncludeZero = amountAfterZero + 1
                amountAfterZero = 0
            } else {
                amountAfterZero++
            }
            max = max(amountBeforeAndIncludeZero + amountAfterZero, max)
        }
        return max
    }

    //Not mine, Repeat
    fun findMaxConsecutiveOnesSlidingWindow(nums: IntArray): Int {
        var max = 0
        var startInd = 0
        var prevZeroInd = -1
        for (i in nums.indices) {
            if (nums[i] == 0) {
                startInd = prevZeroInd + 1
                prevZeroInd = i
            }
            max = max(i - startInd + 1, max)
        }
        return max
    }
}

private class Task487Test {
    private val task = Task487()

    @Test
    fun findMaxConsecutiveOnes() {
        findMaxConsecutiveOnes(intArrayOf(1, 0, 1, 1, 0), 4)
        findMaxConsecutiveOnes(intArrayOf(1, 0, 1, 1, 0, 1), 4)
        findMaxConsecutiveOnes(intArrayOf(1, 0, 1, 1, 0, 1), 4)
        findMaxConsecutiveOnes(intArrayOf(1, 1, 1), 3)
        findMaxConsecutiveOnes(intArrayOf(1), 1)
        findMaxConsecutiveOnes(intArrayOf(0), 1)
        findMaxConsecutiveOnes(intArrayOf(0, 0, 0), 1)
        findMaxConsecutiveOnes(intArrayOf(0, 1, 1), 3)
        findMaxConsecutiveOnes(intArrayOf(0, 1, 0), 2)
        findMaxConsecutiveOnes(intArrayOf(1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1), 9)
    }

    private fun findMaxConsecutiveOnes(actualInp: IntArray, expected: Int) {
        val actual = task.findMaxConsecutiveOnes(actualInp)
        println(actual)
        Assertions.assertEquals(expected, actual)
    }
}