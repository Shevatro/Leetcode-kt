package lc1_999.lc700_799

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

//Solved
//https://leetcode.com/problems/find-pivot-index/
class Task724 {
    fun pivotIndex(nums: IntArray): Int {
        var leftSum = 0
        var rightSum = nums.sum()
        for (i in 0 until nums.size) {
            rightSum -= nums[i]
            leftSum += nums.getOrNull(i - 1) ?: 0
            if (leftSum == rightSum) return i
        }
        return -1
    }
}

private class Task724Test {
    private val task = Task724()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input: IntArray, expected: Int) {
        Assertions.assertEquals(expected, task.pivotIndex(input))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(intArrayOf(1, 7, 3, 6, 5, 6), 3),
                Arguments.of(intArrayOf(1, 2, 3), -1),
                Arguments.of(intArrayOf(2, 1, -1), 0),
                Arguments.of(intArrayOf(1, -1, 2), 2)
            )
        }
    }
}