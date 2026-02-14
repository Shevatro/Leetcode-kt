package lc1_999.lc300_399

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.PriorityQueue
import java.util.stream.Stream

//Similar to Beyond Cracking The Coding Interview, Solved
//https://leetcode.com/problems/top-k-frequent-elements/description/
class Task347 {
    fun topKFrequent(nums: IntArray, k: Int): IntArray {
        return Solution(nums, k).topKFrequent()
    }

    private class Solution(
        private val nums: IntArray,
        private val k: Int
    ) {
        private val freqMap = mutableMapOf<Int, Int>()
        private val heap = PriorityQueue<Pair<Int, Int>>(compareByDescending { it.second })
        fun topKFrequent(): IntArray {
            fillFreqMap()
            fillHeap()
            return generateResult()
        }

        private fun fillFreqMap() {
            for (num in nums) {
                freqMap.merge(num, 1, Int::plus)
            }
        }

        private fun fillHeap() {
            for ((key, value) in freqMap) {
                heap.add(key to value)
            }
        }

        private fun generateResult(): IntArray {
            val result = IntArray(k)
            var i = 0
            while (heap.isNotEmpty() && i < result.size) {
                result[i] = heap.poll().first
                i++
            }
            return result
        }
    }
}

private class Task347Test {
    private val task = Task347()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input1: IntArray, input2: Int, expected: IntArray) {
        Assertions.assertArrayEquals(expected, task.topKFrequent(input1, input2))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(intArrayOf(1, 1, 1, 2, 2, 3), 2, intArrayOf(1, 2)),
                Arguments.of(intArrayOf(1), 1, intArrayOf(1)),
                Arguments.of(intArrayOf(1, 2, 1, 2, 1, 2, 3, 1, 3, 2), 2, intArrayOf(1, 2))
            )
        }
    }
}