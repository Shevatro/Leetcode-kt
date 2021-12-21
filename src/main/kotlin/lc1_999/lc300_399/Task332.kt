package lc1_999.lc300_399

import java.util.ArrayDeque

//From a learning section, wrong
//https://leetcode.com/problems/reconstruct-itinerary/
class Task332 {
    fun findItinerary(tickets: List<List<String>>): List<String> {
        val graph = createGraph(tickets)
        println(graph)
        val paths = mutableListOf<String>()
        val stack = ArrayDeque<String>()
        stack.push("JFK")
        while (stack.isNotEmpty()) {
            val path = stack.pop()
            println("curPath:" + path)
            val node = path.takeLast(3)
            println("node:" + node)
            val subNodes = graph[node]
            if (subNodes.isNullOrEmpty()) {
                paths.add(path)
            } else {
                var hasPath = false
                for (subNode in subNodes) {
                    println(node + subNode + " isContaints:" + path.contains(node + subNode))
                    if (!path.contains(node + subNode)) {
                        stack.push(path + subNode)
                        hasPath = true
                    }
                }
                if (!hasPath) {
                    println("added:" + path)
                    paths.add(path)
                }
            }
        }
        return findMinPath(paths, tickets.size)
    }

    private fun findMinPath(paths: List<String>, amountTickets: Int): List<String> {
        val filteredPaths = paths.filter { it.length == (amountTickets + 1) * 3 }
        val item = filteredPaths.sorted()[0]
        val chs = item.toCharArray()
        val items = mutableListOf<String>()
        for (i in chs.indices step 3) {
            items.add(chs.copyOfRange(i, i + 3).joinToString(""))
        }
        return items
    }

    private fun createGraph(tickets: List<List<String>>): MutableMap<String, MutableList<String>> {
        val map = mutableMapOf<String, MutableList<String>>()
        for (ticket in tickets) {
            val from = ticket[0]
            val to = ticket[1]
            if (map[from] == null) {
                map[from] = mutableListOf()
            }
            map[from]?.add(to)
        }
        return map
    }
}

fun main() {
    val task = Task332()
    println(
        task.findItinerary(
            listOf(
                listOf("MUC", "LHR"),
                listOf("JFK", "MUC"),
                listOf("SFO", "SJC"),
                listOf("LHR", "SFO")
            )
        )
    )
    println(
        task.findItinerary(
            listOf(
                listOf("JFK", "SFO"),
                listOf("JFK", "ATL"),
                listOf("SFO", "ATL"),
                listOf("ATL", "JFK"),
                listOf("ATL", "SFO")
            )
        )
    )
}