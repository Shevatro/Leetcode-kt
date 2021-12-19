package common

import java.util.ArrayDeque

class SimpleGraph(n: Int) {
    private val graph = Array(n) { mutableSetOf<Int>() }
    private val visitedVertexes = BooleanArray(graph.size)

    fun createGraph(edges: Array<IntArray>) {
        for (edge in edges) {
            graph[edge[0]].add(edge[1])
            graph[edge[1]].add(edge[0])
        }
    }

    fun isConnected(item1: Int, item2: Int): Boolean {
        val stack = ArrayDeque<Int>()
        stack.push(item1)
        while (stack.isNotEmpty()) {
            val node = stack.pop()
            if (visitedVertexes[node]) continue
            visitedVertexes[node] = true
            for (subNode in graph[node]) {
                if (subNode == item2) return true
                stack.push(subNode)
            }
        }
        return false
    }

    fun isConnectedRecursive(item1: Int, item2: Int): Boolean {
        if (item1 == item2) return true
        if (visitedVertexes[item1]) return false
        visitedVertexes[item1] = true
        val relatedItems = graph[item1]
        for (relatedItem in relatedItems) {
            if (isConnectedRecursive(relatedItem, item2)) return true
        }
        return false
    }
}