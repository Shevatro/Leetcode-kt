package lc1_999.lc300_399

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

//Solved
//https://leetcode.com/problems/intersection-of-two-arrays/
class Task349() {
    fun intersection(nums1: IntArray, nums2: IntArray): IntArray {
        val nums1Set = nums1.toSet()
        val resultSet = mutableSetOf<Int>()
        for (item in nums2) {
            if (nums1Set.contains(item)) resultSet.add(item)
        }
        return resultSet.toIntArray()
    }

    fun intersectionSimplified(nums1: IntArray, nums2: IntArray): IntArray {
        return nums1.intersect(nums2.toSet()).toIntArray()
    }
}

private class Task349Test {
    private val task = Task349()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input1: IntArray, input2: IntArray, expected: IntArray) {
        Assertions.assertArrayEquals(expected, task.intersection(input1, input2))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(intArrayOf(1, 2, 2, 1), intArrayOf(2, 2), intArrayOf(2)),
                Arguments.of(intArrayOf(4, 9, 5), intArrayOf(9, 4, 9, 8, 4), intArrayOf(9, 4)),
            )
        }
    }
}