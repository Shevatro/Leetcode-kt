package lc1_999.lc600_699

import common.IntTreeNode
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

//Similar to Beyond Cracking The Coding Interview, Solved
//https://leetcode.com/problems/two-sum-iv-input-is-a-bst/description/
class Task653 {
    fun findTarget(root: IntTreeNode?, k: Int): Boolean {
        return Solution().findTarget(root, k)
    }

    class Solution() {
        private val visited = HashSet<Int>()
        fun findTarget(root: IntTreeNode?, k: Int): Boolean {
            if (root == null) return false
            if (visited.contains(root.`val`)) return true
            visited.add(k - root.`val`)
            return findTarget(root.left, k) || findTarget(root.right, k)
        }
    }
}

private class Task653Test {
    private val task = Task653()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input: Int, expected: Boolean) {
        val tree = IntTreeNode(5).apply {
            left = IntTreeNode(3).apply {
                left = IntTreeNode(2)
                right = IntTreeNode(4)
            }
            right = IntTreeNode(6).apply {
                right = IntTreeNode(7)
            }
        }
        Assertions.assertEquals(expected, task.findTarget(tree, input))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(9, true),
                Arguments.of(28, false)
            )
        }
    }
}