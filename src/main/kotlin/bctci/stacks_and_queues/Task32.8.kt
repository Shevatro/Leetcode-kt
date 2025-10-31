package bctci.stacks_and_queues

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Task32_8() {

    fun longestBalancedSubsequence(s: String): String {
        return Solution(s).longestBalancedSubsequence()
    }

    class Solution(private val s: String) {
        private var extraOpeningBrackets = 0
        private var prunedStr = ""

        fun longestBalancedSubsequence(): String {
            removeIncorrectClosingBrackets()
            if (extraOpeningBrackets <= 0) return prunedStr
            return removeExtraOpeningBrackets()
        }

        private fun removeExtraOpeningBrackets(): String {
            val newSb = StringBuilder()
            var amountOpeningBracketsToSkip = 0
            for (i in prunedStr.length - 1 downTo 0) {
                val ch = prunedStr[i]
                //if it's already balanced just add the rest of the prunedStr
                if (extraOpeningBrackets == 0) {
                    newSb.append(ch)
                } else {
                    if (ch == ')') {
                        amountOpeningBracketsToSkip++
                        newSb.append(ch)
                    } else {
                        if (amountOpeningBracketsToSkip != 0) {
                            newSb.append(ch)
                            amountOpeningBracketsToSkip--
                        } else {
                            extraOpeningBrackets--
                        }
                    }
                }
            }
            return newSb.reversed().toString()
        }

        private fun removeIncorrectClosingBrackets() {
            val str = StringBuilder()
            for (ch in s) {
                if (ch == '(') {
                    extraOpeningBrackets++
                    str.append(ch)
                } else if (extraOpeningBrackets > 0) {
                    str.append(ch)
                    extraOpeningBrackets--
                }
            }
            prunedStr = str.toString()
        }
    }
}

private class Task32_8Test {
    private val task = Task32_8()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input: String, expected: Set<String>) {
        Assertions.assertTrue(task.longestBalancedSubsequence(input) in expected)
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of("))(())(()", setOf("(())()")),
                Arguments.of("(()()", setOf("()()", "(())")),
                Arguments.of("())(()", setOf("()()")),
                Arguments.of("(", setOf("")),
                Arguments.of("((()", setOf("()"))
            )
        }
    }
}