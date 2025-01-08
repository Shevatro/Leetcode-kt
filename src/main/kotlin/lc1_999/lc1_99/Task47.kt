package lc1_999.lc1_99

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.LinkedList

//From Cracking The Coding Interview, Solved
//https://leetcode.com/problems/permutations-ii/
class Task47 {
    fun permuteUnique(nums: IntArray): List<List<Int>> {
        return Solution2(nums).permuteUnique()
    }

    private class Solution2(private val nums: IntArray) {
        private val frequency = mutableMapOf<Int, Int>()
        private val result = mutableListOf<List<Int>>()
        private val current = LinkedList<Int>()
        private val uniqueNums = nums.toSet()

        fun permuteUnique(): List<List<Int>> {
            countFrequency()
            permute()
            return result
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
                for (num in uniqueNums) {
                    val amount = frequency[num] ?: 0
                    if (amount == 0) continue
                    current.addLast(num)
                    frequency[num] = amount - 1
                    permute()
                    frequency[num] = amount
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