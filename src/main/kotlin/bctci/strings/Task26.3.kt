package bctci.strings

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Task26_3 {
    fun indexOf(s: String, t: String): Int {
        return Solution(s, t).indexOf()
    }

    private class Solution(
        private val sourceStr: String,
        private val searchStr: String
    ) {
        private val sourceLength = searchStr.length
        private val searchLength = sourceStr.length
        private val sourceLastInd = sourceLength - 1
        fun indexOf(): Int {
            //empty string is always a part of the original string with 0 position
            if (searchStr.isEmpty()) return 0
            if (sourceLength > searchLength) return -1
            for (i in 0 until searchLength - sourceLastInd) {
                //check the first and last character matches
                if (sourceStr[i] == searchStr[0] && sourceStr[i + sourceLastInd] == searchStr[sourceLastInd]) {
                    if (matches(i)) return i
                }
            }
            return -1
        }

        private fun matches(startPos: Int): Boolean {
            if (startPos + sourceLength > searchLength) return false
            var curSearchInd = startPos
            for (searchCh in searchStr) {
                if (searchCh != sourceStr[curSearchInd]) return false
                curSearchInd++
            }
            return true
        }
    }
}


private class Task26_3Test {
    private val task = Task26_3()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input1: String, input2: String, expected: Int) {
        Assertions.assertEquals(expected, task.indexOf(input1, input2))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                // Basic cases
                Arguments.of("hello world", "world", 6),
                Arguments.of("hello world", "hello", 0),
                Arguments.of("needle in a haystack", "needle", 0),
                Arguments.of("needle in a haystack", "haystack", 12),
                Arguments.of("needle in a haystack", "not", -1),

                // Edge case - empty strings
                Arguments.of("", "", 0),
                Arguments.of("", "x", -1),
                Arguments.of("x", "", 0),
                Arguments.of("abc", "", 0),

                // Edge case - single character
                Arguments.of("x", "x", 0),
                Arguments.of("abc", "a", 0),
                Arguments.of("abc", "b", 1),
                Arguments.of("abc", "c", 2),
                Arguments.of("abc", "d", -1),

                // Edge case - pattern longer than string
                Arguments.of("x", "xx", -1),
                Arguments.of("abc", "abcd", -1),

                // multiple occurrences (Should return first occurrence)
                Arguments.of("banana", "ana", 1),
                Arguments.of("banana", "an", 1),
                Arguments.of("banana", "a", 1),

                // overlapping patterns
                Arguments.of("aaaaa", "aa", 0),
                Arguments.of("aaaaa", "aaa", 0),
                Arguments.of("aabaabaa", "aaba", 0),

                // pattern at start/end
                Arguments.of("startend", "start", 0),
                Arguments.of("startend", "end", 5),

                // special characters
                Arguments.of("\n\n\n", "\n", 0),
                Arguments.of("\n\n\n", "\n\n", 0),
                Arguments.of("tab\tseparated", "\t", 3),

                // repeated characters
                Arguments.of("mississippi", "issi", 1),
                Arguments.of("mississippi", "ssi", 2),
                Arguments.of("mississippi", "sip", 6),

                // case sensitivity
                Arguments.of("Hello World", "hello", -1),
                Arguments.of("Hello World", "Hello", 0),

                // whitespace
                Arguments.of("   spaces   ", " ", 0),
                Arguments.of("   spaces   ", "   ", 0),
                Arguments.of("   spaces   ", "spaces", 3),

                // numbers and special chars
                Arguments.of("123123", "123", 0),
                Arguments.of("!@#$%", "@#", 1),

                // long strings and patterns
                Arguments.of("very very very long string to search in", "very long string", 10),
                Arguments.of("aaaaaaaaaaaaaaaaaaaaaaaa", "aaaaaa", 0)
            )
        }
    }
}