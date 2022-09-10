package lc1_999.lc200_299

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

//From Cracking The Coding Interview, Solved
//https://leetcode.com/problems/valid-anagram/

class Task242 {
    fun isAnagram(s: String, t: String): Boolean {
        if (s.length != t.length) return false
        val letters = countLettersDiff(t, s)
        return letters.all { it == 0 }
    }

    private fun countLettersDiff(s1: String, s2: String): IntArray {
        val letters = IntArray(26)
        for (i in s1.indices) {
            val code1 = s1[i] - 'a'
            val code2 = s2[i] - 'a'
            letters[code1]++
            letters[code2]--
        }
        return letters
    }
}

private class Task242Test {
    private val task = Task242()

    @Test
    fun isAnagram() {
        isAnagram("anagram", "nagaram", true)
        isAnagram("rat", "car", false)
    }

    private fun isAnagram(actualInp1: String, actualInp2: String, expected: Boolean) {
        val actual = task.isAnagram(actualInp1, actualInp2)
        println(actual)
        Assertions.assertEquals(expected, actual)
    }
}