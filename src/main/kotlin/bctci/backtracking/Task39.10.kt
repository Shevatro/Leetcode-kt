package bctci.backtracking

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.math.max

class Task39_10 {
    fun calc4DirectionalMaxSumPath(grid: Array<IntArray>): Int {
        return Solution(grid).calc4DirectionalMaxSumPath()
    }

    private class Solution(
        private val grid: Array<IntArray>
    ) {
        private var maxSum = Int.MIN_VALUE
        private val visited = Array(grid.size) { BooleanArray(grid[0].size) }
        private var directions = listOf(
            Pair(1, 0), //down
            Pair(-1, 0), //up
            Pair(0, 1), //right
            Pair(0, -1), //left
        )

        fun calc4DirectionalMaxSumPath(): Int {
            backtrack(0, 0, 0)
            return maxSum
        }

        private fun backtrack(r: Int, c: Int, sum: Int) {
            if (r == grid.lastIndex && c == grid[0].lastIndex) {
                maxSum = max(maxSum, sum + grid[r][c])
                return
            }
            repeat(4) { i ->
                val newR = r + directions[i].first
                val newC = c + directions[i].second

                if (isInRange(newR, newC) && !visited[newR][newC]) {
                    visited[r][c] = true
                    backtrack(newR, newC, sum + grid[r][c])
                    visited[r][c] = false
                }
            }
        }

        private fun isInRange(r: Int, c: Int): Boolean {
            return r in 0..grid.lastIndex && c in 0..grid[0].lastIndex
        }
    }
}


private class Task39_10Test {
    private val task = Task39_10()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input: Array<IntArray>, expected: Int) {
        Assertions.assertEquals(expected, task.calc4DirectionalMaxSumPath(input))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(arrayOf(intArrayOf(1, -4, 3), intArrayOf(-2, 7, -6), intArrayOf(5, -4, 9)), 12),
                Arguments.of(arrayOf(intArrayOf(5)), 5),
                Arguments.of(arrayOf(intArrayOf(1, 2, 3)), 6),
                Arguments.of(arrayOf(intArrayOf(1), intArrayOf(2), intArrayOf(3)), 6),
                Arguments.of(arrayOf(intArrayOf(1, 2), intArrayOf(3, 4)), 8)
            )
        }
    }
}