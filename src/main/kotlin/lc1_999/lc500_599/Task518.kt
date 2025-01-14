package lc1_999.lc500_599

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

//From Cracking The Coding Interview, Not Solved
//https://leetcode.com/problems/coin-change-ii/description/
class Task518 {
    fun change(amount: Int, coins: IntArray): Int {
        return BottomUpSolution(amount, coins).change()
    }

    private class BottomUpSolution(
        private val totalAmount: Int,
        private val coins: IntArray
    ) {
        private val dp = IntArray(totalAmount + 1)
        fun change(): Int {
            dp[0] = 1
            for (coin in coins) {
                for (amount in coin..totalAmount) {
                    dp[amount] += dp[amount - coin]
                }
            }
            return dp[totalAmount]
        }
    }
}

private class Task518Test {
    private val task = Task518()

    @Test
    fun test1() {
        val actualResult = task.change(5, intArrayOf(1, 2, 5))
        Assertions.assertEquals(4, actualResult)
    }

    @Test
    fun test2() {
        val actualResult = task.change(3, intArrayOf(2))
        Assertions.assertEquals(0, actualResult)
    }


    @Test
    fun test3() {
        val actualResult = task.change(10, intArrayOf(10))
        Assertions.assertEquals(1, actualResult)
    }

    @Test
    fun test4() {
        val actualResult = task.change(500, intArrayOf(1, 2, 5))
        Assertions.assertEquals(12701, actualResult)
    }
}