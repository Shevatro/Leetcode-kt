package lc1_999.lc300_399

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.PriorityQueue
import java.util.stream.Stream

//Similar to Beyond Cracking The Coding Interview, Solved
//https://leetcode.com/problems/find-k-pairs-with-smallest-sums/
class Task373 {
    fun kSmallestPairs(nums1: IntArray, nums2: IntArray, k: Int): List<List<Int>> {
        val duplicates = mutableSetOf<Pair<Int, Int>>()
        val maxHeap = PriorityQueue<Pair<Int, Int>>(compareBy { nums1[it.first] + nums2[it.second] })
        maxHeap.add(0 to 0)

        val result = mutableListOf<List<Int>>()
        while (maxHeap.isNotEmpty() && result.size < k) {
            val item = maxHeap.poll()
            result.add(listOf(nums1[item.first], nums2[item.second]))
            val nextFirstPos = item.first + 1
            val newFirstPair = nextFirstPos to item.second
            if (nextFirstPos <= nums1.lastIndex && !duplicates.contains(newFirstPair)) {
                maxHeap.add(newFirstPair)
                duplicates.add(newFirstPair)
            }
            val nextSecondPos = item.second + 1
            val newSecondPair = item.first to nextSecondPos
            if (nextSecondPos <= nums2.lastIndex && !duplicates.contains(newSecondPair)) {
                maxHeap.add(newSecondPair)
                duplicates.add(newSecondPair)
            }
        }
        return result
    }
}

private class Task373Test {
    private val task = Task373()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input1: IntArray, input2: IntArray, input3: Int, expected: List<List<Int>>) {
        Assertions.assertEquals(expected, task.kSmallestPairs(input1, input2, input3))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    intArrayOf(1, 7, 11), intArrayOf(2, 4, 6), 3,
                    listOf(listOf(1, 2), listOf(1, 4), listOf(1, 6))
                ),
                Arguments.of(intArrayOf(1, 1, 2), intArrayOf(1, 2, 3), 2, listOf(listOf(1, 1), listOf(1, 1))),
                Arguments.of(intArrayOf(1), intArrayOf(2, 4, 6), 3, listOf(listOf(1, 2), listOf(1, 4), listOf(1, 6)))
            )
        }
    }
}