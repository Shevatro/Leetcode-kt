package lc1_999.lc1_99

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

//Solved
//https://leetcode.com/problems/climbing-stairs/
class Task70 {
    fun climbStairs(n: Int): Int {
        return Solution(n).climbStairs()
    }

    private class Solution(private val n: Int) {
        private val memo = IntArray(n + 1) { -1 }
        fun climbStairs(): Int {
            return dp(n)
        }

        private fun dp(n: Int): Int {
            if (n <= 0) return 0
            if (n == 1) return 1
            if (n == 2) return 2
            if (memo[n] != -1) return memo[n]
            memo[n] = dp(n - 1) + dp(n - 2)
            return memo[n]
        }
    }
}

private class Task70Test {
    private val task = Task70()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input: Int, expected: Int) {
        Assertions.assertEquals(expected, task.climbStairs(input))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(1, 1),
                Arguments.of(2, 2),
                Arguments.of(3, 3),
                Arguments.of(10, 89)
            )
        }
    }
}