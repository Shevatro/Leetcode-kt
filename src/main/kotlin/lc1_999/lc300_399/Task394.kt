package lc1_999.lc300_399

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

//From Beyond Cracking The Coding Interview, Solved
//https://leetcode.com/problems/decode-string/
class Task394() {
    fun decodeString(s: String): String {
        return Solution(s).decodeString()
    }

    private class Solution(s: String) {
        val arr = s.toCharArray()
        val stack = ArrayDeque<String>()
        var buffer = ""
        fun decodeString(): String {
            for (ch in arr) {
                if (ch.isDigit()) {
                    //do not clean up a number but do it for old buffer with letters
                    if (buffer.isNotEmpty() && !buffer.last().isDigit()) {
                        stack.addFirst(buffer)
                        buffer = ""
                    }
                    buffer += ch
                } else if (ch == '[') {
                    stack.addFirst(buffer)
                    stack.addFirst("[")
                    buffer = ""
                } else if (ch == ']') {
                    stack.addFirst(buffer)
                    buffer = ""
                    val textInBrackets = getTextFromBrackets()
                    //remove "["
                    stack.removeFirst()
                    stack.addFirst(getCopyNTime(textInBrackets))
                } else {
                    buffer += ch
                }
            }
            if (buffer.isNotEmpty()) stack.addFirst(buffer)
            return combineResult()
        }

        private fun getTextFromBrackets(): String {
            var textInBrackets = ""
            while (stack.isNotEmpty() && stack.first() != "[") {
                textInBrackets += if (stack.first().length == 1) {
                    stack.removeFirst()
                } else {
                    stack.removeFirst().reversed()
                }
            }
            return textInBrackets.reversed()
        }

        private fun getCopyNTime(str: String): String {
            val repeatTimes = stack.removeFirst().toInt()
            if (repeatTimes == 1) return str
            var result = ""
            repeat(repeatTimes) {
                result += str
            }
            return result
        }

        private fun combineResult(): String {
            var result = ""
            while (stack.isNotEmpty()) {
                result += stack.removeLast()
            }
            return result
        }
    }
}

private class Task394Test {
    private val task = Task394()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input: String, expected: String) {
        Assertions.assertEquals(expected, task.decodeString(input))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of("3[a]2[bc]", "aaabcbc"),
                Arguments.of("3[a2[c]]", "accaccacc"),
                Arguments.of("2[abc]3[cd]ef", "abcabccdcdcdef"),
                Arguments.of("abc", "abc"),
                Arguments.of(
                    "100[leetcode]",
                    "leetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcode"
                ),
                Arguments.of(
                    "3[z]2[2[y]pq4[2[jk]e1[f]]]ef",
                    "zzzyypqjkjkefjkjkefjkjkefjkjkefyypqjkjkefjkjkefjkjkefjkjkefef"
                )
            )
        }
    }
}