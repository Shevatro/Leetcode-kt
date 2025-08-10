package lc1_999.lc1_99

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

//Solved but it has another solution related to Math
//https://leetcode.com/problems/palindrome-number/
class Task9 {
    fun isPalindrome(x: Int): Boolean {
        val digits = x.toString()
        val size = digits.length
        for (i in 0 until size / 2) {
            val j = size - 1 - i
            if (digits[i] != digits[j]) return false
        }
        return true
    }
}

private class Task9Test {
    private val task = Task9()

    @Test
    fun test1() {
        val result = task.isPalindrome(121)
        Assertions.assertEquals(true, result)
    }

    @Test
    fun test2() {
        val result = task.isPalindrome(-121)
        Assertions.assertEquals(false, result)
    }

    @Test
    fun test3() {
        val result = task.isPalindrome(10)
        Assertions.assertEquals(false, result)
    }
}