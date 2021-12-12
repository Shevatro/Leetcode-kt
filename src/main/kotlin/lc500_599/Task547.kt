package lc500_599
//From leaning section, repeat
//https://leetcode.com/problems/number-of-provinces/
import common.UnionFind

class Task547 {
    fun findCircleNum(isConnected: Array<IntArray>): Int {
        val unionFind = fill(isConnected)
        return unionFind.countRoots()
    }

    private fun fill(isConnected: Array<IntArray>): UnionFind {
        val unionFind = UnionFind(isConnected.size)
        for (i in isConnected.indices) {
            for (j in isConnected[i].indices) {
                if (isConnected[i][j] == 1) {
                    unionFind.union(i, j)
                }
            }
        }
        return unionFind
    }
}


fun main() {
    println(Task547().findCircleNum(arrayOf(intArrayOf(1, 1, 0), intArrayOf(1, 1, 0), intArrayOf(0, 0, 1))))
    println(Task547().findCircleNum(arrayOf(intArrayOf(1, 0, 0), intArrayOf(0, 1, 0), intArrayOf(0, 0, 1))))
}