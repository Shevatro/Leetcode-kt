package bctci.trees

import common.IntTreeNode
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Task35_16 {
    fun findKthElementInBST(root: IntTreeNode?, k: Int): Int {
        return Solution(k).findKthElementInBST(root)
    }

    private class Solution(private val k: Int) {
        private var count = 0
        private var result: Int? = null
        fun findKthElementInBST(root: IntTreeNode?): Int {
            dfs(root)
            return result ?: -1
        }

        private fun dfs(node: IntTreeNode?) {
            if (node == null || result != null) return
            dfs(node.left)
            if (count == k) result = node.`val`
            count++
            dfs(node.right)
        }
    }
}

private class Task35_16Test {
    private val task = Task35_16()

    @Test
    fun test1() {
        val tree = IntTreeNode(5).apply {
            left = IntTreeNode(2).apply {
                right = IntTreeNode(4)
            }
            right = IntTreeNode(9).apply {
                left = IntTreeNode(9)
                right = IntTreeNode(11)
            }
        }
        Assertions.assertEquals(9, task.findKthElementInBST(tree, 4))
    }

    @Test
    fun test2() {
        val tree = IntTreeNode(1)
        Assertions.assertEquals(1, task.findKthElementInBST(tree, 0))
    }

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun tests(input: Int, expected: Int) {
        val tree = IntTreeNode(5).apply {
            left = IntTreeNode(2).apply {
                left = IntTreeNode(1)
                right = IntTreeNode(4)
            }
            right = IntTreeNode(8).apply {
                left = IntTreeNode(6)
                right = IntTreeNode(9)
            }
        }
        Assertions.assertEquals(expected, task.findKthElementInBST(tree, input))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(0, 1),
                Arguments.of(1, 2),
                Arguments.of(2, 4),
                Arguments.of(3, 5),
                Arguments.of(4, 6),
                Arguments.of(5, 8),
                Arguments.of(6, 9)
            )
        }
    }
}