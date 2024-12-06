package lc1_999.lc500_599

import common.IntTreeNode
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

//From Cracking The Coding Interview, Solved
//https://leetcode.com/problems/subtree-of-another-tree/
class Task572 {
    fun isSubtree(root: IntTreeNode?, subRoot: IntTreeNode?): Boolean {
        return Solution(root, subRoot).isSubtree()
    }

    private class Solution(private val root: IntTreeNode?, private val subRoot: IntTreeNode?) {
        private val potentialSubRoots = ArrayList<IntTreeNode>()
        fun isSubtree(): Boolean {
            findSubRootInAnotherTree(root)
            println(potentialSubRoots.map{it.`val`})
            if (potentialSubRoots.isEmpty()) return false
            return potentialSubRoots.any { compareNodes(it, subRoot) ?: false }
        }

        private fun findSubRootInAnotherTree(node: IntTreeNode?) {
            if (node == null) return
            if (node.`val` == subRoot?.`val`) {
                potentialSubRoots.add(node)
            }
            findSubRootInAnotherTree(node.left)
            findSubRootInAnotherTree(node.right)
        }

        private fun compareNodes(node1: IntTreeNode?, node2: IntTreeNode?): Boolean? {
            if (node1 == null && node2 == null) return null
            if (node1?.`val` != node2?.`val`) return false
            val left = compareNodes(node1?.left, node2?.left)
            val right = compareNodes(node1?.right, node2?.right)
            return !(left == false || right == false)
        }
    }
}

private class Task572Test {
    private val task = Task572()

    @Test
    fun isSubtreeTest1() {
        val root = IntTreeNode(3).apply {
            left = IntTreeNode(4).apply {
                left = IntTreeNode(1)
                right = IntTreeNode(2)
            }
            right = IntTreeNode(5)
        }
        val subRoot = IntTreeNode(4).apply {
            left = IntTreeNode(1)
            right = IntTreeNode(2)
        }

        val result = task.isSubtree(root, subRoot)
        Assertions.assertEquals(true, result)
    }

    @Test
    fun isSubtreeTest2() {
        val root = IntTreeNode(3).apply {
            left = IntTreeNode(4).apply {
                left = IntTreeNode(1)
                right = IntTreeNode(2).apply {
                    left = IntTreeNode(0)
                }
            }
            right = IntTreeNode(5)
        }
        val subRoot = IntTreeNode(4).apply {
            left = IntTreeNode(1)
            right = IntTreeNode(2)
        }

        val result = task.isSubtree(root, subRoot)
        Assertions.assertEquals(false, result)
    }

    @Test
    fun isSubtreeTest3() {
        val root = IntTreeNode(1).apply {
            right = IntTreeNode(1)
        }
        val subRoot = IntTreeNode(1)

        val result = task.isSubtree(root, subRoot)
        Assertions.assertEquals(true, result)
    }

    @Test
    fun isSubtreeTest4() {
        val root = IntTreeNode(1).apply {
            right = IntTreeNode(1)
        }
        val subRoot = IntTreeNode(1).apply {
            right = IntTreeNode(1)
        }

        val result = task.isSubtree(root, subRoot)
        Assertions.assertEquals(true, result)
    }
}