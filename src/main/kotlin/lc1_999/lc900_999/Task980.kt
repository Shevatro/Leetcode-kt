package lc1_999.lc900_999

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

//Similar to Beyond Cracking The Coding Interview, Solved
//https://leetcode.com/problems/unique-paths-iii/
class Task980 {
    fun uniquePathsIII(grid: Array<IntArray>): Int {
        return Solution(grid).uniquePathsIII()
    }

    private class Solution(private val grid: Array<IntArray>) {
        private var pathCount = 0
        private var emptyCellsAmount = 0
        private val directions = arrayOf(1 to 0, -1 to 0, 0 to 1, 0 to -1)
        private var startingPoint = -1 to -1
        fun uniquePathsIII(): Int {
            calcPrerequisites()
            val (startingPointR, startingPointC) = startingPoint
            grid[startingPointR][startingPointC] = -1
            backtrack(startingPointR, startingPointC, false, 0)
            return pathCount
        }

        private fun backtrack(r: Int, c: Int, isLast: Boolean, curAmount: Int) {
            if (curAmount > emptyCellsAmount) return
            if (isLast && curAmount == emptyCellsAmount) {
                pathCount++
                return
            }
            repeat(4) { i ->
                val newR = r + directions[i].first
                val newC = c + directions[i].second
                if (isInRange(newR, newC) && grid[newR][newC] != -1) {
                    val backupValue = grid[newR][newC]
                    grid[newR][newC] = -1
                    val isLast = backupValue == 2
                    val emptyIndicator = if (backupValue == 0) 1 else 0
                    backtrack(newR, newC, isLast, curAmount + emptyIndicator)
                    grid[newR][newC] = backupValue
                }
            }
        }

        private fun calcPrerequisites() {
            for (r in grid.indices) {
                for (c in grid[0].indices) {
                    when (grid[r][c]) {
                        0 -> emptyCellsAmount++
                        1 -> startingPoint = r to c
                    }
                }
            }
        }

        private fun isInRange(r: Int, c: Int): Boolean {
            return r in 0..grid.lastIndex && c in 0..grid[0].lastIndex
        }
    }
}

private class Task980Test {
    private val task = Task980()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input: Array<IntArray>, expected: Int) {
        Assertions.assertEquals(expected, task.uniquePathsIII(input))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(arrayOf(intArrayOf(1, 0, 0, 0), intArrayOf(0, 0, 0, 0), intArrayOf(0, 0, 2, -1)), 2),
                Arguments.of(arrayOf(intArrayOf(1, 0, 0, 0), intArrayOf(0, 0, 0, 0), intArrayOf(0, 0, 0, 2)), 4),
                Arguments.of(arrayOf(intArrayOf(0, 1), intArrayOf(2, 0)), 0)
            )
        }
    }
}