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
                // //isDuplicate - add previous one
                if (findInCache(position) != null) {
                    result.add(cache.size)
                    continue
                }
                val r = position[0]
                val c = position[1]
                val strPosition = positionToStr(position)
                val sets = mutableSetOf<MutableSet<String>>()
                for (direction in directions) {
                    val newR = r + direction.first
                    val newC = c + direction.second
                    if (isInRange(newR, newC)) {
                        findInCache(intArrayOf(newR, newC))?.let { sets.add(it) }
                    }
                }
                //new island
                if (sets.isEmpty()) {
                    cache.add(mutableSetOf(strPosition))
                    // only one island just connect a new land to it
                } else if (sets.size == 1) {
                    sets.first().add(strPosition)
                    //we need to merge islands
                } else {
                    val first = sets.first()
                    //copy them over
                    for (set in sets) {
                        if (set == first) continue
                        first.addAll(set)
                    }
                    first.add(strPosition)
                    //clean up
                    for (set in sets) {
                        if (set == first) continue
                        cache.remove(set)
                    }
                }
                result.add(cache.size)
            }
            return result
        }

        private fun positionToStr(pos: IntArray): String {
            return "${pos[0]},${pos[1]}"
        }

        private fun findInCache(pos: IntArray): MutableSet<String>? {
            val posStr = positionToStr(pos)
            for (set in cache) {
                if (set.contains(posStr)) return set
            }
            return null
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