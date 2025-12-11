package bctci.backtracking

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.math.max

class Task39_1 {
    fun maxPathSum(grid: Array<IntArray>): Int {
        return Solution(grid).maxPathSum()
    }

    private class Solution(private val grid: Array<IntArray>) {
        private val paths = Array(grid.size) { IntArray(grid[0].size) }
        fun maxPathSum() = dfs(0, 0)

        private fun dfs(r: Int, c: Int): Int {
            if (r !in 0..<paths.size || c !in 0..<paths[0].size) return 0
            if (paths[r][c] != 0) return paths[r][c]
            paths[r][c] = max(dfs(r, c + 1), dfs(r + 1, c)) + grid[r][c]
            return paths[r][c]
        }
    }
}


private class Task39_1Test {
    private val task = Task39_1()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input: Array<IntArray>, expected: Int) {
        Assertions.assertEquals(expected, task.maxPathSum(input))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(arrayOf(intArrayOf(1, 4, 3), intArrayOf(2, 7, 6), intArrayOf(5, 8, 9)), 29),
                Arguments.of(arrayOf(intArrayOf(5)), 5),
                Arguments.of(arrayOf(intArrayOf(1, 2, 3)), 6),
                Arguments.of(arrayOf(intArrayOf(1, 2, 3, 4)), 10),
                Arguments.of(arrayOf(intArrayOf(1), intArrayOf(2), intArrayOf(3), intArrayOf(4)), 10),
                Arguments.of(arrayOf(intArrayOf(1, 2, 3), intArrayOf(4, 5, 6), intArrayOf(7, 8, 9)), 29),
                Arguments.of(arrayOf(intArrayOf(1, 1, 1), intArrayOf(1, 1, 1), intArrayOf(1, 1, 1)), 5)
            )
        }
    }
}