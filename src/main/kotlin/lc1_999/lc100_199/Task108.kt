package lc1_999.lc100_199

import org.junit.jupiter.api.Test
import common.IntTreeNode
import common.toLevelsList
import org.junit.jupiter.api.Assertions

//From Cracking The Coding Interview, Not Solved
//https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
class Task108() {
    fun sortedArrayToBST(nums: IntArray): IntTreeNode? {
        return Solution(nums).solve()
    }

    private class Solution(private val nums: IntArray) {
        fun solve(): IntTreeNode? {
            return addNode(0, nums.size - 1)
        }

        private fun addNode(start: Int, end: Int): IntTreeNode? {
            if (start > end) return null
            val middle = (start + end) / 2
            val num = nums[middle]
            return IntTreeNode(num).apply {
                left = addNode(start, middle - 1)
                right = addNode(middle + 1, end)
            }
        }
    }

}

private class Task108Test {
    @Test
    fun sortedArrayToBST() {
        val task = Task108()
        val result = task.sortedArrayToBST(intArrayOf(-10, -3, 0, 5, 9))
        Assertions.assertEquals(result?.`val`, 0)
        Assertions.assertEquals(listOf(0, -10, 5, null, -3, null, 9), result?.toLevelsList())

        val result2 = task.sortedArrayToBST(intArrayOf(-10, -4, -3, -2, -1, 0, 5, 9))
        Assertions.assertEquals(result2?.`val`, -2)
        val actualResult2 = listOf( -2, -4, 0, -10, -3, -1, 5, null, null, null, null, null, null, null, 9)
        Assertions.assertEquals(actualResult2, result2?.toLevelsList())

        val result3 = task.sortedArrayToBST(intArrayOf(2, 4, 6, 8, 10, 20))
        Assertions.assertEquals(result3?.`val`, 6)
        Assertions.assertEquals(listOf(6, 2, 10, null, 4, 8, 20), result3?.toLevelsList())
    }
}