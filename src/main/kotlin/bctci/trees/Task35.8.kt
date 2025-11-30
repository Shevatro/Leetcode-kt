package bctci.trees

import common.IntTreeNode
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Task35_8 {
    fun getLeftView(root: IntTreeNode?): List<Int> {
        if (root == null) return emptyList()
        val result = mutableListOf<Int>()
        var curLevel = 0
        val queue = ArrayDeque<Data>()
        queue.addLast(Data(root, 0))
        while (queue.isNotEmpty()) {
            val item = queue.removeFirst()
            if (curLevel == item.level) {
                result.add(item.node.`val`)
                curLevel++
            }
            if (item.node.left != null) {
                queue.addLast(Data(requireNotNull(item.node.left), item.level + 1))
            }
            if (item.node.right != null) {
                queue.addLast(Data(requireNotNull(item.node.right), item.level + 1))
            }
        }
        return result
    }

    private data class Data(
        val node: IntTreeNode,
        val level: Int
    )
}

private class Task35_8Test {
    private val task = Task35_8()

    @Test
    fun test1() {
        val tree = IntTreeNode(1).apply {
            left = IntTreeNode(2).apply {
                right = IntTreeNode(5)
            }
            right = IntTreeNode(3).apply {
                right = IntTreeNode(6).apply {
                    right = IntTreeNode(7)
                }
            }
        }
        val expectedResult = listOf(1, 2, 5, 7)
        Assertions.assertEquals(expectedResult, task.getLeftView(tree))
    }

    @Test
    fun test2() {
        Assertions.assertEquals(emptyList<Int>(), task.getLeftView(null))
    }

    @Test
    fun test3() {
        val tree = IntTreeNode(1)
        Assertions.assertEquals(listOf(1), task.getLeftView(tree))
    }

    @Test
    fun test4() {
        val tree = IntTreeNode(1).apply {
            right = IntTreeNode(2).apply {
                right = IntTreeNode(3)
            }
        }
        val expectedResult = listOf(1, 2, 3)
        Assertions.assertEquals(expectedResult, task.getLeftView(tree))
    }

    @Test
    fun test5() {
        val tree = IntTreeNode(1).apply {
            left = IntTreeNode(2).apply {
                left = IntTreeNode(3)
            }
        }
        val expectedResult = listOf(1, 2, 3)
        Assertions.assertEquals(expectedResult, task.getLeftView(tree))
    }

    @Test
    fun test6() {
        val tree = IntTreeNode(1).apply {
            left = IntTreeNode(2).apply {
                left = IntTreeNode(4)
                right = IntTreeNode(5)
            }
            right = IntTreeNode(3).apply {
                right = IntTreeNode(6)
            }
        }
        val expectedResult = listOf(1, 2, 4)
        Assertions.assertEquals(expectedResult, task.getLeftView(tree))
    }
}