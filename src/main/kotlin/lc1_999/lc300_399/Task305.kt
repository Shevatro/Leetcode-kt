package lc1_999.lc300_399

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

//Solved but with a hint
//Note: This is a suboptimal solution, the most optimal is to use UnionFind
//https://leetcode.com/problems/number-of-islands-ii/
class Task305 {
    fun numIslands2(m: Int, n: Int, positions: Array<IntArray>): List<Int> {
        return Solution2(m, n, positions).numIslands2()
    }

    private class Solution2(
        private val m: Int,
        private val n: Int,
        private val positions: Array<IntArray>
    ) {
        private val cache = mutableListOf<MutableSet<String>>()
        private val directions = arrayOf(1 to 0, -1 to 0, 0 to 1, 0 to -1)
        fun numIslands2(): List<Int> {
            val result = mutableListOf<Int>()
            for (position in positions) {
                val r = position[0]
                val c = position[1]
                // isDuplicate - add previous one
                if (findInCache(r, c) != null) {
                    result.add(cache.size)
                    continue
                }
                analyzeNeighbors(r, c)
                result.add(cache.size)
            }
            return result
        }

        private fun positionToStr(r: Int, c: Int) = "$r,$c"

        private fun findInCache(r: Int, c: Int): MutableSet<String>? {
            val posStr = positionToStr(r, c)
            for (set in cache) {
                if (set.contains(posStr)) return set
            }
            return null
        }

        private fun findAdjacentIslands(r: Int, c: Int): Set<MutableSet<String>> {
            //we use set to prevent duplicated, we should ignore the same neighbors
            val adjacentIslands = mutableSetOf<MutableSet<String>>()
            for (direction in directions) {
                val newR = r + direction.first
                val newC = c + direction.second
                if (isInRange(newR, newC)) {
                    findInCache(newR, newC)?.let { adjacentIslands.add(it) }
                }
            }
            return adjacentIslands
        }

        private fun analyzeNeighbors(r: Int, c: Int) {
            val strPosition = positionToStr(r, c)
            val adjacentIslands = findAdjacentIslands(r, c)

            when {
                //no neighbors, so it's a new island
                adjacentIslands.isEmpty() -> cache.add(mutableSetOf(strPosition))
                // only one island, so just connect a new land to it
                adjacentIslands.size == 1 -> adjacentIslands.first().add(strPosition)
                else -> {
                    unionIslands(adjacentIslands)
                    //add a new land
                    adjacentIslands.first().add(strPosition)
                }
            }
        }

        private fun unionIslands(islands: Set<MutableSet<String>>) {
            val first = islands.first()
            //copy them over to the first one
            for (set in islands) {
                if (set == first) continue
                first.addAll(set)
            }
            //clean up
            for (set in islands) {
                if (set == first) continue
                cache.remove(set)
            }
        }

        private fun isInRange(r: Int, c: Int): Boolean {
            return r in 0..<m && c in 0..<n
        }
    }
}

private class Task305Test {
    private val task = Task305()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input1: Int, input2: Int, input3: Array<IntArray>, expected: List<Int>) {
        Assertions.assertEquals(expected, task.numIslands2(input1, input2, input3))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    3, 3,
                    arrayOf(intArrayOf(0, 0), intArrayOf(0, 1), intArrayOf(1, 2), intArrayOf(2, 1)),
                    listOf(1, 1, 2, 3)
                ),
                Arguments.of(1, 1, arrayOf(intArrayOf(0, 0)), listOf(1)),
                Arguments.of(
                    3, 3,
                    arrayOf(intArrayOf(0, 0), intArrayOf(0, 1), intArrayOf(1, 2), intArrayOf(2, 1), intArrayOf(0, 0)),
                    listOf(1, 1, 2, 3, 3)
                ),
                Arguments.of(
                    3, 3,
                    arrayOf(intArrayOf(0, 0), intArrayOf(0, 1), intArrayOf(1, 2), intArrayOf(1, 2)),
                    listOf(1, 1, 2, 2)
                )
            )
        }
    }
}