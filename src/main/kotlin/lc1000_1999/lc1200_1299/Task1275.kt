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
        //row for cross and zero
        private val row = Array(2) { IntArray(3) }
        //column for cross and zero
        private val column = Array(2) { IntArray(3) }
        //main and second diagonals for cross and zero
        private val diagonals = Array(2) { IntArray(2) }
        fun tictactoe(): String {
            for (k in moves.indices) {
                val move = moves[k]
                val i = move[0]
                val j = move[1]
                val isEven = k % 2 == 0
                val pos = if (isEven) 0 else 1
                val returnVal = if (isEven) "A" else "B"
                if (row[pos][i] == 2 || column[pos][j] == 2) return returnVal
                row[pos][i]++
                column[pos][j]++
                //main diagonal
                if (i == j) {
                    if (diagonals[pos][0] == 2) return returnVal
                    diagonals[pos][0]++
                }
                //second diagonal
                if (i + j == 2) {
                    if (diagonals[pos][1] == 2) return returnVal
                    diagonals[pos][1]++
                }
            }
            return if (moves.size == 9) {
                "Draw"
            } else {
                "Pending"
            }
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