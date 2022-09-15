package lc1_999.lc100_199

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.math.abs

//From Cracking The Coding Interview, Solved
//https://leetcode.com/problems/one-edit-distance/

class Task161 {
    fun isOneEditDistance(s: String, t: String): Boolean {
        if (abs(s.length - t.length) > 1) {
            return false
        }
        val isFirstStrLonger = s.length > t.length
        val strLong = if (isFirstStrLonger) s else t
        val strShort = if (isFirstStrLonger) t else s
        val isLengthEqual = s.length == t.length
        var j = 0
        var diffAmount = 0
        for (i in strLong.indices) {
            if (j >= strShort.length) {
                diffAmount++
                break
            }
            if (strLong[i] == strShort[j]) {
                j++
            } else {
                diffAmount++
                if (isLengthEqual) j++
            }
            if (diffAmount > 1) {
                return false
            }
        }
        return diffAmount == 1
    }
}

private class Task161Test {
    private val task = Task161()

    @Test
    fun isOneEditDistance() {
        isOneEditDistance("ab", "acb", true)
        isOneEditDistance("", "", false)
        isOneEditDistance("ab", "bcb", false)
        isOneEditDistance("a", "A", true)
        isOneEditDistance("abc", "abc", false)
        isOneEditDistance("abc", "", false)
        isOneEditDistance("abc", "abcd", true)
        isOneEditDistance("teacher", "detacher", false)
        isOneEditDistance("cb", "ab", true)
    }

    private fun isOneEditDistance(actualInp1: String, actualInp2: String, expected: Boolean) {
        val actual = task.isOneEditDistance(actualInp1, actualInp2)
        println(actual)
        Assertions.assertEquals(expected, actual)
    }
}