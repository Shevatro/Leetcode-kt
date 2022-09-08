package lc1_999.lc900_999

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test
import toString2

//From a learning section, Solved
//https://leetcode.com/problems/sort-array-by-parity/
class Task905 {
    fun sortArrayByParity(nums: IntArray): IntArray {
        var startPos = 0
        var endPos = nums.lastIndex
        while (startPos <= endPos) {
            val isStarOdd = nums[startPos] % 2 != 0
            val isEndEven = nums[endPos] % 2 == 0
            if (isStarOdd && isEndEven) {
                val temp = nums[startPos]
                nums[startPos] = nums[endPos]
                nums[endPos] = temp
                startPos++
                endPos--
            } else {
                if (!isStarOdd) startPos++
                if (!isEndEven) endPos--
            }

        }
        return nums
    }
}

private class Task905Test {
    private val task = Task905()

    @Test
    fun sortArrayByParity1() {
        sortArrayByParity(intArrayOf(4, 2, 1, 3), intArrayOf(3, 1, 2, 4))
    }

    @Test
    fun sortArrayByParity2() {
        sortArrayByParity(intArrayOf(0), intArrayOf(0))
    }

    @Test
    fun sortArrayByParity3() {
        sortArrayByParity(intArrayOf(3, 1, 5, 7), intArrayOf(3, 1, 5, 7))
    }

    @Test
    fun sortArrayByParity4() {
        sortArrayByParity(intArrayOf(2, 4, 8, 10), intArrayOf(2, 4, 8, 10))
    }

    @Test
    fun sortArrayByParity5() {
        sortArrayByParity(intArrayOf(4, 7, 5, 7), intArrayOf(7, 4, 5, 7))
    }

    @Test
    fun sortArrayByParity6() {
        sortArrayByParity(intArrayOf(4, 6, 7), intArrayOf(7, 6, 4))
    }

    private fun sortArrayByParity(expected: IntArray, actualInp: IntArray) {
        val actual = task.sortArrayByParity(actualInp)
        println(actual.toString2())
        assertArrayEquals(expected, actual)
    }
}

