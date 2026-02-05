package bctci.two_pointers

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Task27_1 {
    fun chekPalindrome(s: String): Boolean {
        if (s.length <= 1) return true
        var startPos = 0
        var endPos = s.lastIndex
        while (startPos < endPos) {
            if (s[startPos] != s[endPos]) return false
            startPos++
            endPos--
        }
        return true
    }
}


private class Task27_1Test {
    private val task = Task27_1()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input: String, expected: Boolean) {
        Assertions.assertEquals(expected, task.chekPalindrome(input))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of("level", true),
                Arguments.of("naan", true),
                Arguments.of("", true),
                Arguments.of("a", true),
                Arguments.of("ab", false),
                Arguments.of("abc", false),
                Arguments.of("abba", true),
                Arguments.of("abcba", true)
            )
        }
    }
}