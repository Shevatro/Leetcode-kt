package lc1000_1999.lc1200_1299

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

//From Cracking The Coding Interview, Solved
//https://leetcode.com/problems/find-winner-on-a-tic-tac-toe-game/description/
class Task1275 {
    fun tictactoe(moves: Array<IntArray>): String {
        return Solution2(moves).tictactoe()
    }

    private class Solution2(private val moves: Array<IntArray>) {
        private val row = IntArray(3)
        private val column = IntArray(3)
        private var mainDiagonal = 0
        private var secondDiagonal = 0
        fun tictactoe(): String {
            if (moves.size < 5) return "Pending"
            for (k in moves.size - 1 downTo 0 step 2) {
                val i = moves[k][0]
                val j = moves[k][1]
                if (i == j) mainDiagonal++
                if (i + j == 2) secondDiagonal++
                row[i]++
                column[j]++
                if (isWin(i, j)) return if (k % 2 == 0) "A" else "B"
            }
            return if (moves.size == 9) "Draw" else "Pending"
        }

        private fun isWin(i: Int, j: Int): Boolean {
            return row[i] == 3 || column[j] == 3 || mainDiagonal == 3 || secondDiagonal == 3
        }
    }
}


private class Task1275Test {
    private val task = Task1275()

    @Test
    fun test1() {
        val input = arrayOf(
            intArrayOf(0, 0), intArrayOf(2, 0), intArrayOf(1, 1), intArrayOf(2, 1), intArrayOf(2, 2)
        )
        Assertions.assertEquals("A", task.tictactoe(input))
    }

    @Test
    fun test2() {
        val input = arrayOf(
            intArrayOf(0, 0), intArrayOf(1, 1), intArrayOf(0, 1), intArrayOf(0, 2), intArrayOf(1, 0), intArrayOf(2, 0)
        )
        Assertions.assertEquals("B", task.tictactoe(input))
    }

    @Test
    fun test3() {
        val input = arrayOf(
            intArrayOf(0, 0), intArrayOf(1, 1), intArrayOf(2, 0), intArrayOf(1, 0), intArrayOf(1, 2), intArrayOf(2, 1),
            intArrayOf(0, 1), intArrayOf(0, 2), intArrayOf(2, 2)
        )
        Assertions.assertEquals("Draw", task.tictactoe(input))
    }

    @Test
    fun test4() {
        val input = arrayOf(
            intArrayOf(0, 2), intArrayOf(1, 2), intArrayOf(0, 1), intArrayOf(1, 0)
        )
        Assertions.assertEquals("Pending", task.tictactoe(input))
    }

    @Test
    fun test5() {
        val input = arrayOf(
            intArrayOf(1, 2), intArrayOf(1, 0), intArrayOf(0, 0), intArrayOf(0, 1), intArrayOf(2, 1)
        )
        Assertions.assertEquals("Pending", task.tictactoe(input))
    }
}