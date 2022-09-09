package lc1_999.lc400_499

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.math.max

//From a learning section, Solved
//https://leetcode.com/problems/third-maximum-number/
class Task414 {
    fun thirdMax(nums: IntArray): Int {
        val max = requireNotNull(maxOrNull(nums))
        val secondMax = maxOrNull(nums) { it != max } ?: return max
        return maxOrNull(nums) { it != max && it != secondMax } ?: max
    }

    private fun maxOrNull(nums: IntArray, condition: ((Int) -> Boolean)? = null): Int? {
        if (nums.isEmpty()) return null
        var max = Int.MIN_VALUE
        var isDefaultMax = true
        for (item in nums) {
            if (condition?.invoke(item) != false) {
                max = max(item, max)
                isDefaultMax = false
            }
        }
        return if (isDefaultMax) null else max
    }
}

private class Task414Test {
    private val task = Task414()

    @Test
    fun thirdMax() {
        thirdMax(intArrayOf(3, 2, 1), 1)
        thirdMax(intArrayOf(1, 2), 2)
        thirdMax(intArrayOf(2, 2, 3, 1), 1)
    }

    private fun thirdMax(actualInp: IntArray, expected: Int) {
        val actual = task.thirdMax(actualInp)
        println(actual)
        Assertions.assertEquals(expected, actual)
    }
}