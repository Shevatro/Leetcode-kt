package lc1_999.lc200_299

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

// Solved
//https://leetcode.com/problems/basic-calculator/
class Task224 {
    fun calculate(s: String): Int {
        return Solution("($s)").calculate()
    }

    private class Solution(private val s: String) {

        fun calculate(): Int {
            val stack = ArrayDeque<Any>()
            var num = 0
            var negativeFlag = 1
            for (i in s.indices) {
                val ch = s[i]
                if (ch == ' ') continue
                if (ch.isDigit()) {
                    num = num * 10 + ch.digitToInt()
                    if (!s[i + 1].isDigit()) {
                        stack.addFirst(num * negativeFlag)
                        num = 0
                    }
                } else if (ch == '(') {
                    if (negativeFlag == 1) {
                        stack.addFirst('+')
                    } else {
                        stack.addFirst('-')
                    }
                    //reset flag, we don't want to influence the num yet
                    negativeFlag = 1
                } else if (ch == '+') {
                    negativeFlag = 1
                } else if (ch == '-') {
                    negativeFlag = -1
                    //if ')'
                } else {
                    var sum = 0
                    while (stack.first() != '-' && stack.first() != '+') {
                        sum += stack.removeFirst() as Int
                    }
                    //remove '(' too
                    val op = stack.removeFirst()
                    if (op == '-') sum *= -1
                    stack.addFirst(sum)
                }
            }
            return stack.removeFirst() as Int
        }
    }

}

private class Task224Test {
    private val task = Task224()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input: String, expected: Int) {
        Assertions.assertEquals(expected, task.calculate(input))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of("1 + 1", 2),
                Arguments.of(" 2-1 + 2 ", 3),
                Arguments.of("(1+(4+5+2)-3)+(6+8)", 23),
                Arguments.of("(1+(4+5+2)-3)-(6+8)", -5),
                Arguments.of("(11+(45+54+222)-3)-(66+88)", 175)
            )
        }
    }
}