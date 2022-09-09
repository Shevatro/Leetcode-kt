package lc1_999.lc400_499

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.math.max

//From a learning section, Solved
//https://leetcode.com/problems/max-consecutive-ones-ii/
class Task487 {
    fun findMaxConsecutiveOnes(nums: IntArray): Int {
        var max = 0
        var amountBeforeZero = 0
        var amountAfterZero = 0
        var hasZeroBefore = false
        for (item in nums) {
            if (item == 0) {
                if (hasZeroBefore) {
                    val amount = amountBeforeZero + amountAfterZero + 1
                    max = max(amount, max)
                } else {
                    hasZeroBefore = true
                }
                amountBeforeZero = amountAfterZero
                amountAfterZero = 0
            } else {
                amountAfterZero++
            }
        }
        val amount = if (hasZeroBefore) amountBeforeZero + amountAfterZero + 1 else amountAfterZero
        return max(amount, max)
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