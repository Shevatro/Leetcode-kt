package lc1_999.lc700_799

import common.IntTreeNode
import common.toLevelsList
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

//From Beyond Cracking The Coding Interview, Solved
//https://leetcode.com/problems/search-in-a-binary-search-tree/description/
class Task700 {
    fun searchBST(root: IntTreeNode?, `val`: Int): IntTreeNode? {
        return Solution(`val`).searchBSTIterative(root)
//        return Solution(`val`).searchBST(root)
    }

    private class Solution(private val value: Int) {
        fun searchBST(root: IntTreeNode?): IntTreeNode? {
            if (root == null) return null
            if (root.`val` == value) return root
            val nextItem = if (root.`val` < value) root.right else root.left
            return searchBST(nextItem)
        }

        fun searchBSTIterative(root: IntTreeNode?): IntTreeNode? {
            var p = root
            while (p != null && p.`val` != value) {
                p = if (p.`val` < value) p.right else p.left
            }
            return p
        }
    }
}

private class Task700Test {
    private val task = Task700()

    @Test
    fun test1() {
        val tree = IntTreeNode(4).apply {
            left = IntTreeNode(2).apply {
                left = IntTreeNode(1)
                right = IntTreeNode(3)
            }
            right = IntTreeNode(7)
        }
        val expected = listOf(2, 1, 3)
        Assertions.assertEquals(expected, task.searchBST(tree, 2)?.toLevelsList())
    }

    @Test
    fun test2() {
        val tree = IntTreeNode(4).apply {
            left = IntTreeNode(2).apply {
                left = IntTreeNode(1)
                right = IntTreeNode(3)
            }
            right = IntTreeNode(7)
        }
        Assertions.assertEquals(null, task.searchBST(tree, 5))
    }
}