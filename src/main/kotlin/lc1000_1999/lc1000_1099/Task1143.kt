package lc1000_1999.lc1000_1099

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.math.max

//From Beyond Cracking The Coding Interview, Not Solved
//https://leetcode.com/problems/longest-common-subsequence/description/
class Task1143 {
    fun longestCommonSubsequence(text1: String, text2: String): Int {
        return Solution(text1, text2).longestCommonSubsequence()
    }

    private class Solution(
        private val text1: String,
        private val text2: String
    ) {
        private val memo = Array(text1.length) { IntArray(text2.length) { -1 } }

        fun longestCommonSubsequence(): Int {
            return dp(0, 0)
        }

        private fun dp(i1: Int, i2: Int): Int {
            if (i1 == text1.length || i2 == text2.length) return 0
            if (memo[i1][i2] != -1) return memo[i1][i2]
            memo[i1][i2] = if (text1[i1] == text2[i2]) {
                //we are good, add this letter to the LCS
                dp(i1 + 1, i2 + 1) + 1
            } else {
                //discard text1[i1] or text2[i2],
                //ex. text1 = "ABC" and text2 = "BCA". It is important that we discard the 'A' in text1, not the 'B' in text2
                max(dp(i1, i2 + 1), dp(i1 + 1, i2))
            }
            return memo[i1][i2]
        }
    }
}

private class Task1143Test {
    private val task = Task1143()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input1: String, input2: String, expected: Int) {
        Assertions.assertEquals(expected, task.longestCommonSubsequence(input1, input2))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of("abcde", "ace", 3),
                Arguments.of("abc", "abc", 3),
                Arguments.of("abc", "def", 0)
            )
        }
    }
}