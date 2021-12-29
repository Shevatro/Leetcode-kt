package common

import java.util.ArrayDeque

class SimpleUndirectedGraph(n: Int) {
    private val graph = Array(n) { mutableSetOf<Int>() }
    private val visitedVertexes = BooleanArray(graph.size)

    fun createGraph(edges: Array<IntArray>) {
        for (edge in edges) {
            graph[edge[0]].add(edge[1])
            graph[edge[1]].add(edge[0])
        }
    }

    fun isConnectedDFS(item1: Int, item2: Int): Boolean {
        if (item1 == item2) return true
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

    fun isConnectedBFS(item1: Int, item2: Int): Boolean {
        if (item1 == item2) return true
        val queue = ArrayDeque<Int>()
        queue.add(item1)
        visitedVertexes[item1] = true
        while (queue.isNotEmpty()) {
            val node = queue.poll()
            for (subNode in graph[node]) {
                if (visitedVertexes[subNode]) continue
                visitedVertexes[subNode] = true
                if (subNode == item2) return true
                queue.push(subNode)
            }
        }
        return false
    }

    fun isConnectedDFSRecursive(item1: Int, item2: Int): Boolean {
        if (item1 == item2) return true
        if (visitedVertexes[item1]) return false
        visitedVertexes[item1] = true
        val relatedItems = graph[item1]
        for (relatedItem in relatedItems) {
            if (isConnectedDFSRecursive(relatedItem, item2)) return true
        }
        return false
    }
}