package lc1_999.lc500_599

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

//Solved but with a hint
//https://leetcode.com/problems/permutation-in-string/
class Task567 {
    fun checkInclusion(s1: String, s2: String): Boolean {
        if (s1.length > s2.length) return false
        val frequencyS1 = IntArray(26)
        populateFrequency(frequencyS1, s1)
        for (i in s2.indices) {
            val frequencyS2 = IntArray(26)
            try {
                val s2Sub = s2.substring(i, s1.length + i)
                populateFrequency(frequencyS2, s2Sub)
                if (match(frequencyS1, frequencyS2)) return true
            } catch (e: IndexOutOfBoundsException) {
                return false
            }
        }
        return false
    }

    private fun populateFrequency(frequency: IntArray, s: String) {
        for (ch in s) {
            frequency[ch - 'a']++
        }
    }

    private fun match(frequencyS1: IntArray, frequencyS2: IntArray): Boolean {
        for (i in frequencyS1.indices) {
            if (frequencyS1[i] != frequencyS2[i]) return false
        }
        return true
    }
}

private class Task567Test {
    private val task = Task567()

    @Test
    fun checkInclusionTest() {
        Assertions.assertEquals(true, task.checkInclusion("ab", "eidbaooo"))
        Assertions.assertEquals(false, task.checkInclusion("ab", "eidboaoo"))
        Assertions.assertEquals(true, task.checkInclusion("eidboaoo", "eidboaoo"))
        Assertions.assertEquals(true, task.checkInclusion("oidboaoe", "eidboaoo"))
        Assertions.assertEquals(false, task.checkInclusion("hello", "ooolleoooleh"))
    }
}