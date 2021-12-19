package lc1900_1999
//From a learning section, Solved
//https://leetcode.com/problems/find-if-path-exists-in-graph/
import common.UnionFind
import toString2
import java.util.*

class Task1971 {

    fun validPath(n: Int, edges: Array<IntArray>, start: Int, end: Int): Boolean {
        if (edges.isEmpty() && start == end) return true
        val visitedVertexes = mutableMapOf<Int, Unit>()
        val graph = createGraph(n, edges)
        val stack = ArrayDeque<Int>()
        stack.push(start)
        while (stack.isNotEmpty()) {
            val node = stack.pop()
            if (visitedVertexes[node] != null) continue
            visitedVertexes[node] = Unit
            for (subNode in graph[node]) {
                if (subNode == end) return true
                stack.push(subNode)
            }
        }
        return false
    }

    private fun createGraph(n: Int, edges: Array<IntArray>): Array<MutableSet<Int>> {
        val nodes = Array(n) { mutableSetOf<Int>() }
        for (edge in edges) {
            nodes[edge[0]].add(edge[1])
            nodes[edge[1]].add(edge[0])
        }
        return nodes
    }

    fun validPathUsingUnionFind(n: Int, edges: Array<IntArray>, start: Int, end: Int): Boolean {
        val uf = createUnionFind(n, edges)
        return uf.isConnected(start, end)
    }

    private fun createUnionFind(n: Int, edges: Array<IntArray>): UnionFind {
        val uf = UnionFind(n)
        for (edge in edges) {
            uf.union(edge[0], edge[1])
        }
        return uf
    }

}

fun main() {
    val task = Task1971()
    println(task.validPath(3, arrayOf(intArrayOf(0, 1), intArrayOf(1, 2), intArrayOf(2, 0)), 0, 2))
    val edges2 = arrayOf(intArrayOf(0, 1), intArrayOf(0, 2), intArrayOf(3, 5), intArrayOf(5, 4), intArrayOf(4, 3))
    val edges3 = arrayOf(
        intArrayOf(4, 3), intArrayOf(1, 4), intArrayOf(4, 8), intArrayOf(1, 7), intArrayOf(6, 4), intArrayOf(4, 2),
        intArrayOf(7, 4), intArrayOf(4, 0), intArrayOf(0, 9), intArrayOf(5, 4)
    )
    println(task.validPath(6, edges2, 0, 5))
    println(task.validPath(1, emptyArray(), 0, 0))
    println(task.validPath(10, edges3, 5, 9))
}