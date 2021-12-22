package lc1_999.lc300_399

import java.util.ArrayDeque

//From a learning section, wrong
//https://leetcode.com/problems/reconstruct-itinerary/
class Task332 {
    fun findItinerary(tickets: List<List<String>>): List<String> {
        val vertexLimit = tickets.size + 1
        println("limit:" + vertexLimit)
        val graph = createGraph(tickets)
        println(graph)
        val paths = mutableListOf<List<String>>()
        val stack = ArrayDeque<Node>()
        stack.push(Node(listOf("JFK"), createDeepCopy(graph)))
        while (stack.isNotEmpty()) {
            val node = stack.pop()
            println("node:" + node)
            val path = node.path
            val lastVertex = path.last()
            val subNodes = node.graph[lastVertex]
            if (subNodes.isNullOrEmpty()) {
                if (path.size == vertexLimit) paths.add(path)
            } else {
                for (subNode in subNodes.toSet()) {
                    val newPath = ArrayList(path).apply { add(subNode) }
                    val newGraph = createDeepCopy(node.graph)
                    newGraph[lastVertex]?.remove(subNode)
                    stack.push(Node(newPath, newGraph))
                }
            }
        }
        println("PATHS:" + paths)
        return findMinPath(paths)
    }

    private fun createDeepCopy(map: MutableMap<String, MutableList<String>>): MutableMap<String, MutableList<String>> {
        val newMap = mutableMapOf<String, MutableList<String>>()
        for (entry in map) {
            newMap[entry.key] = entry.value.map { it }.toMutableList()
        }
        return newMap
    }

    private fun findMinPath(paths: List<List<String>>): List<String> {
        var min = paths[0].joinToString("")
        var iMin = 0
        for (i in 1 until (paths.size)) {
            val item = paths[i].joinToString("")
            if (item < min) {
                min = item
                iMin = i
            }
        }
        return paths[iMin]
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

data class Node(
    val path: List<String>,
    val graph: MutableMap<String, MutableList<String>>
)

fun main() {
    val task = Task332()
    println(
        task.findItinerary(
            listOf(
                listOf("MUC", "LHR"), listOf("JFK", "MUC"), listOf("SFO", "SJC"), listOf("LHR", "SFO")
            )
        )
    )
    println(
        task.findItinerary(
            listOf(
                listOf("JFK", "SFO"), listOf("JFK", "ATL"), listOf("SFO", "ATL"), listOf("ATL", "JFK"),
                listOf("ATL", "SFO")
            )
        )
    )
    println(
        task.findItinerary(
            listOf(
                listOf("EZE", "AXA"), listOf("TIA", "ANU"), listOf("ANU", "JFK"), listOf("JFK", "ANU"),
                listOf("ANU", "EZE"), listOf("TIA", "ANU"), listOf("AXA", "TIA"), listOf("TIA", "JFK"),
                listOf("ANU", "TIA"), listOf("JFK", "TIA")
            )
        )
    )
    println(
        task.findItinerary(
            listOf(
                listOf("AXA","EZE"), listOf("EZE","AUA"), listOf("ADL","JFK"), listOf("ADL","TIA"), listOf("AUA","AXA"),
                listOf("EZE","TIA"), listOf("EZE","TIA"), listOf("AXA","EZE"), listOf("EZE","ADL"), listOf("ANU","EZE"),
                listOf("TIA","EZE"), listOf("JFK","ADL"), listOf("AUA","JFK"), listOf("JFK","EZE"), listOf("EZE","ANU"),
                listOf("ADL","AUA"), listOf("ANU","AXA"), listOf("AXA","ADL"), listOf("AUA","JFK"), listOf("EZE","ADL"),
                listOf("ANU","TIA"), listOf("AUA","JFK"), listOf("TIA","JFK"), listOf("EZE","AUA"), listOf("AXA","EZE"),
                listOf("AUA","ANU"), listOf("ADL","AXA"), listOf("EZE","ADL"), listOf("AUA","ANU"), listOf("AXA","EZE"),
                listOf("TIA","AUA"), listOf("AXA","EZE"), listOf("AUA","SYD"), listOf("ADL","JFK"), listOf("EZE","AUA"),
                listOf("ADL","ANU"), listOf("AUA","TIA"), listOf("ADL","EZE"), listOf("TIA","JFK"), listOf("AXA","ANU"),
                listOf("JFK","AXA"), listOf("JFK","ADL"), listOf("ADL","EZE"), listOf("AXA","TIA"), listOf("JFK","AUA"),
                listOf("ADL","EZE"), listOf("JFK","ADL"), listOf("ADL","AXA"), listOf("TIA","AUA"), listOf("AXA","JFK"),
                listOf("ADL","AUA"), listOf("TIA","JFK"), listOf("JFK","ADL"), listOf("JFK","ADL"), listOf("ANU","AXA"),
                listOf("TIA","AXA"), listOf("EZE","JFK"), listOf("EZE","AXA"), listOf("ADL","TIA"), listOf("JFK","AUA"),
                listOf("TIA","EZE"), listOf("EZE","ADL"), listOf("JFK","ANU"), listOf("TIA","AUA"), listOf("EZE","ADL"),
                listOf("ADL","JFK"), listOf("ANU","AXA"), listOf("AUA","AXA"), listOf("ANU","EZE"), listOf("ADL","AXA"),
                listOf("ANU","AXA"), listOf("TIA","ADL"), listOf("JFK","ADL"), listOf("JFK","TIA"), listOf("AUA","ADL"),
                listOf("AUA","TIA"), listOf("TIA","JFK"), listOf("EZE","JFK"), listOf("AUA","ADL"), listOf("ADL","AUA"),
                listOf("EZE","ANU"), listOf("ADL","ANU"), listOf("AUA","AXA"), listOf("AXA","TIA"), listOf("AXA","TIA"),
                listOf("ADL","AXA"), listOf("EZE","AXA"), listOf("AXA","JFK"), listOf("JFK","AUA"), listOf("ANU","ADL"),
                listOf("AXA","TIA"), listOf("ANU","AUA"), listOf("JFK","EZE"), listOf("AXA","ADL"), listOf("TIA","EZE"),
                listOf("JFK","AXA"), listOf("AXA","ADL"), listOf("EZE","AUA"), listOf("AXA","ANU"), listOf("ADL","EZE"),
                listOf("AUA","EZE")
            )
        )
    )
}