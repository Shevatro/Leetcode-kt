package lc1000_1999.lc1100_1199
//From a learning section, repeat
//https://leetcode.com/problems/the-earliest-moment-when-everyone-become-friends/
import common.UnionFind

class Task1101 {
    fun earliestAcq(logs: Array<IntArray>, n: Int): Int {
        logs.sortBy { it[0] } //Can we optimize here? And don't use a sorting
        val uf = UnionFind(n)
        for (logItem in logs) {
            uf.union(logItem[1], logItem[2])
            if (uf.getAmountRoots() == 1) return logItem[0]
        }
        return -1
    }
}

fun main() {
    val task = Task1101()
    val logs = arrayOf(
        intArrayOf(20190101, 0, 1), intArrayOf(20190104, 3, 4), intArrayOf(20190107, 2, 3), intArrayOf(20190211, 1, 5),
        intArrayOf(20190224, 2, 4), intArrayOf(20190301, 0, 3), intArrayOf(20190312, 1, 2), intArrayOf(20190322, 4, 5)
    )
    println(task.earliestAcq(logs, 6))
    val logs2 = arrayOf(
        intArrayOf(0, 2, 0), intArrayOf(1, 0, 1), intArrayOf(3, 0, 3), intArrayOf(4, 1, 2),
        intArrayOf(7, 3, 1)
    )
    println(task.earliestAcq(logs2, 4))
    val logs3 = arrayOf(
        intArrayOf(9, 3, 0), intArrayOf(0, 2, 1), intArrayOf(8, 0, 1), intArrayOf(1, 3, 2), intArrayOf(2, 2, 0),
        intArrayOf(3, 3, 1)
    )
    println(task.earliestAcq(logs3, 4)) //?
}