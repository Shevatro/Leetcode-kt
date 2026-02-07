package lc1_999.lc300_399

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

//From Beyond Cracking The Coding Interview, Solved
//From an interview
//https://leetcode.com/problems/intersection-of-two-arrays-ii/
class Task350 {
    fun intersect(nums1: IntArray, nums2: IntArray): IntArray {
        return SolutionUsingMaps(nums1, nums2).intersect()
    }

    private class SolutionUsingSorting(
        private val nums1: IntArray,
        private val nums2: IntArray
    ) {
        fun intersect(): IntArray {
            nums1.sort()
            nums2.sort()
            val result = mutableListOf<Int>()
            var nums1P = 0
            var nums2P = 0
            while (nums1P < nums1.size && nums2P < nums2.size) {
                if (nums1[nums1P] == nums2[nums2P]) {
                    result.add(nums1[nums1P])
                    nums1P++
                    nums2P++
                } else if (nums1[nums1P] < nums2[nums2P]) {
                    nums1P++
                } else {
                    nums2P++
                }
            }
            return result.toIntArray()
        }
    }

    private class SolutionUsingMaps(
        private val nums1: IntArray,
        private val nums2: IntArray
    ) {
        private val frequency = mutableMapOf<Int, Int>()
        private val shortestNums = if (nums1.size > nums2.size) nums2 else nums1
        fun intersect(): IntArray {
            fillOutFrequency()
            return findResult()
        }

        private fun fillOutFrequency() {
            for (num in shortestNums) {
                if (frequency[num] == null) {
                    frequency[num] = 0
                }
                frequency[num] = frequency[num]!! + 1
            }
        }

        private fun findResult(): IntArray {
            val result = mutableListOf<Int>()
            val longestNums = if (nums1 == shortestNums) nums2 else nums1
            for (num in longestNums) {
                if (frequency[num] != null && frequency[num]!! > 0) {
                    frequency[num] = frequency[num]!! - 1
                    result.add(num)
                }
            }
            return result.toIntArray()
        }
    }
}

private class Task350Test {
    private val task = Task350()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input1: IntArray, input2: IntArray, expected: IntArray) {
        val actual = task.intersect(input1, input2)
        //sort them to keep the order
        expected.sort()
        actual.sort()
        Assertions.assertArrayEquals(expected, actual)
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(intArrayOf(1, 2, 2, 1), intArrayOf(2, 2), intArrayOf(2, 2)),
                Arguments.of(intArrayOf(4, 9, 5), intArrayOf(9, 4, 9, 8, 4), intArrayOf(4, 9))
            )
        }
    }
}