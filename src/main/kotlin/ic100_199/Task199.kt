package ic100_199

//Solved, but repeat
//https://leetcode.com/problems/binary-tree-right-side-view/
class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null

    override fun toString(): String {
        return `val`.toString()
    }

}

class Task199 {

//    fun rightSideView(root: TreeNode?): List<Int> {
//        val maxesInLayer = mutableMapOf<Int, Int>()
//        rightSideView(root, 0, maxesInLayer)
//        println(maxesInLayer)
//        return maxesInLayer.values.toList()
//    }
//
//    private fun rightSideView(root: TreeNode?, layer: Int, maxesInLayer: MutableMap<Int, Int>) {
//        if (root == null) return
//        println(layer.toString()+" node:"+root.toString()+" "+maxesInLayer.toString())
//        if (maxesInLayer[layer] == null) {
//            maxesInLayer[layer] = root.`val`
//        }
//        rightSideView(root.right, layer + 1, maxesInLayer)
//        rightSideView(root.left, layer + 1, maxesInLayer)
//    }

    fun rightSideView(root: TreeNode?): List<Int> {
        val list = mutableListOf<Int>()
        rightSideView(root, 0, list)
        return list
    }

    private fun rightSideView(root: TreeNode?, layer: Int, list: MutableList<Int>) {
        if (root == null) return
        println(layer.toString() + " node:" + root.toString() + " " + list.toString())
        if (list.getOrNull(layer) == null) {
            list.add(root.`val`)
        }
        rightSideView(root.right, layer + 1, list)
        rightSideView(root.left, layer + 1, list)
    }

}

fun main() {
    val test1 = createTree8()
    println(Task199().rightSideView(test1))
}

private fun createTree1(): TreeNode {
    val root = TreeNode(1)
    root.apply { left = TreeNode(2).apply { right = TreeNode(5) } }
    root.apply { right = TreeNode(3).apply { right = TreeNode(4) } }
    return root
}

private fun createTree2(): TreeNode {
    val root = TreeNode(1)
    root.apply { left = TreeNode(2).apply { right = TreeNode(5) } }
    root.apply { right = TreeNode(3).apply { right = TreeNode(4); left = TreeNode(3) } }
    return root
}

private fun createTree3(): TreeNode {
    val root = TreeNode(1)
    root.apply { left = TreeNode(2).apply { right = TreeNode(5); left = TreeNode(3) } }
    return root
}

private fun createTree4(): TreeNode {
    val root = TreeNode(1)
    root.apply { left = TreeNode(2).apply { right = TreeNode(5); left = TreeNode(4) } }
    root.apply { right = TreeNode(3) }
    return root
}

private fun createTree5(): TreeNode {
    val root = TreeNode(1)
    root.apply {
        right = TreeNode(2).apply {
            right = TreeNode(5).apply { right = TreeNode(6); left = TreeNode(4).apply { left = TreeNode(3) } }
        }
    }
    return root
}

private fun createTree6(): TreeNode {
    val root = TreeNode(5)
    root.apply { right = TreeNode(6) }
    root.apply { left = TreeNode(3).apply { right = TreeNode(4); left = TreeNode(1).apply { right = TreeNode(2); } } }

    return root
}

private fun createTree7(): TreeNode {
    val root = TreeNode(1)
    root.apply { right = TreeNode(3).apply { left = TreeNode(6) } }
    root.apply { left = TreeNode(2).apply { right = TreeNode(5) } }

    return root
}

private fun createTree8(): TreeNode {
    val root = TreeNode(0)
    root.apply {
        right = TreeNode(2).apply { left = TreeNode(4).apply { left = TreeNode(9).apply { left = TreeNode(10) } } }
    }
    root.apply {
        left = TreeNode(1).apply { right = TreeNode(3).apply { right = TreeNode(5).apply { right = TreeNode(6) } } }
    }

    return root
}