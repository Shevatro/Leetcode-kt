package bctci.two_pointers

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Task27_18 {

    fun shiftWordToBack(arr: CharArray, word: String) {
        return Solution(arr, word).shiftWordToBack()
    }

    private class Solution(
        private val arr: CharArray,
        private val word: String
    ) {
        fun shiftWordToBack() {
            //seekerandwriter -> sekeranwreriter
            removeLettersFromWord()
            //sekeranwreriter ->sekeranwreredit
            copyWordAtTheEnd()
        }

        private fun removeLettersFromWord() {
            var wordInd = 0
            var writePos = 0
            for (readPos in arr.indices) {
                if (wordInd < word.length && arr[readPos] == word[wordInd]) {
                    wordInd++
                } else {
                    arr[writePos] = arr[readPos]
                    writePos++
                }
            }
        }

        private fun copyWordAtTheEnd() {
            var wordInd = 0
            for (i in arr.size - word.length until arr.size) {
                arr[i] = word[wordInd]
                wordInd++
            }
        }
    }
}


private class Task27_18Test {
    private val task = Task27_18()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input1: CharArray, input2: String, expected: CharArray) {
        task.shiftWordToBack(input1, input2)
        Assertions.assertArrayEquals(input1, expected)
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of("seekerandwriter".toCharArray(), "edit", "sekeranwreredit".toCharArray()),
                Arguments.of("bacb".toCharArray(), "ab", "bcab".toCharArray()),
                Arguments.of("babc".toCharArray(), "b", "abcb".toCharArray()),
                Arguments.of("".toCharArray(), "", "".toCharArray()),
                Arguments.of("a".toCharArray(), "a", "a".toCharArray()),
                Arguments.of("abc".toCharArray(), "", "abc".toCharArray()),
                Arguments.of("hello".toCharArray(), "ho", "ellho".toCharArray()),
                Arguments.of("abcabc".toCharArray(), "abc", "abcabc".toCharArray())
            )
        }
    }
}