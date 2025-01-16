package lc1_999.lc1_99

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

//From a learning section, Solved
//https://leetcode.com/problems/merge-sorted-array/
class Task88 {

    fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int): Unit {
        var nums1Index = m - 1
        var nums2Index = n - 1
        for (k in nums1.lastIndex downTo 0) {
            if (nums2Index < 0 || nums1Index >= 0 && nums1[nums1Index] > nums2[nums2Index]) {
                nums1[k] = nums1[nums1Index]
                nums1Index--
            } else {
                nums1[k] = nums2[nums2Index]
                nums2Index--
            }
        }
    }
}

private class Task88Test {
    private val task = Task88()

    @Test
    fun test1() {
        val input = intArrayOf(1, 2, 3, 0, 0, 0)
        task.merge(input, 3, intArrayOf(2, 5, 6), 3)
        val expectedResult = intArrayOf(1, 2, 2, 3, 5, 6)
        Assertions.assertArrayEquals(expectedResult, input)
    }

    @Test
    fun test2() {
        val input = intArrayOf(1)
        task.merge(input, 1, intArrayOf(), 0)
        val expectedResult = intArrayOf(1)
        Assertions.assertArrayEquals(expectedResult, input)
    }

    @Test
    fun test3() {
        val input = intArrayOf(0)
        task.merge(input, 0, intArrayOf(1), 1)
        val expectedResult = intArrayOf(1)
        Assertions.assertArrayEquals(expectedResult, input)
    }

    @Test
    fun test4() {
        val input = intArrayOf()
        task.merge(input, 0, intArrayOf(), 0)
        val expectedResult = intArrayOf()
        Assertions.assertArrayEquals(expectedResult, input)
    }
}

