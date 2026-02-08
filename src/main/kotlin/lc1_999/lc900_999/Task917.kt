package lc1_999.lc900_999

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

//Similar to Beyond Cracking The Coding Interview, Solved
//https://leetcode.com/problems/reverse-only-letters/
class Task917 {
    fun reverseOnlyLetters(s: String): String {
        val arr = s.toCharArray()
        var startPos = 0
        var endPos = arr.lastIndex
        while (startPos < endPos) {
            if (!arr[startPos].isLetter()) {
                startPos++
                continue
            }
            if (!arr[endPos].isLetter()) {
                endPos--
                continue
            }

            val temp = arr[startPos]
            arr[startPos] = arr[endPos]
            arr[endPos] = temp

            startPos++
            endPos--
        }
        return arr.concatToString()
    }
}

private class Task917Test {
    private val task = Task917()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input: String, expected: String) {
        Assertions.assertEquals(expected, task.reverseOnlyLetters(input))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of("ab-cd", "dc-ba"),
                Arguments.of("a-bC-dEf-ghIj", "j-Ih-gfE-dCba"),
                Arguments.of("Test1ng-Leet=code-Q!", "Qedo1ct-eeLg=ntse-T!"),
                Arguments.of("a-bcd", "d-cba"),
                Arguments.of("-abcd", "-dcba"),
                Arguments.of("---cd", "---dc"),
                Arguments.of("-----", "-----")
            )
        }
    }
}