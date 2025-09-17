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
        var licensePlateArr = IntArray(0)
        fun shortestCompletingWord(): String {
            licensePlateArr = countChs(licensePlate)
            // println(licensePlateArr.toList())
            for (word in words.sortedBy { it.length }) {
                if (compareToOriginal(word)) return word
            }
            return ""
        }

        private fun countChs(word: String): IntArray {
            val result = IntArray(26)
            for (ch in word) {
                val code = ch.lowercaseChar() - 'a'
                // println("code:"+code)
                if (code in 0..26) result[code]++
            }
            return result
        }

        private fun compareToOriginal(word: String): Boolean {
            val wordArr = countChs(word)
            // println("compare:"+wordArr.toList())
            for (i in licensePlateArr.indices) {
                if (wordArr[i] < licensePlateArr[i]) return false
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