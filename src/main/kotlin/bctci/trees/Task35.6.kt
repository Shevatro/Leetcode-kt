package bctci.trees

import common.IntTreeNode
import common.toLevelsList
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Task35_6 {
    fun invertBinaryTree(root: IntTreeNode?): IntTreeNode? {
        dfs(root)
        return root
    }

    private fun dfs(node: IntTreeNode?) {
        if (node == null) return
        val temp = node.left
        node.left = node.right
        node.right = temp
        dfs(node.left)
        dfs(node.right)
    }
}

private class Task35_6Test {
    private val task = Task35_6()

    @Test
    fun test1() {
        val tree = IntTreeNode(1).apply {
            left = IntTreeNode(6).apply {
                left = IntTreeNode(4).apply {
                    right = IntTreeNode(5)
                }
                right = IntTreeNode(11)
            }
            right = IntTreeNode(7).apply {
                left = IntTreeNode(2).apply {
                    right = IntTreeNode(9)
                }
            }
        }
        val expTree = IntTreeNode(1).apply {
            left = IntTreeNode(7).apply {
                right = IntTreeNode(2).apply {
                    left = IntTreeNode(9)
                }
            }
            right = IntTreeNode(6).apply {
                right = IntTreeNode(4).apply {
                    left = IntTreeNode(5)
                }
                left = IntTreeNode(11)
            }
        }
        Assertions.assertEquals(expTree.toLevelsList(), task.invertBinaryTree(tree)?.toLevelsList())
    }

    @Test
    fun test2() {
        val tree = IntTreeNode(1).apply {
            left = IntTreeNode(2).apply {
                left = IntTreeNode(3)
            }
        }
        val expTree = IntTreeNode(1).apply {
            right = IntTreeNode(2).apply {
                right = IntTreeNode(3)
            }
        }
        Assertions.assertEquals(expTree.toLevelsList(), task.invertBinaryTree(tree)?.toLevelsList())
    }

    @Test
    fun test3() {
        val tree = IntTreeNode(1)
        Assertions.assertEquals(tree.toLevelsList(), task.invertBinaryTree(tree)?.toLevelsList())
    }

    @Test
    fun test4() {
        Assertions.assertEquals(null, task.invertBinaryTree(null))
    }
}