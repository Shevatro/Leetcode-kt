package common

import kotlin.math.max

class TreeNode<T>(var `val`: T) {
    var left: TreeNode<T>? = null
    var right: TreeNode<T>? = null
}

fun <T> TreeNode<T>.toLevelsList(): List<T?> {
    val list = mutableListOf<T?>()
    val queue = ArrayDeque<Pair<TreeNode<T>?, Int>>()
    queue.addLast(this to 0)
    var maxLevel = 0
    while (queue.isNotEmpty()) {
        val item = queue.removeFirst()
        val (node, level) = item
        if (level <= maxLevel) list.add(node?.`val`)
        if (node != null) {
            val newLevel = level + 1
            queue.addLast(node.left to newLevel)
            queue.addLast(node.right to newLevel)
            if (node.left != null || node.right != null) {
                maxLevel = max(newLevel, maxLevel)
            }
        }
    }
    return list
}