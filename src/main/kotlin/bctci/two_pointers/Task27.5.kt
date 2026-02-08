package bctci.two_pointers

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Task27_5 {
    fun mathReverseCase(s: String): Boolean {
        val lowerCasePart = s.filter { it.isLowerCase() }
        val upperCasePart = s.filter { it.isUpperCase() }
        if (lowerCasePart.length != upperCasePart.length) return false
        var startPos = 0
        var endPos = upperCasePart.lastIndex
        while (startPos < endPos) {
            if (lowerCasePart[startPos] != upperCasePart[endPos].lowercaseChar()) return false
            startPos++
            endPos--
        }
        return true
    }
}


private class Task27_5Test {
    private val task = Task27_5()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input: String, expected: Boolean) {
        Assertions.assertEquals(expected, task.mathReverseCase(input))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of("haDrRAHd'", true),
                Arguments.of("haHrARDd", false),
                Arguments.of("", true),
                Arguments.of("aA", true),
                Arguments.of("Aa", true),
                Arguments.of("BbbB", true),
                Arguments.of("abAB", false),
                Arguments.of("abBA", true),
                Arguments.of("helloworldHELLOWORLD", false)
            )
        }
    }
}