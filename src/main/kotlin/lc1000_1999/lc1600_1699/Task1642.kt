package lc1000_1999.lc1600_1699

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.PriorityQueue
import java.util.stream.Stream

//Similar to Beyond Cracking The Coding Interview, Not Solved
//https://leetcode.com/problems/furthest-building-you-can-reach/description/
class Task1642 {
    fun furthestBuilding(heights: IntArray, bricks: Int, ladders: Int): Int {
        val minHeap = PriorityQueue<Int>()
        var bricksLeftover = bricks
        for (i in 1..heights.lastIndex) {
            val diff = heights[i] - heights[i - 1]
            //if we go down -> skip
            if (diff <= 0) continue
            minHeap.add(diff)
            //if we reach max ladders -> we have to replace the smallest gap with bricks
            if (minHeap.size > ladders) {
                val smallestItem = minHeap.poll()
                bricksLeftover -= smallestItem
                //we exceed leftover of bricks -> go to the previous building
                if (bricksLeftover < 0) {
                    return i - 1
                }
            }
        }
        //reached last building successfully
        return heights.lastIndex
    }
}

private class Task1642Test {
    private val task = Task1642()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input1: IntArray, input2: Int, input3: Int, expected: Int) {
        Assertions.assertEquals(expected, task.furthestBuilding(input1, input2, input3))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(intArrayOf(4, 2, 7, 6, 9, 14, 12), 5, 1, 4),
                Arguments.of(intArrayOf(4, 12, 2, 7, 3, 18, 20, 3, 19), 10, 2, 7),
                Arguments.of(intArrayOf(4, 3, 19, 3), 17, 0, 3),
                Arguments.of(intArrayOf(1, 5, 1, 2, 3, 4, 10000), 4, 1, 5),
                Arguments.of(intArrayOf(4, 2, 7, 6, 9, 14, 12), 0, 1, 3),
                Arguments.of(intArrayOf(4, 2, 7, 6, 9, 14, 12), 0, 0, 1)
            )
        }
    }
}