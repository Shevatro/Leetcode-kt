package lc1000_1999.lc1200_1299

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.math.max

//Similar to Beyond Cracking The Coding Interview, Solved
//https://leetcode.com/problems/path-with-maximum-gold
class Task1219 {
    fun getMaximumGold(grid: Array<IntArray>): Int {
        return Solution(grid).getMaximumGold()
    }

    private class Solution(private val grid: Array<IntArray>) {
        private var maxGold = 0
        private val directions = arrayOf(0 to -1, 0 to 1, -1 to 0, 1 to 0)
        fun getMaximumGold(): Int {
            for (r in grid.indices) {
                for (c in grid[0].indices) {
                    if (grid[r][c] != 0) {
                        val backup = grid[r][c]
                        grid[r][c] = 0
                        backtrack(r, c, backup)
                        grid[r][c] = backup
                    }
                }
            }
            return maxGold
        }

        private fun backtrack(r: Int, c: Int, gold: Int) {
            maxGold = max(maxGold, gold)
            repeat(4) { i ->
                val newR = r + directions[i].first
                val newC = c + directions[i].second
                if (newR in 0..grid.lastIndex && newC in 0..grid[0].lastIndex
                    && grid[newR][newC] != 0
                ) {
                    val backup = grid[newR][newC]
                    grid[newR][newC] = 0
                    backtrack(newR, newC, gold + backup)
                    grid[newR][newC] = backup
                }
            }
        }
    }
}

private class Task1219Test {
    private val task = Task1219()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input: Array<IntArray>, expected: Int) {
        Assertions.assertEquals(expected, task.getMaximumGold(input))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(arrayOf(intArrayOf(0, 6, 0), intArrayOf(5, 8, 7), intArrayOf(0, 9, 0)), 24),
                Arguments.of(
                    arrayOf(intArrayOf(1, 0, 7), intArrayOf(2, 0, 6), intArrayOf(3, 4, 5), intArrayOf(0, 3, 0), intArrayOf(9, 0, 20)),
                    28
                ),
                Arguments.of(arrayOf(intArrayOf(0, 0, 0), intArrayOf(0, 0, 0), intArrayOf(0, 0, 0)), 0)
            )
        }
    }
}