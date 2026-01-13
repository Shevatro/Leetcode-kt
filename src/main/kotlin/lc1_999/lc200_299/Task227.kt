package lc1_999.lc200_299

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

// Solved
//https://leetcode.com/problems/basic-calculator-ii/
class Task227 {
    fun calculate(s: String): Int {
        if (s.length == 1) return s[0].digitToInt()
        val topPriorityOperations = charArrayOf('*', '/')
        val stack = ArrayDeque<Any>()
        var number = 0
        for (i in s.indices) {
            val ch = s[i]
            if (ch == ' ') continue
            if (ch.isDigit()) {
                number = number * 10 + ch.digitToInt()
                val nextPos = i + 1
                if (nextPos >= s.length || !s[nextPos].isDigit()) {
                    val prev = stack.firstOrNull()
                    if (prev is Char && (prev in topPriorityOperations)) {
                        //remove this operation
                        stack.removeFirst()
                        //apply this operation:
                        val previousNumber = stack.removeFirst() as Int
                        val newNumber = if (prev == '*') {
                            previousNumber * number
                        } else {
                            previousNumber / number
                        }
                        stack.addFirst(newNumber)
                    } else {
                        stack.addFirst(number)
                    }
                    number = 0
                }
            } else {
                //if just operations
                stack.addFirst(ch)
            }
        }
        return calculateBackward(stack)
    }

    private fun calculateBackward(stack: ArrayDeque<Any>): Int {
        var num = stack.removeLast() as Int
        while (stack.isNotEmpty()) {
            val oper = stack.removeLast()
            val next = stack.removeLast()
            if (oper == '+') {
                num += next as Int
            } else {
                num -= next as Int
            }
        }
        return num
    }
}

private class Task227Test {
    private val task = Task227()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input: String, expected: Int) {
        Assertions.assertEquals(expected, task.calculate(input))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of("3+2*2", 7),
                Arguments.of(" 3/2 ", 1),
                Arguments.of(" 3+5 / 2 ", 5),
                Arguments.of("323+22*21", 785),
                Arguments.of("33+22*21-33+22*21", 924),
                Arguments.of("3", 3)
            )
        }
    }
}