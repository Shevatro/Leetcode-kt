package lc1900_1999
//From a learning section, Solved
//https://leetcode.com/problems/find-if-path-exists-in-graph/
import common.UnionFind

class Task1971 {
    fun validPath(n: Int, edges: Array<IntArray>, start: Int, end: Int): Boolean {
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
    println(task.validPath(6, edges2, 0, 5))
}