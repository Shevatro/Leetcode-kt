package lc1000_1999.lc1700_1799

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.math.max

//Solved
//https://leetcode.com/problems/merge-strings-alternately/description/
class Task1768 {
    fun mergeAlternately(word1: String, word2: String): String {
        val sb = StringBuilder()
        for (i in 0 until max(word1.length, word2.length)) {
            val l1 = word1.getOrNull(i)
            val l2 = word2.getOrNull(i)
            l1?.let { sb.append(it) }
            l2?.let { sb.append(it) }
        }
        return sb.toString()
    }
}

private class Task1768Test {
    private val task = Task1768()

    @Test
    fun test1() {
        val result = task.mergeAlternately("abc", "pqr")
        Assertions.assertEquals("apbqcr", result)
    }

    @Test
    fun test2() {
        val result = task.mergeAlternately("ab", "pqrs")
        Assertions.assertEquals("apbqrs", result)
    }

    @Test
    fun test3() {
        val result = task.mergeAlternately("abcd", "pq")
        Assertions.assertEquals("apbqcd", result)
    }
}