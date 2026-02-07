package bctci.two_pointers

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Task27_4 {
    fun isSentencePalindromic(s: String): Boolean {
        var startPos = 0
        var endPos = s.lastIndex
        while (startPos < endPos) {
            if (!s[startPos].isLetter() || !s[endPos].isLetter()) {
                if (!s[startPos].isLetter()) startPos++
                if (!s[endPos].isLetter()) endPos--
                continue
            }
            if (s[startPos].lowercaseChar() != s[endPos].lowercaseChar()) {
                return false
            }
            startPos++
            endPos--
        }
        return true
    }

    fun isSentencePalindromicSimplified(s: String): Boolean {
        val prunedStr = prune(s)
        return isPalindrome(prunedStr)
    }

    private fun prune(s: String): String {
        val sb = StringBuilder()
        for (ch in s) {
            if (ch.isLetter()) {
                val lowerCaseCh = if (ch.isUpperCase()) ch.lowercaseChar() else ch
                sb.append(lowerCaseCh)
            }
        }
        return sb.toString()
    }

    private fun isPalindrome(str: String): Boolean {
        var startPos = 0
        var endPos = str.lastIndex
        while (startPos < endPos) {
            if (str[startPos] != str[endPos]) return false
            startPos++
            endPos--
        }
        return true
    }
}


private class Task27_4Test {
    private val task = Task27_4()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input: String, expected: Boolean) {
        Assertions.assertEquals(expected, task.isSentencePalindromic(input))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of("Bob wondered, 'Now, Bob?'", true),
                Arguments.of("", true),
                Arguments.of("a", true),
                Arguments.of("A man, a plan, a canal: Panama", true),
                Arguments.of("race a car", false),
                Arguments.of("Was it a car or a cat I saw?", true),
                Arguments.of("hello", false),
                Arguments.of(".,?!'", true)
            )
        }
    }
}