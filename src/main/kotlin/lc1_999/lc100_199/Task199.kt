package lc1_999.lc100_199

import common.IntTreeNode
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

//From Beyond Cracking The Coding Interview, Solved
//https://leetcode.com/problems/binary-tree-right-side-view/

class Task199 {

    fun rightSideView(root: IntTreeNode?): List<Int> {
        return BFSSolution2().rightSideView(root)
    }

    class BFSSolution2() {
        fun rightSideView(root: IntTreeNode?): List<Int> {
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
                if (item.node.right != null) {
                    queue.addLast(Data(requireNotNull(item.node.right), item.level + 1))
                }
                if (item.node.left != null) {
                    queue.addLast(Data(requireNotNull(item.node.left), item.level + 1))
                }
            }
            return result
        }

        private data class Data(
            val node: IntTreeNode,
            val level: Int
        )
    }

    class DFSSolution() {
        fun rightSideView(root: IntTreeNode?): List<Int> {
            val list = mutableListOf<Int>()
            rightSideView(root, 0, list)
            return list
        }

        private fun rightSideView(root: IntTreeNode?, layer: Int, list: MutableList<Int>) {
            if (root == null) return
            println(layer.toString() + " node:" + root.toString() + " " + list.toString())
            if (list.getOrNull(layer) == null) {
                list.add(root.`val`)
            }
            rightSideView(root.right, layer + 1, list)
            rightSideView(root.left, layer + 1, list)
        }
    }

    class DFSSolution2() {
        fun rightSideView(root: IntTreeNode?): List<Int> {
            val maxesInLayer = mutableMapOf<Int, Int>()
            rightSideView(root, 0, maxesInLayer)
            println(maxesInLayer)
            return maxesInLayer.values.toList()
        }

        private fun rightSideView(root: IntTreeNode?, layer: Int, maxesInLayer: MutableMap<Int, Int>) {
            if (root == null) return
            println(layer.toString() + " node:" + root.toString() + " " + maxesInLayer.toString())
            if (maxesInLayer[layer] == null) {
                maxesInLayer[layer] = root.`val`
            }
            rightSideView(root.right, layer + 1, maxesInLayer)
            rightSideView(root.left, layer + 1, maxesInLayer)
        }
    }
}

private class Task199Test {
    private val task = Task199()

    @Test
    fun test1() {
        val tree = IntTreeNode(1)
        tree.apply { left = IntTreeNode(2).apply { right = IntTreeNode(5) } }
        tree.apply { right = IntTreeNode(3).apply { right = IntTreeNode(4) } }
        val expected = listOf(1, 3, 4)
        Assertions.assertEquals(expected, task.rightSideView(tree))
    }

    @Test
    fun test2() {
        val tree = IntTreeNode(1)
        tree.apply { left = IntTreeNode(2).apply { right = IntTreeNode(5) } }
        tree.apply { right = IntTreeNode(3).apply { right = IntTreeNode(4); left = IntTreeNode(3) } }
        val expected = listOf(1, 3, 4)
        Assertions.assertEquals(expected, task.rightSideView(tree))
    }

    @Test
    fun test3() {
        val tree = IntTreeNode(1)
        tree.apply { left = IntTreeNode(2).apply { right = IntTreeNode(5); left = IntTreeNode(3) } }
        val expected = listOf(1, 2, 5)
        Assertions.assertEquals(expected, task.rightSideView(tree))
    }

    @Test
    fun test4() {
        val tree = IntTreeNode(1)
        tree.apply { left = IntTreeNode(2).apply { right = IntTreeNode(5); left = IntTreeNode(4) } }
        tree.apply { right = IntTreeNode(3) }
        val expected = listOf(1, 3, 5)
        Assertions.assertEquals(expected, task.rightSideView(tree))
    }

    @Test
    fun test5() {
        val tree = IntTreeNode(1)
        tree.apply {
            right = IntTreeNode(2).apply {
                right = IntTreeNode(5).apply { right = IntTreeNode(6); left = IntTreeNode(4).apply { left = IntTreeNode(3) } }
            }
        }
        val expected = listOf(1, 2, 5, 6, 3)
        Assertions.assertEquals(expected, task.rightSideView(tree))
    }

    @Test
    fun test6() {
        val tree = IntTreeNode(5)
        tree.apply { right = IntTreeNode(6) }
        tree.apply { left = IntTreeNode(3).apply { right = IntTreeNode(4); left = IntTreeNode(1).apply { right = IntTreeNode(2); } } }

        val expected = listOf(5, 6, 4, 2)
        Assertions.assertEquals(expected, task.rightSideView(tree))
    }

    @Test
    fun test7() {
        val tree = IntTreeNode(1)
        tree.apply { right = IntTreeNode(3).apply { left = IntTreeNode(6) } }
        tree.apply { left = IntTreeNode(2).apply { right = IntTreeNode(5) } }

        val expected = listOf(1, 3, 6)
        Assertions.assertEquals(expected, task.rightSideView(tree))
    }

    @Test
    fun test8() {
        val tree = IntTreeNode(0)
        tree.apply {
            right = IntTreeNode(2).apply { left = IntTreeNode(4).apply { left = IntTreeNode(9).apply { left = IntTreeNode(10) } } }
        }
        tree.apply {
            left = IntTreeNode(1).apply { right = IntTreeNode(3).apply { right = IntTreeNode(5).apply { right = IntTreeNode(6) } } }
        }

        val expected = listOf(0, 2, 4, 9, 10)
        Assertions.assertEquals(expected, task.rightSideView(tree))
    }
}