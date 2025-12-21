package bctci.backtracking

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Task39_11 {
    fun getShortestEscapePathWithAllClues(grid: Array<IntArray>): List<Pair<Int, Int>> {
        return Solution(grid).getShortestEscapePathWithAllClues()
    }

    private class Solution(
        private val grid: Array<IntArray>
    ) {
        private val cluesAmount = findCluesAmount()
        private var curPath = ArrayDeque<Pair<Int, Int>>()
        private var shortestPath = mutableListOf<Pair<Int, Int>>()
        private val visited = Array(grid.size) { BooleanArray(grid[0].size) }
        private var directions = listOf(
            Pair(1, 0), //down
            Pair(-1, 0), //up
            Pair(0, 1), //right
            Pair(0, -1), //left
        )

        fun getShortestEscapePathWithAllClues(): List<Pair<Int, Int>> {
            curPath.addLast(0 to 0)
            visited[0][0] = true
            backtrack(0, 0, 0)
            return shortestPath
        }

        private fun findCluesAmount(): Int {
            var count = 0
            for (r in grid.indices) {
                for (c in grid[0].indices) {
                    if (grid[r][c] == 2) count++
                }
            }
            return count
        }

        private fun backtrack(r: Int, c: Int, curCluesAmount: Int) {
            if (curCluesAmount == cluesAmount) {
                if (shortestPath.isEmpty() || curPath.size < shortestPath.size) {
                    shortestPath = curPath.toMutableList()
                }
                return
            }
            repeat(4) { i ->
                val newR = r + directions[i].first
                val newC = c + directions[i].second

                if (isInRange(newR, newC) && !visited[newR][newC] && grid[newR][newC] != 1) {
                    visited[newR][newC] = true
                    curPath.addLast(newR to newC)

                    val clueIndicator = if (grid[newR][newC] == 2) 1 else 0
                    backtrack(newR, newC, curCluesAmount + clueIndicator)

                    visited[newR][newC] = false
                    curPath.removeLast()
                }
            }
        }

        private fun isInRange(r: Int, c: Int): Boolean {
            return r in 0..grid.lastIndex && c in 0..grid[0].lastIndex
        }
    }
}


private class Task39_11Test {
    private val task = Task39_11()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input: Array<IntArray>, expected: List<List<Pair<Int, Int>>>) {
        val result = task.getShortestEscapePathWithAllClues(input)
        if (result.isEmpty() && expected.isEmpty()) {
            Assertions.assertEquals(true, true)
        } else {
            Assertions.assertEquals(true, result in expected)
        }
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    arrayOf(intArrayOf(0, 1, 0), intArrayOf(0, 2, 0), intArrayOf(0, 0, 2)),
                    listOf(
                        listOf(0 to 0, 1 to 0, 1 to 0, 1 to 2, 2 to 2),
                        listOf(0 to 0, 1 to 0, 1 to 1, 2 to 1, 2 to 2),
                    )
                ),
                Arguments.of(arrayOf(intArrayOf(0, 0, 0), intArrayOf(2, 1, 2)), emptyList<List<Pair<Int, Int>>>()),
                Arguments.of(arrayOf(intArrayOf(0, 0, 1, 2), intArrayOf(0, 1, 0, 0)), emptyList<List<Pair<Int, Int>>>()),
                Arguments.of(arrayOf(intArrayOf(0, 2)), listOf(listOf(0 to 0, 0 to 1))),
                Arguments.of(arrayOf(intArrayOf(0, 1), intArrayOf(1, 2)), emptyList<List<Pair<Int, Int>>>()),
                Arguments.of(arrayOf(intArrayOf(0, 2, 2)), listOf(listOf(0 to 0, 0 to 1, 0 to 2))),
                Arguments.of(arrayOf(intArrayOf(0, 2), intArrayOf(2, 1)), emptyList<List<Pair<Int, Int>>>()),
                Arguments.of(
                    arrayOf(
                        intArrayOf(0, 0, 0, 0, 0), intArrayOf(1, 1, 0, 1, 1), intArrayOf(2, 0, 0, 0, 0),
                        intArrayOf(0, 0, 2, 0, 2), intArrayOf(0, 0, 0, 0, 0)
                    ),
                    listOf(
                        listOf(0 to 0, 0 to 1, 0 to 2, 1 to 2, 2 to 2, 2 to 1, 2 to 0, 3 to 0, 3 to 1, 3 to 2, 3 to 3, 3 to 4)
                    )
                )
            )
        }
    }
}