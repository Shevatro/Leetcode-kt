package lc1_999.lc700_799

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

//Solved
//https://leetcode.com/problems/shortest-completing-word/description/
class Task748() {
    fun shortestCompletingWord(licensePlate: String, words: Array<String>): String {
        return Solution2(licensePlate, words).shortestCompletingWord()
    }

    private class Solution2(
        private val licensePlate: String,
        private val words: Array<String>
    ) {
        var licensePlateArr = mutableMapOf<Int, Int>()
        fun shortestCompletingWord(): String {
            licensePlateArr = countChs(licensePlate)
//             println(licensePlateArr.toList())
            var (wordLen, wordResult) = Int.MAX_VALUE to ""
            for (word in words) {
                if (word.length < wordLen && compareToOriginal(word)) {
                    wordLen = word.length
                    wordResult = word
                }
            }
            return wordResult
        }

        private fun countChs(word: String): MutableMap<Int, Int> {
            val result = mutableMapOf<Int, Int>()
            for (ch in word) {
                val code = ch.lowercaseChar() - 'a'
                if (code in 0..26) result.merge(code, 1, Int::plus)
            }
            return result
        }

        private fun compareToOriginal(word: String): Boolean {
            val wordArr = countChs(word)
//             println("compare:"+wordArr.toList())
            for (key in licensePlateArr.keys) {
                if (wordArr.getOrDefault(key, 0) < licensePlateArr.getOrDefault(key, 0)) return false
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