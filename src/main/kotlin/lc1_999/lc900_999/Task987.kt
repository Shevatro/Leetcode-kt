package lc1_999.lc900_999

import common.IntTreeNode
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.math.max
import kotlin.math.min

//Similar to Beyond Cracking The Coding Interview, Solved
//https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/description/
class Task987 {
    fun verticalTraversal(root: IntTreeNode?): List<List<Int>> {
        return Solution(root).verticalTraversal()
    }

    class Solution(private val root: IntTreeNode?) {
        private var minInd = Int.MAX_VALUE
        private var maxInd = Int.MIN_VALUE
        private val cache = Array<MutableList<Item>?>(1001) { null }
        fun verticalTraversal(): List<List<Int>> {
            dfs(root, 500, 0)
            return toResult()
        }

        private fun dfs(node: IntTreeNode?, offset: Int, level: Int) {
            if (node == null) return
            if (cache[offset] == null) cache[offset] = mutableListOf()
            cache[offset]?.add(Item(level, node.`val`))
            minInd = min(minInd, offset)
            maxInd = max(maxInd, offset)
            dfs(node.left, offset - 1, level + 1)
            dfs(node.right, offset + 1, level + 1)
        }

        private fun toResult(): List<List<Int>> {
            val result = mutableListOf<List<Int>>()
            for (i in minInd..maxInd) {
                val item = cache[i]
                    ?.asSequence()
                    ?.sortedWith(compareBy<Item> { it.level }.thenBy { it.value })?.map { it.value }
                    ?.toList() ?: emptyList()
                result.add(item)
            }
            return result
        }

        private data class Item(
            val level: Int,
            val value: Int
        )
    }
}

private class Task987Test {
    private val task = Task987()

    @Test
    fun test1() {
        val tree = IntTreeNode(3).apply {
            left = IntTreeNode(9)
            right = IntTreeNode(20).apply {
                left = IntTreeNode(15)
                right = IntTreeNode(7)
            }
        }
        val expected = listOf(listOf(9), listOf(3, 15), listOf(20), listOf(7))
        Assertions.assertEquals(expected, task.verticalTraversal(tree))
    }

    @Test
    fun test2() {
        val tree = IntTreeNode(1).apply {
            left = IntTreeNode(2).apply {
                left = IntTreeNode(4)
                right = IntTreeNode(5)
            }
            right = IntTreeNode(3).apply {
                left = IntTreeNode(6)
                right = IntTreeNode(7)
            }
        }
        val expected = listOf(listOf(4), listOf(2), listOf(1, 5, 6), listOf(3), listOf(7))
        Assertions.assertEquals(expected, task.verticalTraversal(tree))
    }

    @Test
    fun test3() {
        val tree = IntTreeNode(1).apply {
            left = IntTreeNode(2).apply {
                left = IntTreeNode(4)
                right = IntTreeNode(6)
            }
            right = IntTreeNode(3).apply {
                left = IntTreeNode(5)
                right = IntTreeNode(7)
            }
        }
        val expected = listOf(listOf(4), listOf(2), listOf(1, 5, 6), listOf(3), listOf(7))
        Assertions.assertEquals(expected, task.verticalTraversal(tree))
    }

    @Test
    fun test4() {
        val tree = IntTreeNode(3).apply {
            left = IntTreeNode(1).apply {
                left = IntTreeNode(0)
                right = IntTreeNode(2)
            }
            right = IntTreeNode(4).apply {
                left = IntTreeNode(2)
            }
        }
        val expected = listOf(listOf(0), listOf(1), listOf(3, 2, 2), listOf(4))
        Assertions.assertEquals(expected, task.verticalTraversal(tree))
    }
}