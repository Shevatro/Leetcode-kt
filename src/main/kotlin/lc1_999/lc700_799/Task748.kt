package lc1_999.lc700_799

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

//From an interview, Solved
//https://leetcode.com/problems/shortest-completing-word/description/
class Task748() {
    fun shortestCompletingWord(licensePlate: String, words: Array<String>): String {
        return Solution2(licensePlate, words).shortestCompletingWord()
    }

    private class Solution2(
        private val licensePlate: String,
        private val words: Array<String>
    ) {
        var licensePlateChsFrequency = IntArray(26)
        fun shortestCompletingWord(): String {
            countChsFrequencyInLicensePlate()
            return getShortestWord()
        }

        private fun getShortestWord(): String {
            var (wordLen, wordResult) = Int.MAX_VALUE to ""
            for (word in words) {
                if (word.length < wordLen && compareToLicensePlate(word)) {
                    wordLen = word.length
                    wordResult = word
                }
            }
            return wordResult
        }

        private fun countChsFrequencyInLicensePlate() {
            for (ch in licensePlate) {
                val code = ch.lowercaseChar() - 'a'
                if (code in 0..26) licensePlateChsFrequency[code]++
            }
        }

        private fun getChsFrequency(word: String): IntArray {
            val result = IntArray(26)
            for (ch in word) {
                val code = ch - 'a'
                result[code]++
            }
            return result
        }

        private fun compareToLicensePlate(word: String): Boolean {
            val wordArr = getChsFrequency(word)
            for (i in licensePlateChsFrequency.indices) {
                if (wordArr[i] < licensePlateChsFrequency[i]) return false
            }
            return true
        }
    }
}

private class Task748Test {
    private val task = Task748()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(licensePlate: String, words: Array<String>, expected: String) {
        Assertions.assertEquals(expected, task.shortestCompletingWord(licensePlate, words))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of("1s3 PSt", arrayOf("step", "steps", "stripe", "stepple"), "steps"),
                Arguments.of("1s3 456", arrayOf("looks", "pest", "stew", "show"), "pest"),
            )
        }
    }
}