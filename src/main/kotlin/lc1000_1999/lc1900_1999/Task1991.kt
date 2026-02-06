package lc1000_1999.lc1900_1999

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

//Solved
//https://leetcode.com/problems/find-the-middle-index-in-array/
class Task1991 {
    fun findMiddleIndex(nums: IntArray): Int {
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

private class Task1991Test {
    private val task = Task1991()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input: IntArray, expected: Int) {
        Assertions.assertEquals(expected, task.findMiddleIndex(input))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(intArrayOf(2, 3, -1, 8, 4), 3),
                Arguments.of(intArrayOf(1, -1, 4), 2),
                Arguments.of(intArrayOf(2, 5), -1)
            )
        }
    }
}