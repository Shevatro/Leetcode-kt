package lc1_999.lc100_199

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.math.abs

//From Cracking The Coding Interview, Solved (but Repeat an optimal solution)
//https://leetcode.com/problems/one-edit-distance/

class Task161 {
    fun isOneEditDistance(s: String, t: String): Boolean {
        if (abs(s.length - t.length) > 1) {
            return false
        }
        return if (s.length == t.length) {
            hasOneEditReplace(s, t)
        } else if (s.length > t.length) {
            hasOneEditInsert(s, t)
        } else {
            hasOneEditInsert(t, s)
        }
    }

    private fun hasOneEditReplace(str1: String, str2: String): Boolean {
        var foundDiff = false
        for (i in str1.indices) {
            if (str1[i] != str2[i]) {
                if (!foundDiff) {
                    foundDiff = true
                } else {
                    return false
                }
            }
        }
        return foundDiff
    }

    private fun hasOneEditInsert(longStr: String, shortStr: String): Boolean {
        var longStrInd = 0
        var shortStrInd = 0
        while (longStrInd < longStr.length && shortStrInd < shortStr.length) {
            if (longStr[longStrInd] == shortStr[shortStrInd]) {
                shortStrInd++
            } else if (longStrInd != shortStrInd) {
                return false
            }
            longStrInd++
        }
        return true
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