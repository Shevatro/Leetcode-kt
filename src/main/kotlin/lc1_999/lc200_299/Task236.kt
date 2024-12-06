package lc1_999.lc200_299

import common.IntTreeNode
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

//From Cracking The Coding Interview, Solved
//https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
class Task236 {
    fun lowestCommonAncestor(root: IntTreeNode?, p: IntTreeNode?, q: IntTreeNode?): IntTreeNode? {
//        return Solution(root, p, q).lowestCommonAncestor()
        return Solution2(p, q).lowestCommonAncestor(root)
    }

    private class Solution(private val root: IntTreeNode?, private val p: IntTreeNode?, private val q: IntTreeNode?) {
        private var result: IntTreeNode? = null
        fun lowestCommonAncestor(): IntTreeNode? {
            findNodes(root)
            return result
        }

        private fun findNodes(node: IntTreeNode?): Boolean {
            if (node == null) {
                printReturn(node, "base")
                return false
            }
            val leftStatus = findNodes(node.left)
            val rightStatus = findNodes(node.right)
            if (result != null) {
                printReturn(node, "already found the result before")
                return true
            }
            val hasPOrQ = node == p || node == q
            if (hasPOrQ && leftStatus || hasPOrQ && rightStatus || leftStatus && rightStatus) {
                printReturn(node, "Found the result!!!")
                result = node
                return true
            }
            val result = hasPOrQ || leftStatus || rightStatus
            printReturn(node, "return $result")
            return result
        }

        private fun printReturn(node: IntTreeNode?, status: String) {
            if (node == null) return
            println(node.`val`.toString() + " why?: " + status)
        }

    }

    private class Solution2(private val p: IntTreeNode?, private val q: IntTreeNode?) {
        fun lowestCommonAncestor(root: IntTreeNode?): IntTreeNode? {
            // If the root is null or root is either p or q, then root is the LCA
            if (root == null || root == p || root == q) return root
            // Recurse on the left subtree to find the LCA of p and q
            val left = lowestCommonAncestor(root.left)
            // Recurse on the right subtree to find the LCA of p and q
            val right = lowestCommonAncestor(root.right)
            // If finding LCA in the left subtree returns null, the LCA is in the right subtree
            if (left == null) return right
            // If finding LCA in the right subtree returns null, the LCA is in the left subtree
            if (right == null) return left
            // If both left and right are non-null, we've found the LCA at the root
            return root
        }
    }
}

private class Task236Test {
    private val task = Task236()

    @Test
    fun lowestCommonAncestorTest1() {
        val p = IntTreeNode(5).apply {
            left = IntTreeNode(6)
            right = IntTreeNode(2).apply {
                left = IntTreeNode(7)
                right = IntTreeNode(4)
            }
        }
        val q = IntTreeNode(1).apply {
            left = IntTreeNode(0)
            right = IntTreeNode(8)
        }
        val tree = IntTreeNode(3).apply {
            left = p
            right = q
        }

        val result = task.lowestCommonAncestor(tree, p, q)
        Assertions.assertEquals(3, result?.`val`)
    }

    @Test
    fun lowestCommonAncestorTest2() {
        val q = IntTreeNode(4)
        val p = IntTreeNode(5).apply {
            left = IntTreeNode(6)
            right = IntTreeNode(2).apply {
                left = IntTreeNode(7)
                right = q
            }
        }
        val tree = IntTreeNode(3).apply {
            left = p
            right = IntTreeNode(1).apply {
                left = IntTreeNode(0)
                right = IntTreeNode(8)
            }
        }

        val result = task.lowestCommonAncestor(tree, p, q)
        Assertions.assertEquals(5, result?.`val`)
    }

    @Test
    fun lowestCommonAncestorTest3() {
        val p = IntTreeNode(1)
        val q = IntTreeNode(2)
        val tree = p.apply {
            right = q
        }
        val result = task.lowestCommonAncestor(tree, p, q)
        Assertions.assertEquals(1, result?.`val`)
    }

    @Test
    fun lowestCommonAncestorTest4() {
        val q = IntTreeNode(4)
        val p = IntTreeNode(6)
        val tree = IntTreeNode(3).apply {
            left = IntTreeNode(5).apply {
                left = p
                right = IntTreeNode(2).apply {
                    left = IntTreeNode(7)
                    right = q
                }
            }
            right = IntTreeNode(1).apply {
                left = IntTreeNode(0)
                right = IntTreeNode(8)
            }
        }

        val result = task.lowestCommonAncestor(tree, p, q)
        Assertions.assertEquals(5, result?.`val`)
    }

    @Test
    fun lowestCommonAncestorTest5() {
        val q = IntTreeNode(4)
        val p = IntTreeNode(5).apply {
            left = IntTreeNode(6)
            right = IntTreeNode(2).apply {
                left = IntTreeNode(7)
                right = q
            }
        }
        val tree = IntTreeNode(3).apply {
            left = p
            right = IntTreeNode(1).apply {
                left = IntTreeNode(0)
                right = IntTreeNode(8)
            }
        }

        val result = task.lowestCommonAncestor(tree, p, q)
        Assertions.assertEquals(5, result?.`val`)
    }

    @Test
    fun lowestCommonAncestorTest6() {
        val q = IntTreeNode(7)
        val p = IntTreeNode(2).apply {
            left = q
            right = IntTreeNode(4)
        }
        val tree = IntTreeNode(3).apply {
            left = IntTreeNode(5).apply {
                left = IntTreeNode(6)
                right = p
            }
            right = IntTreeNode(1).apply {
                left = IntTreeNode(0)
                right = IntTreeNode(8)
            }
        }

        val result = task.lowestCommonAncestor(tree, p, q)
        Assertions.assertEquals(2, result?.`val`)
    }
}