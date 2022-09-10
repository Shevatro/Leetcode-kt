package lc1_999.lc300_399

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

//From Cracking The Coding Interview, Solved
//https://leetcode.com/problems/contains-duplicate/

class Task387 {
    fun firstUniqChar(s: String): Int {
        val lettersAmount = fillLettersAmount(s)
        return findFirstUniqCharPos(s, lettersAmount)
    }

    private fun fillLettersAmount(s: String): Map<Int, Int> {
        val lettersAmount = mutableMapOf<Int, Int>()
        for (i in s.indices) {
            val item = s[i].code
            lettersAmount[item] = lettersAmount.getOrDefault(item, 0) + 1
        }
        return lettersAmount
    }

    private fun findFirstUniqCharPos(s: String, lettersAmount: Map<Int, Int>): Int {
        for (i in s.indices) {
            if (lettersAmount[s[i].code] == 1) return i
        }
        return -1
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