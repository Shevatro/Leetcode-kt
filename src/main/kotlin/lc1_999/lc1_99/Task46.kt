package lc1_999.lc1_99

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

//From Cracking The Coding Interview, Not Solved
//https://leetcode.com/problems/permutations/
class Task46 {
    fun permute(nums: IntArray): List<List<Int>> {
        return Solution2(nums).permute()
    }

    private class Solution2(private val nums: IntArray) {
        private val result = mutableListOf<List<Int>>()
        private val current = mutableSetOf<Int>()
        fun permute(): List<List<Int>> {
            performPermutation()
            return result
        }

        private fun performPermutation() {
            if (current.size == nums.size) {
                result.add(current.toList())
            } else {
                for (num in nums) {
                    if (current.contains(num)) continue
                    current.add(num)
                    performPermutation()
                    current.remove(num)
                }
            }
        }
    }
}

private class Task46Test {
    private val task = Task46()

    @Test
    fun permuteTest1() {
        val actualResult = task.permute(intArrayOf(1, 2, 3))
        val expectedResult = listOf(
            listOf(1, 2, 3), listOf(1, 3, 2), listOf(2, 1, 3),
            listOf(2, 3, 1), listOf(3, 1, 2), listOf(3, 2, 1)
        )
        Assertions.assertEquals(6, actualResult.size)
        Assertions.assertEquals(emptySet<Int>(), actualResult.subtract(expectedResult))
    }

    @Test
    fun permuteTest2() {
        val actualResult = task.permute(intArrayOf(0, 1))
        val expectedResult = listOf(listOf(0, 1), listOf(1, 0))
        Assertions.assertEquals(2, actualResult.size)
        Assertions.assertEquals(expectedResult, actualResult)
    }

    @Test
    fun permuteTest3() {
        val actualResult = task.permute(intArrayOf(1))
        val expectedResult = listOf(listOf(1))
        Assertions.assertEquals(1, actualResult.size)
        Assertions.assertEquals(expectedResult, actualResult)
    }

    @Test
    fun permuteTest4() {
        val actualResult = task.permute(intArrayOf(1, 2, 3, 4))
        val expectedResult = listOf(
            listOf(1, 2, 3, 4), listOf(2, 1, 3, 4), listOf(3, 1, 2, 4),
            listOf(1, 3, 2, 4), listOf(2, 3, 1, 4), listOf(3, 2, 1, 4),
            listOf(3, 2, 4, 1), listOf(2, 3, 4, 1), listOf(4, 3, 2, 1),
            listOf(3, 4, 2, 1), listOf(2, 4, 3, 1), listOf(4, 2, 3, 1),
            listOf(4, 1, 3, 2), listOf(1, 4, 3, 2), listOf(3, 4, 1, 2),
            listOf(4, 3, 1, 2), listOf(1, 3, 4, 2), listOf(3, 1, 4, 2),
            listOf(2, 1, 4, 3), listOf(1, 2, 4, 3), listOf(4, 2, 1, 3),
            listOf(2, 4, 1, 3), listOf(1, 4, 2, 3), listOf(4, 1, 2, 3)
        )
        Assertions.assertEquals(24, actualResult.size)
        Assertions.assertEquals(emptySet<Int>(), actualResult.subtract(expectedResult))
    }
}