package lc1_999.lc1_99

import common.IntTreeNode
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

//From Cracking The Coding Interview, Not Solved
//https://leetcode.com/problems/validate-binary-search-tree/
class Task98 {
    fun isValidBST(root: IntTreeNode?): Boolean {
        return Solution().isValidBST(root)
//        return Solution2().isValidBST(root)
    }

    private class Solution {
        private var previousValue: Int? = null
        fun isValidBST(root: IntTreeNode?): Boolean {
            previousValue = null
            return isValid(root)
        }

        private fun isValid(root: IntTreeNode?): Boolean {
            if (root == null) return true
            //left
            if (!isValid(root.left)) return false
            //current
            if (!(previousValue == null || previousValue!! < root.`val`)) return false
            previousValue = root.`val`
            //right, if nobody violates the order -> return true
            return isValid(root.right)
        }
    }

    private class Solution2 {
        private var sortedList = ArrayList<Int>()
        fun isValidBST(root: IntTreeNode?): Boolean {
            sortedList = ArrayList()
            traverseAndAddItems(root)
            return checkIfSorted()
        }

        private fun traverseAndAddItems(root: IntTreeNode?) {
            if (root == null) return
            //left
            traverseAndAddItems(root.left)
            //current
            sortedList.add(root.`val`)
            //right
            return traverseAndAddItems(root.right)
        }

        private fun checkIfSorted(): Boolean {
            var previous: Int? = null
            for (item in sortedList) {
                if (previous != null && item <= previous) return false
                previous = item
            }
            return true
        }
    }
}


private class Task110Test {
    @Test
    fun isValidBSTTest() {
        val task = Task98()
        val tree = IntTreeNode(2).apply {
            left = IntTreeNode(1)
            right = IntTreeNode(3)
        }
        val result = task.isValidBST(tree)
        Assertions.assertEquals(true, result)

        val tree2 = IntTreeNode(5).apply {
            left = IntTreeNode(1)
            right = IntTreeNode(4).apply {
                left = IntTreeNode(3)
                right = IntTreeNode(6)
            }
        }
        val result2 = task.isValidBST(tree2)
        Assertions.assertEquals(false, result2)

        val tree3 = IntTreeNode(5).apply {
            left = IntTreeNode(4)
            right = IntTreeNode(6).apply {
                left = IntTreeNode(3)
                right = IntTreeNode(7)
            }
        }
        val result3 = task.isValidBST(tree3)
        Assertions.assertEquals(false, result3)

        val tree4 = IntTreeNode(2).apply {
            left = IntTreeNode(2)
            right = IntTreeNode(2)
        }
        val result4 = task.isValidBST(tree4)
        Assertions.assertEquals(false, result4)
    }
}