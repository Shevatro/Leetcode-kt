package lc1_999.lc1_99

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.math.min

//From Beyond Cracking The Coding Interview, Solved
//https://leetcode.com/problems/minimum-path-sum/description/
class Task64 {
    fun minPathSum(grid: Array<IntArray>): Int {
        return Solution2(grid).minPathSum()
    }

    private class Solution2(private val grid: Array<IntArray>) {
        private val paths = Array(grid.size) { IntArray(grid[0].size) { -1 } }
        fun minPathSum(): Int {
            return dfs(0, 0)
        }

        private fun dfs(r: Int, c: Int): Int {
            if (r !in 0..<paths.size || c !in 0..<paths[0].size) return Int.MAX_VALUE
            if (paths[r][c] != -1) return paths[r][c]
            val min = min(dfs(r, c + 1), dfs(r + 1, c))
            paths[r][c] = grid[r][c]
            if (min != Int.MAX_VALUE) paths[r][c] += min
            return paths[r][c]
        }
    }
}

private class Task64Test {
    private val task = Task64()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input: Array<IntArray>, expected: Int) {
        Assertions.assertEquals(expected, task.minPathSum(input))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(arrayOf(intArrayOf(1, 3, 1), intArrayOf(1, 5, 1), intArrayOf(4, 2, 1)), 7),
                Arguments.of(arrayOf(intArrayOf(1, 2, 3), intArrayOf(4, 5, 6)), 12),
                Arguments.of(
                    arrayOf(
                        intArrayOf(9, 9, 0, 8, 9, 0, 5, 7, 2, 2, 7, 0, 8, 0, 2, 4, 8),
                        intArrayOf(4, 4, 2, 7, 6, 0, 9, 7, 3, 2, 5, 4, 6, 5, 4, 8, 7),
                        intArrayOf(4, 9, 7, 0, 7, 9, 2, 4, 0, 2, 4, 4, 6, 2, 8, 0, 7),
                        intArrayOf(7, 7, 9, 6, 6, 4, 8, 4, 8, 7, 9, 4, 7, 6, 9, 6, 5),
                        intArrayOf(1, 3, 7, 5, 7, 9, 7, 3, 3, 3, 8, 3, 6, 5, 0, 3, 6),
                        intArrayOf(7, 1, 0, 7, 5, 0, 6, 6, 5, 3, 2, 6, 0, 0, 9, 5, 7),
                        intArrayOf(6, 5, 6, 3, 8, 1, 8, 6, 4, 4, 3, 4, 9, 9, 3, 3, 1),
                        intArrayOf(1, 0, 2, 9, 7, 9, 3, 1, 7, 5, 1, 8, 2, 8, 4, 7, 6),
                        intArrayOf(9, 6, 7, 7, 4, 1, 4, 0, 6, 5, 1, 9, 0, 3, 2, 1, 7),
                        intArrayOf(2, 0, 8, 7, 1, 7, 4, 3, 5, 6, 1, 9, 4, 0, 0, 2, 7),
                        intArrayOf(9, 8, 1, 3, 8, 7, 1, 2, 8, 3, 7, 3, 4, 6, 7, 6, 6),
                        intArrayOf(4, 8, 3, 8, 1, 0, 4, 4, 1, 0, 4, 1, 4, 4, 0, 3, 5),
                        intArrayOf(6, 3, 4, 7, 5, 4, 2, 2, 7, 9, 8, 4, 5, 6, 0, 3, 9),
                        intArrayOf(0, 4, 9, 7, 1, 0, 7, 7, 3, 2, 1, 4, 7, 6, 0, 0, 0)
                    ), 77
                )
            )
        }
    }
}