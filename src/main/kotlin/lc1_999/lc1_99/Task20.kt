package lc1_999.lc1_99

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

//Solved
//https://leetcode.com/problems/valid-parentheses/
class Task20 {
    fun isValid(s: String): Boolean {
        val openToClose = mapOf('(' to ')', '[' to ']', '{' to '}')
        val openPar = setOf('(', '[', '{')
        val stack = ArrayDeque<Char>()
        for (ch in s) {
            if (ch in openPar) {
                stack.addFirst(openToClose[ch]!!)
            } else if (stack.isEmpty()) {
                return false
            } else {
                val expectedPar = stack.removeFirst()
                if (ch != expectedPar) return false
            }
        }
        return stack.isEmpty()
    }
}

private class Task20Test {
    private val task = Task20()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input: String, expected: Boolean) {
        Assertions.assertEquals(expected, task.isValid(input))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of("()", true),
                Arguments.of("()[]{}", true),
                Arguments.of("(]", false),
                Arguments.of("([])", true),
                Arguments.of("([)]", false),
                Arguments.of("(([[]]))", true),
                Arguments.of("[", false),
                Arguments.of("]", false)
            )
        }
    }
}