package lc1_999.lc800_899

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.math.max
import kotlin.math.min

// Solved with Hint
//https://leetcode.com/problems/max-increase-to-keep-city-skyline/
class Task807 {
    fun maxIncreaseKeepingSkyline(grid: Array<IntArray>): Int {
        return Solution(grid).maxIncreaseKeepingSkyline()
    }

    private class Solution(private val grid: Array<IntArray>) {
        private val n = grid.size
        private val maxR = IntArray(n) { -1 }
        private val maxC = IntArray(n) { -1 }
        fun maxIncreaseKeepingSkyline(): Int {
            fillOutMax()
            return calculateTheDifference()
        }

        private fun fillOutMax() {
            for (r in grid.indices) {
                for (c in grid[0].indices) {
                    maxC[c] = max(maxC[c], grid[c][r])
                    maxR[r] = max(maxR[r], grid[c][r])
                }
            }
        }

        private fun calculateTheDifference(): Int {
            var sum = 0
            for (r in grid.indices) {
                for (c in grid[0].indices) {
                    sum += min(maxR[r], maxC[c]) - grid[c][r]
                }
            }
            return sum
        }
    }
}

private class Task807Test {
    private val task = Task807()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input: Array<IntArray>, expected: Int) {
        Assertions.assertEquals(expected, task.maxIncreaseKeepingSkyline(input))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    arrayOf(
                        intArrayOf(3, 0, 8, 4),
                        intArrayOf(2, 4, 5, 7),
                        intArrayOf(9, 2, 6, 3),
                        intArrayOf(0, 3, 1, 0),
                    ), 35
                ),
                Arguments.of(
                    arrayOf(
                        intArrayOf(0, 0, 0),
                        intArrayOf(0, 0, 0),
                        intArrayOf(0, 0, 0)
                    ), 0
                )
            )
        }
    }
}