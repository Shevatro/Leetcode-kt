package lc1_999.lc300_399

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

//From Beyond Cracking The Coding Interview, Solved
//https://leetcode.com/problems/reverse-string/
class Task344 {
    fun reverseString(s: CharArray): Unit {
        var startPos = 0
        var endPos = s.lastIndex
        while (startPos < endPos) {
            val temp = s[startPos]
            s[startPos] = s[endPos]
            s[endPos] = temp
            startPos++
            endPos--
        }
    }
}

private class Task344Test {
    private val task = Task344()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input: CharArray, expected: CharArray) {
        task.reverseString(input)
        Assertions.assertArrayEquals(expected, input)
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(charArrayOf('h', 'e', 'l', 'l', 'o'), charArrayOf('o', 'l', 'l', 'e', 'h')),
                Arguments.of(charArrayOf('H', 'a', 'n', 'n', 'a', 'h'), charArrayOf('h', 'a', 'n', 'n', 'a', 'H'))
            )
        }
    }
}