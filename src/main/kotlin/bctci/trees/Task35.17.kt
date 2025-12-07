package bctci.trees

import common.IntTreeNode
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Task35_17 {
    fun mergeBSTIntoArray(root1: IntTreeNode?, root2: IntTreeNode?): List<Int> {
        return Solution().mergeBSTIntoArray(root1, root2)
    }

    private class Solution {
        fun mergeBSTIntoArray(root1: IntTreeNode?, root2: IntTreeNode?): List<Int> {
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

private class Task35_17Test {
    private val task = Task35_17()

    @Test
    fun test1() {
        val tree1 = IntTreeNode(5).apply {
            left = IntTreeNode(2).apply {
                right = IntTreeNode(4)
            }
            right = IntTreeNode(9).apply {
                left = IntTreeNode(9)
                right = IntTreeNode(11)
            }
        }
        val tree2 = IntTreeNode(3).apply {
            left = IntTreeNode(2).apply {
                left = IntTreeNode(1)
            }
            right = IntTreeNode(7).apply {
                left = IntTreeNode(6)
                right = IntTreeNode(8)
            }
        }
        val expected = listOf(1, 2, 2, 3, 4, 5, 6, 7, 8, 9, 9, 11)
        Assertions.assertEquals(expected, task.mergeBSTIntoArray(tree1, tree2))
    }

    @Test
    fun test2() {
        val tree1 = IntTreeNode(2).apply {
            left = IntTreeNode(2)
            right = IntTreeNode(2)
        }
        val tree2 = IntTreeNode(2).apply {
            left = IntTreeNode(2)
            right = IntTreeNode(2)
        }
        val expected = listOf(2, 2, 2, 2, 2, 2)
        Assertions.assertEquals(expected, task.mergeBSTIntoArray(tree1, tree2))
    }

    @Test
    fun test3() {
        Assertions.assertEquals(emptyList<Int>(), task.mergeBSTIntoArray(null, null))
    }

    @Test
    fun test4() {
        val tree = IntTreeNode(1)
        val expected = listOf(1)
        Assertions.assertEquals(expected, task.mergeBSTIntoArray(null, tree))
    }

    @Test
    fun test5() {
        val tree = IntTreeNode(1)
        val expected = listOf(1)
        Assertions.assertEquals(expected, task.mergeBSTIntoArray(tree, null))
    }

    @Test
    fun test6() {
        val tree1 = IntTreeNode(1)
        val tree2 = IntTreeNode(2)
        val expected = listOf(1, 2)
        Assertions.assertEquals(expected, task.mergeBSTIntoArray(tree1, tree2))
    }
}