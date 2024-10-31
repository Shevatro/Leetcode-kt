package lc1_999.lc500_599

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

//From Cracking The Coding Interview, Not Solved
//https://leetcode.com/problems/inorder-successor-in-bst-ii/
private class Task510 {
    fun inorderSuccessor(node: Node?): Node? {
        return if (node?.right != null) {
            getTheMostLeftInRightSubTree(requireNotNull(node.right))
        } else {
            getTheMostRightParent(node)
        }
    }

    private fun getTheMostLeftInRightSubTree(rightRoot: Node): Node {
        var curNode = rightRoot
        while (curNode.left != null) {
            curNode = requireNotNull(curNode.left)
        }
        return curNode
    }

    private fun getTheMostRightParent(node: Node?): Node? {
        var curNode = node
        while (curNode?.parent != null && curNode == curNode.parent?.right) {
            curNode = curNode.parent
        }
        return curNode?.parent
    }

}

private class Task510Test {
    private val task = Task510()

    @Test
    fun inorderSuccessorTest1() {
        val root = Node(2)
        val node1 = Node(1).apply {
            parent = root
        }
        val node3 = Node(3).apply {
            parent = root
        }
        root.apply {
            left = node1
            right = node3
        }
        val result = task.inorderSuccessor(node1)
        Assertions.assertEquals(2, result?.`val`)
    }

    @Test
    fun inorderSuccessorTest2() {
        val root = Node(5)
        val node3 = Node(3).apply {
            parent = root
        }
        val node2 = Node(2).apply {
            parent = node3
        }
        val node4 = Node(4).apply {
            parent = node3
        }
        node3.apply {
            left = node2
            right = node4
        }
        val node1 = Node(1).apply {
            parent = node2
        }
        node2.left = node1
        val node6 = Node(6).apply {
            parent = root
        }
        root.apply {
            left = node3
            right = node6
        }
        val result = task.inorderSuccessor(node6)
        Assertions.assertEquals(null, result?.`val`)
    }

    @Test
    fun inorderSuccessorTest3() {
        val root = Node(6)
        val node3 = Node(3).apply {
            parent = root
        }
        val node7 = Node(7).apply {
            parent = root
        }
        val node2 = Node(2).apply {
            parent = node3
        }
        val node4 = Node(4).apply {
            parent = node3
        }
        node3.apply {
            left = node2
            right = node4
        }
        val node1 = Node(1).apply {
            parent = node2
        }
        node2.left = node1
        val node5 = Node(5).apply {
            parent = node1
        }
        node1.right = node5
        root.apply {
            left = node3
            right = node7
        }
        val result = task.inorderSuccessor(node3)
        Assertions.assertEquals(4, result?.`val`)
    }

    @Test
    fun inorderSuccessorTest4() {
        val root = Node(15)
        val node6 = Node(6).apply {
            parent = root
        }
        val node18 = Node(18).apply {
            parent = root
        }
        root.apply {
            left = node6
            right = node18
        }
        val node3 = Node(3).apply {
            parent = node6
        }
        val node7 = Node(7).apply {
            parent = node6
        }
        node6.apply {
            left = node3
            right = node7
        }
        val node2 = Node(2).apply {
            parent = node3
        }
        val node4 = Node(4).apply {
            parent = node3
        }
        node3.apply {
            left = node2
            right = node4
        }
        val node13 = Node(13).apply {
            parent = node7
        }
        node7.right = node13
        val node9 = Node(9).apply {
            parent = node13
        }
        node13.left = node9

        val node17 = Node(17).apply {
            parent = node6
        }
        val node20 = Node(20).apply {
            parent = node6
        }
        node18.apply {
            left = node17
            right = node20
        }

        val result = task.inorderSuccessor(root)
        Assertions.assertEquals(17, result?.`val`)

        val result2 = task.inorderSuccessor(node13)
        Assertions.assertEquals(15, result2?.`val`)
    }

}

private class Node(var `val`: Int) {
    var left: Node? = null
    var right: Node? = null
    var parent: Node? = null
}