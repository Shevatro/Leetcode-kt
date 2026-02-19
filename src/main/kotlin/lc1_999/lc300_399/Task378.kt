package lc1_999.lc300_399

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.PriorityQueue
import java.util.stream.Stream
import kotlin.math.min

//Similar to Beyond Cracking The Coding Interview, Solved
//https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/description/
class Task378 {
    fun kthSmallest(matrix: Array<IntArray>, k: Int): Int {
        val minHeap = PriorityQueue<Pair<Int, Int>>(compareBy { matrix[it.first][it.second] })
        //add a first position for each row
        for (r in 0 until min(k, matrix.size)) {
            minHeap.add(r to 0)
        }

        //the last one is our answer
        repeat(k - 1) {
            val topItem = minHeap.poll()
            val nextC = topItem.second + 1
            if (nextC < matrix[0].size) {
                minHeap.add(topItem.first to nextC)
            }
        }
        val (r, c) = minHeap.poll()
        return matrix[r][c]
    }

    fun kthSmallestFirstVersion(matrix: Array<IntArray>, k: Int): Int {
        val maxHeap = PriorityQueue<Int>(compareByDescending { it })
        for (r in matrix.indices) {
            for (c in matrix[0].indices) {
                maxHeap.add(matrix[r][c])
                if (maxHeap.size > k) maxHeap.poll()
            }
        }
        return maxHeap.poll()
    }
}

private class Task378Test {
    private val task = Task378()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input1: Array<IntArray>, input2: Int, expected: Int) {
        Assertions.assertEquals(expected, task.kthSmallest(input1, input2))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(arrayOf(intArrayOf(1, 5, 9), intArrayOf(10, 11, 13), intArrayOf(12, 13, 15)), 8, 13),
                Arguments.of(arrayOf(intArrayOf(-5)), 1, -5),
                Arguments.of(arrayOf(intArrayOf(1, 5, 9), intArrayOf(10, 11, 13), intArrayOf(12, 13, 15)), 2, 5),
                Arguments.of(arrayOf(intArrayOf(1, 5, 9), intArrayOf(10, 11, 13), intArrayOf(12, 13, 15)), 3, 9)
            )
        }
    }
}