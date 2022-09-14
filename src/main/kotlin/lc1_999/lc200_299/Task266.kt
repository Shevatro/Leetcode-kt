package lc1_999.lc200_299

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

//From Cracking The Coding Interview, Solved
//https://leetcode.com/problems/palindrome-permutation/

class Task266 {
    fun canPermutePalindrome(s: String): Boolean {
        val letters = getLettersFrequency(s)
        return isPairsEnough(s, letters)
    }

    private fun getLettersFrequency(s: String): IntArray {
        val letters = IntArray(26)
        for (ch in s) {
            val index = ch - 'a'
            letters[index]++
        }
        return letters
    }

    private fun isPairsEnough(s: String, letters: IntArray): Boolean {
        val canBeOdd = s.length % 2 != 0
        var oddFound = false
        for (amount in letters) {
            if (amount % 2 != 0) {
                if (canBeOdd && !oddFound) {
                    oddFound = true
                } else {
                    return false
                }
            }
        }
        return true
    }
}

private class Task266Test {
    private val task = Task266()

    @Test
    fun canPermutePalindrome() {
        canPermutePalindrome("code", false)
        canPermutePalindrome("aab", true)
        canPermutePalindrome("carerac", true)
        canPermutePalindrome("aa", true)
        canPermutePalindrome("ab", false)
        canPermutePalindrome("a", true)
    }

    private fun canPermutePalindrome(actualInp1: String, expected: Boolean) {
        val actual = task.canPermutePalindrome(actualInp1)
        println(actual)
        Assertions.assertEquals(expected, actual)
    }
}