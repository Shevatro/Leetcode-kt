package bctci.dp

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.math.max

class Task40_8 {
    fun reconstructLongestCommonSubsequence(s1: String, s2: String): String {
        return Solution2(s1, s2).reconstructLongestCommonSubsequence()
    }

    private class Solution(
        private val s1: String,
        private val s2: String
    ) {
        private val memo = Array(s1.length) { IntArray(s2.length) { -1 } }

        fun reconstructLongestCommonSubsequence(): String {
            dp(0, 0)
            return reconstruct(0, 0)
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

        private fun reconstruct(i1: Int, i2: Int): String {
            println("i1: $i1, i2: $i2")
            if (i1 == s1.length || i2 == s2.length) return ""
            val result = if (s1[i1] == s2[i2]) {
                s1[i1] + reconstruct(i1 + 1, i2 + 1)
            } else {
                if (i1 == s1.lastIndex || (i2 != s2.lastIndex && memo[i1][i2 + 1] > memo[i1 + 1][i2])) {
                    reconstruct(i1, i2 + 1)
                } else {
                    reconstruct(i1 + 1, i2)
                }
            }
            return result
        }
    }

    private class Solution2(
        private val s1: String,
        private val s2: String
    ) {
        private val memo = Array(s1.length) { IntArray(s2.length) { -1 } }

        fun reconstructLongestCommonSubsequence(): String {
            if (s1.isEmpty() || s2.isEmpty()) return ""
            dp(0, 0)
            return reconstruct()
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

        private fun reconstruct(): String {
            val sb = StringBuilder()
            var i1 = 0
            var i2 = 0
            while (i1 < s1.length && i2 < s2.length) {
                if (s1[i1] == s2[i2]) {
                    sb.append(s1[i1])
                    i1++
                    i2++
                } else if (dp(i1 + 1, i2) > dp(i1, i2 + 1)) {
                    i1++
                } else {
                    i2++
                }
            }
            return sb.toString()
        }
    }

    private class NotOptimalSolution(
        private val s1: String,
        private val s2: String
    ) {
        private val memo = Array(s1.length) { Array<String?>(s2.length) { null } }

        fun reconstructLongestCommonSubsequence(): String {
            return dp(0, 0)
        }

        private fun dp(i1: Int, i2: Int): String {
            if (i1 == s1.length || i2 == s2.length) return ""
            memo[i1][i2]?.let { return it }
            memo[i1][i2] = if (s1[i1] == s2[i2]) {
                //we are good, add this letter to the LCS
                s1[i1] + dp(i1 + 1, i2 + 1)
            } else {
                //discard text1[i1] or text2[i2],
                //ex. s1 = "ABC" and s2 = "BCA". It is important that we discard the 'A' in s1, not the 'B' in s2
                val discardI2 = dp(i1, i2 + 1)
                val discardI1 = dp(i1 + 1, i2)
                if (discardI1.length > discardI2.length) discardI1 else discardI2
            }
            return memo[i1][i2]!!
        }
    }
}


private class Task40_8Test {
    private val task = Task40_8()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input1: String, input2: String, expected: Int) {
        Assertions.assertEquals(expected, task.reconstructLongestCommonSubsequence(input1, input2).length)
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