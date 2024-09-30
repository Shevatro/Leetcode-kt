package common

import kotlin.math.max

class IntTreeNode(var `val`: Int) {
    var left: IntTreeNode? = null
    var right: IntTreeNode? = null
}

fun IntTreeNode.toLevelsList(): List<Int?> {
    val list = mutableListOf<Int?>()
    val queue = ArrayDeque<Pair<IntTreeNode?, Int>>()
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