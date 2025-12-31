package bctci.dp

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.math.max

class Task40_7 {
    fun longestCommonSubsequence(s1: String, s2: String): Int {
        return Solution(s1, s2).longestCommonSubsequence()
    }

    private class Solution(
        private val s1: String,
        private val s2: String
    ) {
        private val memo = Array(s1.length) { IntArray(s2.length) { -1 } }

        fun longestCommonSubsequence(): Int {
            return dp(0, 0)
        }

        private fun dp(i1: Int, i2: Int): Int {
            if (i1 == s1.length || i2 == s2.length) return 0
            if (memo[i1][i2] != -1) return memo[i1][i2]
            memo[i1][i2] = if (s1[i1] == s2[i2]) {
                //we are good, add this letter to the LCS
                dp(i1 + 1, i2 + 1) + 1
            } else {
                //discard text1[i1] or text2[i2],
                //ex. s1 = "ABC" and s2 = "BCA". It is important that we discard the 'A' in s1, not the 'B' in s2
                max(dp(i1, i2 + 1), dp(i1 + 1, i2))
            }
            return memo[i1][i2]
        }
    }
}


private class Task40_7Test {
    private val task = Task40_7()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input1: String, input2: String, expected: Int) {
        Assertions.assertEquals(expected, task.longestCommonSubsequence(input1, input2))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of("HAHAH", "AAAAHH", 3),
                Arguments.of("", "AA", 0),
                Arguments.of("ABC", "BCA", 2),
                Arguments.of("ABCD", "ACBAD", 3),
                Arguments.of("", "", 0),
                Arguments.of("ABCDEFGHIJ", "ACBDEFGHIK", 8),
                Arguments.of("AAAAAAAAAAAAAAA", "AAAAAAAAAAAAA", 13),
                Arguments.of("THEQUICKBROWNFOX", "THESLOWREDFOX", 8),
                Arguments.of("AAAAABBBBBCCCCCDDDDD", "BBBBBCCCCCDDDDDEEEEE", 15)
            )
        }
    }
}