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
//        return Solution3().isValidBST(root)
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

    private class Solution3 {
        fun isValidBST(root: IntTreeNode?): Boolean {
            return isBST(root, null, null)
        }

        private fun isBST(root: IntTreeNode?, min: Int?, max: Int?): Boolean {
            if (root == null) return true
            if (min != null && min >= root.`val` || max != null && max <= root.`val`) return false
            val isLeftValid = isBST(root.left, min, root.`val`)
            val isRightValid = isBST(root.right, root.`val`, max)
            return isLeftValid && isRightValid
        }
    }
}


private class Task98Test {
    private val task = Task98()

    @Test
    fun isValidBSTTest1() {
        val tree = IntTreeNode(2).apply {
            left = IntTreeNode(1)
            right = IntTreeNode(3)
        }
        val result = task.isValidBST(tree)
        Assertions.assertEquals(true, result)
    }

    @Test
    fun isValidBSTTest2() {
        val tree = IntTreeNode(5).apply {
            left = IntTreeNode(1)
            right = IntTreeNode(4).apply {
                left = IntTreeNode(3)
                right = IntTreeNode(6)
            }
        }
        val result = task.isValidBST(tree)
        Assertions.assertEquals(false, result)
    }

    @Test
    fun isValidBSTTest3() {
        val tree = IntTreeNode(5).apply {
            left = IntTreeNode(4)
            right = IntTreeNode(6).apply {
                left = IntTreeNode(3)
                right = IntTreeNode(7)
            }
        }
        val result = task.isValidBST(tree)
        Assertions.assertEquals(false, result)
    }

    @Test
    fun isValidBSTTest4() {
        val tree = IntTreeNode(2).apply {
            left = IntTreeNode(2)
            right = IntTreeNode(2)
        }
        val result = task.isValidBST(tree)
        Assertions.assertEquals(false, result)
    }

    @Test
    fun isValidBSTTest5() {
        val tree = IntTreeNode(1).apply {
            left = IntTreeNode(1)
        }
        val result = task.isValidBST(tree)
        Assertions.assertEquals(false, result)
    }

    @Test
    fun isValidBSTTest6() {
        val tree = IntTreeNode(-2147483648)
        val result = task.isValidBST(tree)
        Assertions.assertEquals(true, result)
    }
}