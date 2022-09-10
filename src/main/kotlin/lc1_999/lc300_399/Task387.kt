package lc1_999.lc300_399

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

//From Cracking The Coding Interview, Solved
//https://leetcode.com/problems/contains-duplicate/

private const val LOWER_CASE_LETTERS_AMOUNT = 26
private const val FIRST_LETTER_ASCII_POS = 97

class Task387 {
    fun firstUniqChar(s: String): Int {
        val lettersAmount = fillLettersAmount(s)
        for (i in s.indices) {
            val item = s[i].code - FIRST_LETTER_ASCII_POS
            if (lettersAmount[item] == 1) {
                return i
            }
        }
        return -1
    }

    private fun fillLettersAmount(s: String): IntArray {
        val lettersAmount = IntArray(LOWER_CASE_LETTERS_AMOUNT)
        for (i in s.indices) {
            val item = s[i].code - FIRST_LETTER_ASCII_POS
            lettersAmount[item]++
        }
        return lettersAmount
    }
}

private class Task387Test {
    private val task = Task387()

    @Test
    fun firstUniqChar() {
        firstUniqChar("leetcode", 0)
        firstUniqChar("loveleetcode", 2)
        firstUniqChar("aabb", -1)
    }

    private fun firstUniqChar(actualInp: String, expected: Int) {
        val actual = task.firstUniqChar(actualInp)
        println(actual)
        Assertions.assertEquals(expected, actual)
    }
}