package lc1_999.lc300_399

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

//Solved
//https://leetcode.com/problems/is-subsequence/
class Task392 {
    fun isSubsequence(s: String, t: String): Boolean {
        if (s.isEmpty()) return true
        var sPos = 0
        for (tPos in t.indices) {
            if (sPos < s.length && t[tPos] == s[sPos]) {
                sPos++
                if (sPos == s.length) return true
            }
        }
        return false
    }
}

private class Task392Test {
    private val task = Task392()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input1: String, input2: String, expected: Boolean) {
        Assertions.assertEquals(expected, task.isSubsequence(input1, input2))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of("abc", "ahbgdc", true),
                Arguments.of("axc", "ahbgdc", false),
                Arguments.of("", "ahbgdc", true),
                Arguments.of("", "", true),
                Arguments.of("abc", "", false)
            )
        }
    }
}