package lc3000_3999.lc3600_3699

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

// Solved
//https://leetcode.com/problems/count-islands-with-total-value-divisible-by-k/
class Task3619 {
    fun countIslands(grid: Array<IntArray>, k: Int): Int {
        return Solution(grid, k).countIslands()
    }

    private class Solution(
        private val grid: Array<IntArray>,
        private val k: Int
    ) {
        private val directions = arrayOf(
            1 to 0, -1 to 0, 0 to 1, 0 to -1
        )
        private var sum = 0
        fun countIslands(): Int {
            var amount = 0
            for (r in grid.indices) {
                for (c in grid[0].indices) {
                    if (grid[r][c] > 0) {
                        dfs(r, c)
                        if (sum == 0) amount++
                        sum = 0
                    }
                }
            }
            return amount
        }

        private fun dfs(r: Int, c: Int) {
            if (grid[r][c] <= 0) return
            sum = (sum + grid[r][c]) % k
            grid[r][c] = -1
            for (direction in directions) {
                val newR = r + direction.first
                val newC = c + direction.second
                if (isInRange(newR, newC)) {
                    dfs(newR, newC)
                }
            }
        }

        private fun isInRange(r: Int, c: Int): Boolean {
            return r in 0..grid.lastIndex && c in 0..grid[0].lastIndex
        }
    }
}

private class Task3619Test {
    private val task = Task3619()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input1: Array<IntArray>, input2: Int, expected: Int) {
        Assertions.assertEquals(expected, task.countIslands(input1, input2))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    arrayOf(
                        intArrayOf(0, 2, 1, 0, 0),
                        intArrayOf(0, 5, 0, 0, 5),
                        intArrayOf(0, 0, 1, 0, 0),
                        intArrayOf(0, 1, 4, 7, 0),
                        intArrayOf(0, 2, 0, 0, 8)
                    ), 5, 2
                ),
                Arguments.of(
                    arrayOf(
                        intArrayOf(3, 0, 3, 0),
                        intArrayOf(0, 3, 0, 3),
                        intArrayOf(3, 0, 3, 0)
                    ), 3, 6
                ),
            )
        }
    }
}