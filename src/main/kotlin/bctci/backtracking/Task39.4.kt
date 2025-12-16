package bctci.backtracking

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Task39_4 {
    fun shakespearify(sentence: String): Set<String> {
        return Solution(sentence).shakespearify()
    }

    private class Solution(sentence: String) {
        private val words = sentence.split(" ")
        private val result = mutableSetOf<String>()
        private val current = ArrayDeque<String>()

        fun shakespearify(): Set<String> {
            backtrack(0)
            return result
        }

        private fun backtrack(i: Int) {
            if (i == words.size) {
                result.add(current.joinToString(" "))
                return
            }
            current.addLast(words[i])
            backtrack(i + 1)
            current.removeLast()
            backtrack(i + 1)
        }
    }
}


private class Task39_4Test {
    private val task = Task39_4()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input: String, expected: Set<String>) {
        Assertions.assertEquals(expected, task.shakespearify(input))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    "I love dogs",
                    setOf("", "I", "love", "dogs", "I love", "I dogs", "love dogs", "I love dogs")
                ),
                Arguments.of("hello", setOf("", "hello")),
                Arguments.of("", setOf("")),
                Arguments.of("hello world", setOf("", "hello", "world", "hello world"))
            )
        }
    }
}