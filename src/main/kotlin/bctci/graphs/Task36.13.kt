package bctci.graphs

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Task36_13 {
    fun countGridIslands(grid: Array<IntArray>): Int {
        return Solution(grid).countGridIslands()
    }

    private class Solution(
        private val grid: Array<IntArray>
    ) {
        private var directions = listOf(
            1 to 0, //down
            -1 to 0, //up
            0 to 1, //right
            0 to -1, //left
        )
        private var amount = 0

        fun countGridIslands(): Int {
            for (r in 0 until grid.size) {
                for (c in 0 until grid[0].size) {
                    if (grid[r][c] == 1) {
                        dfs(r, c)
                        amount++
                    }
                }
            }
            return amount
        }

        private fun dfs(r: Int, c: Int) {
            if (grid[r][c] == 0) return
            grid[r][c] = 0
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


private class Task36_13Test {
    private val task = Task36_13()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input: Array<IntArray>, expected: Int) {
        Assertions.assertEquals(expected, task.countGridIslands(input))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    arrayOf(
                        intArrayOf(0, 0, 1, 0),
                        intArrayOf(1, 1, 0, 1),
                        intArrayOf(0, 0, 1, 1),
                    ), 3
                ),
                Arguments.of(emptyArray<IntArray>(), 0),
                Arguments.of(arrayOf(intArrayOf(1)), 1),
                Arguments.of(arrayOf(intArrayOf(0, 0), intArrayOf(0, 0)), 0),
                Arguments.of(arrayOf(intArrayOf(1, 1), intArrayOf(1, 1)), 1),
                Arguments.of(
                    arrayOf(
                        intArrayOf(1, 0, 1),
                        intArrayOf(0, 0, 0),
                        intArrayOf(1, 0, 1),
                    ), 4
                )
            )
        }
    }
}