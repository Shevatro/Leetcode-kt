package lc1_999.lc500_599

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

//Solved but with a hint
//https://leetcode.com/problems/permutation-in-string/
class Task567 {
    fun checkInclusion(s1: String, s2: String): Boolean {
        return Solution(s1, s2).solve()
    }

    private class Solution(private val s1: String, private val s2: String) {
        private val frequencyS1 = IntArray(26)
        private val frequencyS2 = IntArray(26)
        private var amountNotEqual = 0

        fun solve(): Boolean {
            if (s1.length > s2.length) return false
            populateInitState()
            amountNotEqual = countNotEqual()
            if (isAllEquals()) return true
            for (i in 1..s2.length - s1.length) {
                val previousItem = s2[i - 1] - 'a'
                val newItem = s2[i + s1.length - 1] - 'a'
                if (previousItem == newItem) continue
                shiftFrequency(previousItem, -1)
                shiftFrequency(newItem, 1)
                if (isAllEquals()) return true
            }
            return false
        }

        private fun populateInitState() {
            for (i in s1.indices) {
                frequencyS1[s1[i] - 'a']++
                frequencyS2[s2[i] - 'a']++
            }
        }


        private fun countNotEqual(): Int {
            var count = 0
            for (i in frequencyS1.indices) {
                if (isFrequencyDifferent(i)) count++
            }
            return count
        }

        private fun isFrequencyDifferent(item: Int) = frequencyS1[item] != frequencyS2[item]

        private fun isAllEquals() = amountNotEqual == 0

        private fun shiftFrequency(item: Int, frequencyDiff: Int) {
            val diffFrequencyIndicatorBefore = isFrequencyDifferent(item).toInt()
            frequencyS2[item] += frequencyDiff
            val diffFrequencyIndicatorAfter = isFrequencyDifferent(item).toInt()
            amountNotEqual += diffFrequencyIndicatorAfter - diffFrequencyIndicatorBefore
        }

        private fun Boolean.toInt() = if (this) 1 else 0
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