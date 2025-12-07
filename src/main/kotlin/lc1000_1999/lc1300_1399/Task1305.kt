package lc1000_1999.lc1300_1399

import common.IntTreeNode
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

//From Beyond Cracking The Coding Interview, Solved
//https://leetcode.com/problems/all-elements-in-two-binary-search-trees/
class Task1305 {
    fun getAllElements(root1: IntTreeNode?, root2: IntTreeNode?): List<Int> {
        return Solution().getAllElements(root1, root2)
    }

    private class Solution {
        fun getAllElements(root1: IntTreeNode?, root2: IntTreeNode?): List<Int> {
            val list1 = InOrderTraversal(root1).execute()
            val list2 = InOrderTraversal(root2).execute()
            return merge(list1, list2)
        }

        private fun merge(list1: List<Int>, list2: List<Int>): List<Int> {
            val result = mutableListOf<Int>()
            var p1 = 0
            var p2 = 0
            while (p1 < list1.size || p2 < list2.size) {
                if (list2.getOrNull(p2) == null || (list1.getOrNull(p1) != null && list1.getOrNull(p1)!! < list2.getOrNull(p2)!!)) {
                    result.add(list1[p1])
                    p1++
                } else {
                    result.add(list2[p2])
                    p2++
                }
            }
            return result
        }

        private class InOrderTraversal(private val root: IntTreeNode?) {
            private val result = mutableListOf<Int>()
            fun execute(): List<Int> {
                dfs(root)
                return result
            }

            private fun dfs(node: IntTreeNode?) {
                if (node == null) return
                dfs(node.left)
                result.add(node.`val`)
                dfs(node.right)
            }
        }
    }
}

private class Task1305Test {
    private val task = Task1305()

    @Test
    fun test1() {
        val tree1 = IntTreeNode(2).apply {
            left = IntTreeNode(1)
            right = IntTreeNode(4)
        }
        val tree2 = IntTreeNode(1).apply {
            left = IntTreeNode(0)
            right = IntTreeNode(3)
        }
        val expected = listOf(0, 1, 1, 2, 3, 4)
        Assertions.assertEquals(expected, task.getAllElements(tree1, tree2))
    }

    @Test
    fun test2() {
        val tree1 = IntTreeNode(1).apply {
            right = IntTreeNode(8)
        }
        val tree2 = IntTreeNode(8).apply {
            left = IntTreeNode(1)
        }
        val expected = listOf(1, 1, 8, 8)
        Assertions.assertEquals(expected, task.getAllElements(tree1, tree2))
    }
}