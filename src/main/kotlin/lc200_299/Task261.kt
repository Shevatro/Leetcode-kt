package lc200_299

import toString2

//From a learning section, Wrong!!!
//https://leetcode.com/problems/graph-valid-tree/
class Task261 {
    fun validTree(n: Int, edges: Array<IntArray>): Boolean {
        val arr = createAndFill(n)
        for (edge in edges) {
            val start = edge[0]
            val end = edge[1]
            if (arr[end] == end) {
                arr[end] = start
            } else if (arr[start] == start) {
                arr[start] = end
            } else {
                return false
            }
        }
        return true
    }

    private fun createAndFill(size: Int): IntArray {
        val arr = IntArray(size)
        for (i in arr.indices) {
            arr[i] = i
        }
        return arr
    }
}

fun main() {
    val obj = Task261()
    val edges = arrayOf(intArrayOf(0, 1), intArrayOf(0, 2), intArrayOf(0, 3), intArrayOf(1, 4))
    println(obj.validTree(5, edges))
    val edges2 = arrayOf(intArrayOf(0, 1), intArrayOf(1, 2), intArrayOf(2, 3), intArrayOf(1, 3), intArrayOf(1, 4))
    println(obj.validTree(5, edges2))
    val edges3 = arrayOf(intArrayOf(0, 1), intArrayOf(2, 3))
    println(obj.validTree(4, edges3))
    val edges4 = arrayOf(intArrayOf(1, 0), intArrayOf(2, 0))
    println(obj.validTree(3, edges4))
    println(obj.validTree(1, emptyArray()))
}