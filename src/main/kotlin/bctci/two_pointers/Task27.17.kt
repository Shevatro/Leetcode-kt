package bctci.two_pointers

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Task27_17 {

    fun swapPrefixSuffix(arr: CharArray) {
        return Solution(arr).swapPrefixSuffix()
    }

    //Note: the same idea as Leetcode 8. Rotate Image
    private class Solution(private val arr: CharArray) {
        fun swapPrefixSuffix() {
            //prefix = arr.size / 3, suffix = arr.size * 2 / 3
            val turningPoint = arr.size / 3
            //ab cdef -> ba cdef
            rotate(0, turningPoint - 1)
            //ba cdef -> ba fedc
            rotate(turningPoint, arr.lastIndex)
            //ba fedc -> cd efab
            rotate(0, arr.lastIndex)
        }

        private fun rotate(startPos_: Int, endPos_: Int) {
            var startPos = startPos_
            var endPos = endPos_
            while (startPos < endPos) {
                arr[startPos] = arr[endPos].also { arr[endPos] = arr[startPos] }
                startPos++
                endPos--
            }
        }
    }
}


private class Task27_17Test {
    private val task = Task27_17()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input: CharArray, expected: CharArray) {
        task.swapPrefixSuffix(input)
        Assertions.assertArrayEquals(input, expected)
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of("badreview".toCharArray(), "reviewbad".toCharArray()),
                Arguments.of("".toCharArray(), "".toCharArray()),
                Arguments.of("abc".toCharArray(), "bca".toCharArray()),
                Arguments.of("abcdef".toCharArray(), "cdefab".toCharArray()),
                Arguments.of("123456789".toCharArray(), "456789123".toCharArray()),
                Arguments.of("aaabbbccc".toCharArray(), "bbbcccaaa".toCharArray())
            )
        }
    }
}