package lc300_399
//From a learning section, repeat, similar to Task547
//https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/
import common.UnionFind

class Task323 {
    fun countComponents(n: Int, edges: Array<IntArray>): Int {
        val uf = UnionFind(n)
        for (edge in edges) {
            uf.union(edge[0], edge[1])
        }
        return uf.getAmountRoots()
    }
}

fun main() {
    val task = Task323()
    println(task.countComponents(5, arrayOf(intArrayOf(0, 1), intArrayOf(1, 2), intArrayOf(3, 4))))
    println(task.countComponents(5, arrayOf(intArrayOf(0, 1), intArrayOf(1, 2), intArrayOf(2, 3), intArrayOf(3, 4))))
}