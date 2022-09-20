package lc1_999.lc700_799

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

//From Cracking The Coding Interview, Not Solved, Repeat
//https://leetcode.com/problems/rotate-string/
class Task796 {
    fun rotateString(s: String, goal: String): Boolean {
        if (s.length != goal.length) return false
        return isCyclicEquals(s, goal)
    }
    private fun isCyclicEquals(xy: String, yx: String): Boolean{
        val xyxy = xy + xy
        return xyxy.contains(yx)
    }
}

private class Task796Test {
    private val task = Task796()

    @Test
    fun rotateString() {
        rotateString("abcde", "cdeab", true)
        rotateString("abcde", "abced", false)
        rotateString("abcde", "abcde", true)
        rotateString("hgfld", "abcde", false)
        rotateString("bbbacddceeb", "ceebbbbacdd", true)
    }

    private fun rotateString(actualInp1: String, actualInp2: String, expected: Boolean) {
        val actual = task.rotateString(actualInp1, actualInp2)
        println(actual.toString())
        Assertions.assertEquals(expected, actual)
    }
}