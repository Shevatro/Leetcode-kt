package lc1_999.lc500_599

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

//Solved but with a hint
//https://leetcode.com/problems/permutation-in-string/
class Task567 {
    fun checkInclusion(s1: String, s2: String): Boolean {
        if (s1.length > s2.length) return false
        val (frequencyS1, frequencyS2) = populateInitState(s1, s2)
        var amountNotEqual = countNotEqual(frequencyS1, frequencyS2)
        if (amountNotEqual == 0) return true
        for (i in 1..s2.length - s1.length) {
            val previous = s2[i - 1] - 'a'
            val next = s2[i + s1.length - 1] - 'a'
            if (previous == next) continue
            val previousDifferentNotEqual = frequencyS1[previous] != frequencyS2[previous]
            val nextDifferentNotEqual = frequencyS1[next] != frequencyS2[next]
            frequencyS2[previous]--
            frequencyS2[next]++
            val previousDifferentAfterNotEqual = frequencyS1[previous] != frequencyS2[previous]
            val nextDifferentAfterNotEqual = frequencyS1[next] != frequencyS2[next]
            if (previousDifferentNotEqual && !previousDifferentAfterNotEqual) amountNotEqual--
            if (!previousDifferentNotEqual && previousDifferentAfterNotEqual) amountNotEqual++
            if (nextDifferentNotEqual && !nextDifferentAfterNotEqual) amountNotEqual--
            if (!nextDifferentNotEqual && nextDifferentAfterNotEqual) amountNotEqual++
            if (amountNotEqual == 0) return true
        }
        return false
    }

    private fun populateInitState(s1: String, s2: String): Pair<IntArray, IntArray> {
        val frequencyS1 = IntArray(26)
        val frequencyS2 = IntArray(26)
        for (i in s1.indices) {
            frequencyS1[s1[i] - 'a']++
            frequencyS2[s2[i] - 'a']++
        }
        return frequencyS1 to frequencyS2
    }


    private fun countNotEqual(frequencyS1: IntArray, frequencyS2: IntArray): Int {
        var count = 0
        for (i in frequencyS1.indices) {
            if (frequencyS1[i] != frequencyS2[i]) count++
        }
        return count
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
        Assertions.assertEquals(true, task.checkInclusion("adc", "dcda"))
    }
}