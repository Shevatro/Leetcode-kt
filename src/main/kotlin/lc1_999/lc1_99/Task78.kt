package lc1_999.lc1_99

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

//From Cracking The Coding Interview, Solved
//https://leetcode.com/problems/subsets/
class Task78 {
    fun subsets(nums: IntArray): List<List<Int>> {
        return Solution(nums).subsets()
    }

    private class Solution2(private val nums: IntArray) {
        private val result = mutableListOf<List<Int>>().apply {
            add(listOf())
        }

        fun subsets(): List<List<Int>> {
            for (i in nums.indices) {
                subsets(i, listOf(nums[i]))
            }
            return result
        }

        private fun subsets(pos: Int, item: List<Int>) {
            if (pos == nums.size) {
                return
            }
            result.add(item)
            for (i in pos + 1 until nums.size) {
                val newItem = item.toMutableList().apply {
                    this.add(nums[i])
                }
                subsets(i, newItem)
            }
        }
    }

    private class Solution(private val s: IntArray) {
        private val result = mutableListOf<List<Int>>()
        private val curSubSet = ArrayDeque<Int>()
        fun subsets(): List<List<Int>> {
            dfs(0)
            return result
        }

        private fun dfs(i: Int) {
            if (i == s.size) {
                result.add(curSubSet.toList())
                return
            }
            //choice1: pick s[i]
            curSubSet.addLast(s[i])
            dfs(i + 1)
            //choice2: skip s[i]
            curSubSet.removeLast()
            dfs(i + 1)
        }
    }
}

private class Task78Test {
    private val task = Task78()

    @Test
    fun subsetsTest1() {
        val input = intArrayOf(1, 2, 3)
        val actual = task.subsets(input)
        val expected = listOf(
            emptyList(), listOf(1), listOf(2), listOf(1, 2), listOf(3), listOf(1, 3), listOf(2, 3), listOf(1, 2, 3)
        )
        Assertions.assertEquals(expected.toSet(), actual.toSet())
    }

    @Test
    fun subsetsTest2() {
        val input = intArrayOf(0)
        val actual = task.subsets(input)
        val expected = listOf(
            emptyList(), listOf(0)
        )
        Assertions.assertEquals(expected.toSet(), actual.toSet())
    }

    @Test
    fun subsetsTest3() {
        val input = intArrayOf(1, 2, 3, 4)
        val actual = task.subsets(input)
        val expected = listOf(
            emptyList(), listOf(1), listOf(2), listOf(1, 2), listOf(3), listOf(1, 3), listOf(2, 3),
            listOf(1, 2, 3), listOf(4), listOf(1, 4), listOf(2, 4), listOf(1, 2, 4), listOf(3, 4), listOf(1, 3, 4),
            listOf(2, 3, 4), listOf(1, 2, 3, 4)
        )
        Assertions.assertEquals(expected.toSet(), actual.toSet())
    }
}