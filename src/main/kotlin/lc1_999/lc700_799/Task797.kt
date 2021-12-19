package lc1_999.lc700_799
//From a learning section, Solved, but repeat
//https://leetcode.com/problems/all-paths-from-source-to-target/
import java.util.ArrayDeque

class Task797 {
    fun allPathsSourceTarget(graph: Array<IntArray>): List<List<Int>> {
        val lastNode = graph.size - 1
        val paths = ArrayList<ArrayList<Int>>()
        val stack = ArrayDeque<ArrayList<Int>>()
        stack.push(ArrayList<Int>().apply { add(0) })
        while (stack.isNotEmpty()) {
            val lastPath = stack.pop()
            val lastVertex = lastPath.last()
            val relatedNodes = graph[lastVertex]
            for (relatedNode in relatedNodes) {
                val newList = ArrayList(lastPath).apply { add(relatedNode) }
                if (relatedNode == lastNode) {
                    paths.add(newList)
                } else {
                    stack.push(newList)
                }
            }
        }
        return paths
    }

    fun allPathsSourceTargetWithRecursive(graph: Array<IntArray>): List<List<Int>> {
        val paths = mutableListOf<List<Int>>()
        allPathsSourceTargetRecursive(graph, paths)
        return paths
    }

    private fun allPathsSourceTargetRecursive(
        graph: Array<IntArray>, paths: MutableList<List<Int>>, path: MutableList<Int> = mutableListOf(0),
        target: Int = graph.size - 1
    ) {
        val item = path.last()
        if (item == target) {
            paths.add(path)
        } else {
            for (relatedNode in graph[item]) {
                val newPath = path.toMutableList().apply { add(relatedNode) }
                allPathsSourceTargetRecursive(graph, paths, newPath, target)
            }
        }
    }
}

fun main() {
    val task = Task797()
    val graph1 = arrayOf(intArrayOf(1, 2), intArrayOf(3), intArrayOf(3), intArrayOf())
    val graph2 = arrayOf(intArrayOf(4, 3, 1), intArrayOf(3, 2, 4), intArrayOf(3), intArrayOf(4), intArrayOf())
    println(task.allPathsSourceTarget(graph1))
    println(task.allPathsSourceTarget(graph2))
}