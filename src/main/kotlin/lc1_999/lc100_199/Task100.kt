package lc1_999.lc100_199

import common.IntTreeNode
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

//Solved
//https://leetcode.com/problems/same-tree/
class Task100 {
    fun isSameTree(p: IntTreeNode?, q: IntTreeNode?): Boolean {
        if (p == null && q == null) return true
        //only check backward
        if (p?.right == null && p?.left == null && q?.right == null && q?.left == null) {
            return p?.`val` == q?.`val`
        }
        val left = isSameTree(p?.left, q?.left)
        val right = isSameTree(p?.right, q?.right)
        return left && right && p?.`val` == q?.`val`
    }

    fun isSameTreeIdeal(p: IntTreeNode?, q: IntTreeNode?): Boolean {
        if (p == null && q == null) return true
        if (p == null || q == null) return false
        if (p.`val` != q.`val`) return false
        return isSameTreeIdeal(p.left, q.left) && isSameTreeIdeal(p.right, q.right)
    }
}

private class Task100Test {
    private val task = Task100()

    @Test
    fun test1() {
        val p = IntTreeNode(1).apply {
            left = IntTreeNode(2)
            right = IntTreeNode(3)
        }
        val q = IntTreeNode(1).apply {
            left = IntTreeNode(2)
            right = IntTreeNode(3)
        }
        Assertions.assertEquals(true, task.isSameTree(p, q))
    }

    @Test
    fun test2() {
        val p = IntTreeNode(1).apply {
            left = IntTreeNode(2)
        }
        val q = IntTreeNode(1).apply {
            right = IntTreeNode(2)
        }
        Assertions.assertEquals(false, task.isSameTree(p, q))
    }
    @Test
    fun test3() {
        val p = IntTreeNode(1).apply {
            left = IntTreeNode(2)
            right = IntTreeNode(1)
        }
        val q = IntTreeNode(1).apply {
            left = IntTreeNode(1)
            right = IntTreeNode(2)
        }
        Assertions.assertEquals(false, task.isSameTree(p, q))
    }
    @Test
    fun test4() {
        val p = IntTreeNode(1).apply {
            right = IntTreeNode(2).apply {
                left = IntTreeNode(4).apply {
                    right = IntTreeNode(3)
                }
            }
        }
        val q = IntTreeNode(1).apply {
            right = IntTreeNode(4).apply {
                left = IntTreeNode(2).apply {
                    right = IntTreeNode(3)
                }
            }
        }
        Assertions.assertEquals(false, task.isSameTree(p, q))
    }
}