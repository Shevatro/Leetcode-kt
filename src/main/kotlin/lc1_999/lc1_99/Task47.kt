package lc1_999.lc1_99

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

//From Cracking The Coding Interview, Solved
//https://leetcode.com/problems/permutations-ii/
class Task47 {
    fun permuteUnique(nums: IntArray): List<List<Int>> {
        return Solution2(nums).permuteUnique()
    }

    private class Solution2(private val nums: IntArray) {
        private val frequency = mutableMapOf<Int, Int>()
        private val result = mutableSetOf<List<Int>>()
        private val current = mutableListOf<Int>()

        fun permuteUnique(): List<List<Int>> {
            countFrequency()
            permute()
            return result.toList()
        }

        private fun countFrequency() {
            for (num in nums) {
                frequency[num] = frequency.getOrDefault(num, 0) + 1
            }
        }

        private fun permute() {
            // print(current+ " "+frequency)
            if (current.size == nums.size) {
                // println(" found result")
                result.add(current.toList())
            } else {
                // println(" continue")
                for (num in nums) {
                    if (frequency[num]!! == 0) continue
                    current.add(num)
                    frequency[num] = frequency[num]!! - 1
                    permute()
                    frequency[num] = frequency[num]!! + 1
                    current.removeLast()
                }
            }
        }
    }
}

private class Task47Test {
    private val task = Task47()

    @Test
    fun permuteTest1() {
        val actualResult = task.permuteUnique(intArrayOf(1, 1, 2))
        val expectedResult = listOf(listOf(1, 1, 2), listOf(1, 2, 1), listOf(2, 1, 1))
        Assertions.assertEquals(3, actualResult.size)
        Assertions.assertEquals(expectedResult, actualResult)
    }

    @Test
    fun permuteTest2() {
        val actualResult = task.permuteUnique(intArrayOf(1, 2, 3))
        val expectedResult = listOf(
            listOf(1, 2, 3), listOf(1, 3, 2), listOf(2, 1, 3),
            listOf(2, 3, 1), listOf(3, 1, 2), listOf(3, 2, 1)
        )
        Assertions.assertEquals(6, actualResult.size)
        Assertions.assertEquals(emptySet<Int>(), actualResult.subtract(expectedResult))
    }


    @Test
    fun permuteTest3() {
        val actualResult = task.permuteUnique(intArrayOf(1))
        val expectedResult = listOf(listOf(1))
        Assertions.assertEquals(1, actualResult.size)
        Assertions.assertEquals(expectedResult, actualResult)
    }

    @Test
    fun permuteTest4() {
        val actualResult = task.permuteUnique(intArrayOf(2, 2, 3, 3, 3))
        val expectedResult = listOf(
            listOf(2, 2, 3, 3, 3), listOf(2, 3, 2, 3, 3), listOf(2, 3, 3, 2, 3), listOf(2, 3, 3, 3, 2),
            listOf(3, 2, 2, 3, 3), listOf(3, 2, 3, 2, 3), listOf(3, 2, 3, 3, 2), listOf(3, 3, 2, 2, 3),
            listOf(3, 3, 2, 3, 2), listOf(3, 3, 3, 2, 2)
        )
        Assertions.assertEquals(10, actualResult.size)
        Assertions.assertEquals(emptySet<Int>(), actualResult.subtract(expectedResult))
    }
}