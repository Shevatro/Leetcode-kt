package lc1_999.lc1_99

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.math.max

//From Beyond Cracking The Coding Interview, Solved
//https://leetcode.com/problems/longest-valid-parentheses/
class Task32() {
    fun longestValidParentheses(s: String): Int {
        val status = fillOutStatuses(s)
        return findMaxValidSubstringSize(status)
    }

    private fun fillOutStatuses(s: String): BooleanArray {
        val status = BooleanArray(s.length)
        val stack = ArrayDeque<Data>()
        for (i in 0 until s.length) {
            val ch = s[i]
            if (ch == '(') {
                stack.addFirst(Data(ch, i))
            } else if (stack.isNotEmpty()) {
                val openingPos = stack.removeFirst().pos
                status[i] = true
                status[openingPos] = true
            }
        }
        return status
    }

    private fun findMaxValidSubstringSize(status: BooleanArray): Int {
        var max = 0
        var count = 0
        for (item in status) {
            if (item) {
                count++
            } else {
                max = max(max, count)
                count = 0
            }
        }
        return max(max, count)
    }

    private data class Data(
        val value: Char,
        val pos: Int
    )
}

private class Task32Test {
    private val task = Task32()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input: String, expected: Int) {
        Assertions.assertEquals(expected, task.longestValidParentheses(input))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of("(()", 2),
                Arguments.of(")()())", 4),
                Arguments.of("", 0),
                Arguments.of("(((", 0),
                Arguments.of(")))", 0),
                Arguments.of("((()()))", 8),
                Arguments.of("()(()", 2)
            )
        }
    }
}