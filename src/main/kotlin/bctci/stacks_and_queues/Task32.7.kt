package bctci.stacks_and_queues

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Task32_7 {

    fun isValidCustomBrackets(s: String, brackets: Array<String>): Boolean {
        val openToCloseMap = brackets.associateBy({ it[0] }, { it[1] })
        val closeBrackets = openToCloseMap.values.toSet()
        val stack = ArrayDeque<Char>()
        for (ch in s) {
            when {
                openToCloseMap[ch] != null -> stack.addFirst(requireNotNull(openToCloseMap[ch]))
                ch == stack.firstOrNull() -> stack.removeFirst()
                ch in closeBrackets -> return false
            }
        }

        return stack.isEmpty()
    }
}

private class Task32_7Test {
    private val task = Task32_7()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input1: String, input2: Array<String>, expected: Boolean) {
        Assertions.assertEquals(expected, task.isValidCustomBrackets(input1, input2))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of("((a+b)*[c-d]-{e/f})", arrayOf("()", "[]", "{}"), true),
                Arguments.of("()[}", arrayOf("()", "[]", "{}"), false),
                Arguments.of("([)]", arrayOf("()", "[]", "{}"), false),
                Arguments.of("<div> hello :) </div>", arrayOf("<>", "()"), false),
                Arguments.of(")))(()((", arrayOf(")("), true),
                Arguments.of("()", arrayOf("()"), true),
            )
        }
    }
}