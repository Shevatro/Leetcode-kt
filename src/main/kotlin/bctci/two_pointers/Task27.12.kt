package bctci.two_pointers

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Task27_12 {

    fun reverseArray(arr: CharArray) {
        var startPos = 0
        var endPos = arr.lastIndex
        while (startPos < endPos) {
            val temp = arr[startPos]
            arr[startPos] = arr[endPos]
            arr[endPos] = temp
            startPos++
            endPos--
        }
    }
}


private class Task27_12Test {
    private val task = Task27_12()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input: String, expected: String) {
        val inputArr = input.toCharArray()
        task.reverseArray(inputArr)
        Assertions.assertArrayEquals(expected.toCharArray(), inputArr)
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of("hello", "olleh"),
                Arguments.of("", ""),
                Arguments.of("a", "a"),
                Arguments.of("ab", "ba"),
                Arguments.of("abc", "cba"),
                Arguments.of("abcd", "dcba")
            )
        }
    }
}