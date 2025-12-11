package bctci.backtracking

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.math.max

class Task39_1 {
    fun maxPathSum(grid: Array<IntArray>): Int {
        return Solution2(grid).maxPathSum()
    }

    private class Solution2(private val grid: Array<IntArray>) {
        private val paths = Array(grid.size) { IntArray(grid[0].size) }
        fun maxPathSum() = dfs(0, 0)

        private fun dfs(c: Int, r: Int): Int {
            if (c !in 0..<paths.size || r !in 0..<paths[0].size) return 0
            if (paths[c][r] != 0) return paths[c][r]
            val min = max(dfs(c, r + 1), dfs(c + 1, r))
            paths[c][r] = grid[c][r]
            if (min != 0) paths[c][r] += min
            return paths[c][r]
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