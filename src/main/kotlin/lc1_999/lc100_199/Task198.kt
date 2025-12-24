package lc1_999.lc100_199

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.math.max

//From Beyond Cracking The Coding Interview, Not Solved
//https://leetcode.com/problems/house-robber/description/
class Task198 {
    fun rob(nums: IntArray): Int {
        return Solution(nums).rob()
    }

    private class Solution(private val nums: IntArray) {
        private val memo = IntArray(nums.size) { Int.MIN_VALUE }
        fun rob(): Int {
            return dp(0)
        }

        private fun dp(i: Int): Int {
            if (i > nums.lastIndex) return 0
            if (memo[i] != Int.MIN_VALUE) return memo[i]
            memo[i] = max(nums[i] + dp(i + 2), dp(i + 1))
            return memo[i]
        }
    }
}

private class Task198Test {
    private val task = Task198()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input: IntArray, expected: Int) {
        Assertions.assertEquals(expected, task.rob(input))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(intArrayOf(1, 2, 3, 1), 4),
                Arguments.of(intArrayOf(2, 7, 9, 3, 1), 12),
                Arguments.of(intArrayOf(5, 2, 3, 13), 18),
                Arguments.of(intArrayOf(1, 2), 2),
                Arguments.of(intArrayOf(1, 3, 1), 3),
                Arguments.of(intArrayOf(1, 2, 1, 1), 3)
            )
        }
    }
}