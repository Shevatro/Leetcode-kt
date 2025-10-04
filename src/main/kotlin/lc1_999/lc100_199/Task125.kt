package lc1_999.lc100_199

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

//Solved
//https://leetcode.com/problems/valid-palindrome
class Task125 {
    fun isPalindrome(s: String): Boolean {
        var startInd = 0
        var endInd = s.length - 1
        while (startInd < endInd) {
            val startCh = s[startInd].lowercaseChar()
            val endCh = s[endInd].lowercaseChar()
            val isStartChValid = isValid(startCh)
            val isEndChValid = isValid(endCh)
            if (isStartChValid && isEndChValid){
                if (startCh != endCh) return false
                startInd++
                endInd--
            }else{
                if (!isStartChValid) startInd++
                if (!isEndChValid) endInd--
            }
        }
        return true
    }

    //also it's possible to use ch.isLetterOrDigit()
    private fun isValid(ch: Char) = ch in 'a'..'z' || ch in '0'..'9'
}

private class Task125Test {
    private val task = Task125()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input: String, expected: Boolean) {
        Assertions.assertEquals(expected, task.isPalindrome(input))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of("A man, a plan, a canal: Panama", true),
                Arguments.of("race a car", false),
                Arguments.of(" ", true),
                Arguments.of("A", true),
                Arguments.of("0P", false)
            )
        }
    }
}