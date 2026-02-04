package lc1_999.lc1_99

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

//From Beyond Cracking The Coding Interview, Solved
//Note: There are more efficient algorithms like KMP and rolling hash that can solve this in O(sn) time
//https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/description/
class Task28 {
    fun strStr(haystack: String, needle: String): Int {
        return Solution(haystack, needle).strStr()
    }

    private class Solution(
        private val haystack: String,
        private val needle: String
    ) {
        private val needleLength = needle.length
        private val haystackSize = haystack.length
        private val needleLastInd = needleLength - 1
        fun strStr(): Int {
            if (needleLength > haystackSize) return -1
            for (i in 0 until haystackSize - needleLastInd) {
                //check the first and last straw
                if (haystack[i] == needle[0] && haystack[i + needleLastInd] == needle[needleLastInd]) {
                    if (matches(i)) return i
                }
            }
            return -1
        }

        private fun matches(startPos: Int): Boolean {
            if (startPos + needleLength > haystackSize) return false
            var curNeedleInd = startPos
            for (needlePart in needle) {
                if (needlePart != haystack[curNeedleInd]) return false
                curNeedleInd++
            }
            return true
        }
    }
}

private class Task28Test {
    private val task = Task28()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input1: String, input2: String, expected: Int) {
        Assertions.assertEquals(expected, task.strStr(input1, input2))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of("sadbutsad", "sad", 0),
                Arguments.of("leetcode", "leeto", -1),
                Arguments.of("mississippi", "issip", 4),
                Arguments.of("a", "a", 0)
            )
        }
    }
}