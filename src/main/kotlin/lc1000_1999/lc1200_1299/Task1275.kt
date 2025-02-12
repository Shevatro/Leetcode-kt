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
        private val grid = Array(3) { IntArray(3) }
        private var mark: Int = 0
        fun tictactoe(): String {
            applyMoves()
            mark = 1
            if (checkIfWins()) return "A"
            mark = 2
            if (checkIfWins()) return "B"
            return if (moves.size == 9) {
                "Draw"
            } else {
                "Pending"
            }
        }

        private fun checkIfWins(): Boolean {
            return isEq(0, 0) && isEq(0, 1) && isEq(0, 2)
                    || isEq(0, 0) && isEq(1, 1) && isEq(2, 2)
                    || isEq(0, 0) && isEq(1, 0) && isEq(2, 0)
                    || isEq(1, 0) && isEq(1, 1) && isEq(1, 2)
                    || isEq(0, 2) && isEq(1, 1) && isEq(2, 0)
                    || isEq(0, 1) && isEq(1, 1) && isEq(2, 1)
                    || isEq(2, 0) && isEq(2, 1) && isEq(2, 2)
                    || isEq(0, 2) && isEq(1, 2) && isEq(2, 2)
        }

        private fun isEq(i: Int, j: Int): Boolean {
            return grid[i][j] == mark
        }

        private fun applyMoves() {
            for (k in moves.indices) {
                val move = moves[k]
                val i = move[0]
                val j = move[1]
                grid[i][j] = if (k % 2 == 0) 1 else 2
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
            intArrayOf(0,2),intArrayOf(1,2),intArrayOf(0,1),intArrayOf(1,0)
        )
        Assertions.assertEquals("Pending", task.tictactoe(input))
    }
}