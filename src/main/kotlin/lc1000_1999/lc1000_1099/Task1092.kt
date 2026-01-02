package lc1000_1999.lc1000_1099

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.math.max

//Similar to Beyond Cracking The Coding Interview, Solved but with Hints
//https://leetcode.com/problems/shortest-common-supersequence/
class Task1092 {
    fun shortestCommonSupersequence(str1: String, str2: String): String {
        return Solution(str1, str2).shortestCommonSupersequence()
    }

    private class Solution(
        private val str1: String,
        private val str2: String
    ) {
        private val memo = Array(str1.length) { IntArray(str2.length) { -1 } }
        fun shortestCommonSupersequence(): String {
            dp(0, 0)
            return reconstruct()
        }

        private fun dp(i1: Int, i2: Int): Int {
            if (i1 == str1.length || i2 == str2.length) return 0
            if (memo[i1][i2] != -1) return memo[i1][i2]
            memo[i1][i2] = if (str1[i1] == str2[i2]) {
                dp(i1 + 1, i2 + 1) + 1
            } else {
                max(dp(i1 + 1, i2), dp(i1, i2 + 1))
            }
            return memo[i1][i2]
        }

        private fun reconstruct(): String {
            val sb = StringBuilder()
            var i1 = 0
            var i2 = 0
            while (i1 < str1.length && i2 < str2.length) {
                if (str1[i1] == str2[i2]) {
                    sb.append(str1[i1])
                    i1++
                    i2++
                } else if (dp(i1 + 1, i2) < dp(i1, i2 + 1)) {
                    sb.append(str2[i2])
                    i2++
                } else {
                    sb.append(str1[i1])
                    i1++
                }
            }
            //Copying the remaining suffix of the non-exhausted string
            if (i1 < str1.length) sb.append(str1.substring(i1))
            if (i2 < str2.length) sb.append(str2.substring(i2))
            return sb.toString()
        }
    }
}

private class Task1092Test {
    private val task = Task1092()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input1: String, input2: String, expected: String) {
        Assertions.assertEquals(expected, task.shortestCommonSupersequence(input1, input2))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of("abac", "cab", "cabac"),
                Arguments.of("aaaaaaaa", "aaaaaaaa", "aaaaaaaa"),
                Arguments.of("bbabacaa", "cccababab", "bbcccabacabab")
            )
        }
    }
}