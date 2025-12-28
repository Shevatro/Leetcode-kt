package bctci.dp

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Task40_4 {
    fun count0SumPaths(grid: Array<IntArray>): Int {
        return Solution(grid).count0SumPaths()
    }

    private class Solution(
        private val grid: Array<IntArray>
    ) {
        private val memo = Array(grid.size) { IntArray(grid[0].size) { -1 } }
        private val directions = listOf(
            1 to 0, //down
            0 to 1, // right
            1 to 1 //diagonal down-right
        )

        fun count0SumPaths(): Int {
            return dp(0, 0, grid[0][0])
        }

        private fun dp(r: Int, c: Int, sum: Int): Int {
            if (sum > 0) return 0
            if (r == grid.lastIndex && c == grid[0].lastIndex) {
                return if (sum == 0) 1 else 0
            }
            if (memo[r][c] != -1) return memo[r][c]
            var amount0Paths = 0
            repeat(3) { i ->
                val newR = r + directions[i].first
                val newC = c + directions[i].second
                if (isInRange(newR, newC)) {
                    amount0Paths += dp(newR, newC, sum + grid[newR][newC])
                }
            }
            memo[r][c] = amount0Paths
            return amount0Paths
        }

        private fun isInRange(r: Int, c: Int): Boolean {
            return r in 0..grid.lastIndex && c in 0..grid[0].lastIndex
        }
    }


    private class NotOptimalSolution(
        private val grid: Array<IntArray>
    ) {
        private var count = 0
        private val directions = listOf(
            0 to 1, // down
            1 to 0, //right
            1 to 1 //diagonal down-right
        )

        fun count0SumPaths(): Int {
            backtrack(0, 0, grid[0][0])
            return count
        }

        private fun backtrack(r: Int, c: Int, sum: Int) {
            if (r == grid.lastIndex && c == grid[0].lastIndex) {
                if (sum == 0) count++
                return
            }
            repeat(3) { i ->
                val newR = r + directions[i].first
                val newC = c + directions[i].second
                if (isInRange(newR, newC)) {
                    backtrack(newR, newC, sum + grid[newR][newC])
                }
            }
        }

        private fun isInRange(r: Int, c: Int): Boolean {
            return r in 0..grid.lastIndex && c in 0..grid[0].lastIndex
        }
    }
}


private class Task40_4Test {
    private val task = Task40_4()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input: Array<IntArray>, expected: Int) {
        Assertions.assertEquals(expected, task.count0SumPaths(input))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(arrayOf(intArrayOf(0, 1, 1), intArrayOf(0, 0, 0), intArrayOf(1, 0, 0)), 7),
                Arguments.of(arrayOf(intArrayOf(1)), 0),
                Arguments.of(arrayOf(intArrayOf(0, 0), intArrayOf(0, 0)), 3),
                Arguments.of(arrayOf(intArrayOf(0)), 1),
                Arguments.of(arrayOf(intArrayOf(0, 0, 0), intArrayOf(0, 1, 0), intArrayOf(0, 0, 0)), 4)
            )
        }
    }
}