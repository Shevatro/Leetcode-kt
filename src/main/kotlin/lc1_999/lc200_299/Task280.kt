package lc1_999.lc200_299
//From Cracking The Coding Interview, Solved but not optimal
//https://leetcode.com/problems/wiggle-sort/
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Task280 {
    fun wiggleSort(nums: IntArray): Unit {
        if (nums.size == 1) return
        for (i in 1 until nums.size) {
            if ((i % 2 == 0) == (nums[i - 1] < nums[i])) {
                swapItems(nums, i)
            }
        }
    }

    fun wiggleSortSimple(nums: IntArray): Unit {
        if (nums.size == 1) return
        val newNums = nums.clone()
        newNums.sort()
        var j = nums.size - 1
        var i = 0
        var k = 0
        while (i <= j) {
            nums[k] = newNums[i]
            if (nums.getOrNull(k + 1) != null) {
                nums[k + 1] = newNums[j]
            }
            k += 2
            i++
            j--
        }
    }

    private fun swapItems(nums: IntArray, i: Int) {
        val temp = nums[i]
        nums[i] = nums[i - 1]
        nums[i - 1] = temp
    }
}

private class Task280Test {
    private val task = Task280()

    @Test
    fun test1() {
        val input = intArrayOf(3, 5, 2, 1, 6, 4)
        task.wiggleSort(input)
        val expectedResult = intArrayOf(3, 5, 1, 6, 2, 4)
        Assertions.assertArrayEquals(expectedResult, input)
    }

    @Test
    fun test2() {
        val input = intArrayOf(6, 6, 5, 6, 3, 8)
        task.wiggleSort(input)
        val expectedResult = intArrayOf(6, 6, 5, 6, 3, 8)
        Assertions.assertArrayEquals(expectedResult, input)
    }

    @Test
    fun test3() {
        val input = intArrayOf(1, 2, 3, 4, 5, 6)
        task.wiggleSort(input)
        val expectedResult = intArrayOf(1, 3, 2, 5, 4, 6)
        Assertions.assertArrayEquals(expectedResult, input)
    }

    @Test
    fun test4() {
        val input = intArrayOf(2, 1)
        task.wiggleSort(input)
        val expectedResult = intArrayOf(1, 2)
        Assertions.assertArrayEquals(expectedResult, input)
    }

    @Test
    fun test5() {
        val input = intArrayOf(1, 3, 4, 2)
        task.wiggleSort(input)
        val expectedResult = intArrayOf(1, 4, 2, 3)
        Assertions.assertArrayEquals(expectedResult, input)
    }

    @Test
    fun test6() {
        val input = intArrayOf(1, 1, 1)
        task.wiggleSort(input)
        val expectedResult = intArrayOf(1, 1, 1)
        Assertions.assertArrayEquals(expectedResult, input)
    }

    @Test
    fun test7() {
        val input = intArrayOf(3, 8, 7, 4, 7)
        task.wiggleSort(input)
        val expectedResult = intArrayOf(3, 8, 4, 7, 7)
        Assertions.assertArrayEquals(expectedResult, input)
    }
}