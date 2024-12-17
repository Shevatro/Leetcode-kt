package lc1_999.lc300_399

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.LinkedList
import java.util.PriorityQueue

//From a learning section, Not Solved
//https://leetcode.com/problems/reconstruct-itinerary/
class Task332 {
    fun findItinerary(tickets: List<List<String>>): List<String> {
        return Solution2(tickets).findItinerary()
    }

    private class Solution2(private val tickets: List<List<String>>) {
        private val graph = mutableMapOf<String, PriorityQueue<String>>()
        private val result = LinkedList<String>()

        fun findItinerary(): List<String> {
            fillGraph()
            dfs("JFK")
            return result
        }

        private fun fillGraph() {
            for (ticket in tickets) {
                val from = ticket[0]
                val to = ticket[1]
                if (graph[from] == null) {
                    graph[from] = PriorityQueue()
                }
                graph[from]?.add(to)
            }
        }

        private fun dfs(from: String?) {
            while (graph[from]?.isNotEmpty()==true) {
                dfs(graph[from]?.poll())
            }
            result.addFirst(from)
        }
    }
}


private class Task332Test {
    private val task = Task332()

    @Test
    fun findItineraryTest1() {
        val tickets = listOf(
            listOf("MUC", "LHR"), listOf("JFK", "MUC"), listOf("SFO", "SJC"), listOf("LHR", "SFO")
        )
        val result = task.findItinerary(tickets)
        val expectedResult = listOf("JFK", "MUC", "LHR", "SFO", "SJC")
        Assertions.assertEquals(expectedResult, result)
    }

    @Test
    fun findItineraryTest2() {
        val tickets = listOf(
            listOf("JFK", "SFO"), listOf("JFK", "ATL"), listOf("SFO", "ATL"), listOf("ATL", "JFK"),
            listOf("ATL", "SFO")
        )
        val result = task.findItinerary(tickets)
        val expectedResult = listOf("JFK", "ATL", "JFK", "SFO", "ATL", "SFO")
        Assertions.assertEquals(expectedResult, result)
    }

    @Test
    fun findItineraryTest3() {
        val tickets = listOf(
            listOf("EZE", "AXA"), listOf("TIA", "ANU"), listOf("ANU", "JFK"), listOf("JFK", "ANU"),
            listOf("ANU", "EZE"), listOf("TIA", "ANU"), listOf("AXA", "TIA"), listOf("TIA", "JFK"),
            listOf("ANU", "TIA"), listOf("JFK", "TIA")
        )
        val result = task.findItinerary(tickets)
        val expectedResult = listOf("JFK", "ANU", "EZE", "AXA", "TIA", "ANU", "JFK", "TIA", "ANU", "TIA", "JFK")
        Assertions.assertEquals(expectedResult, result)
    }

    @Test
    fun findItineraryTest4() {
        val tickets =  listOf(
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
        val result = task.findItinerary(tickets)
        val expectedResult = listOf(
            "JFK","ADL","ANU","ADL","ANU","AUA","ADL","AUA","ADL","AUA","ANU","AXA","ADL","AUA","ANU","AXA","ADL",
            "AXA","ADL","AXA","ANU","AXA","ANU","AXA","EZE","ADL","AXA","EZE","ADL","AXA","EZE","ADL","EZE","ADL",
            "EZE","ADL","EZE","ANU","EZE","ANU","EZE","AUA","AXA","EZE","AUA","AXA","EZE","AUA","AXA","JFK","ADL",
            "EZE","AUA","EZE","AXA","JFK","ADL","JFK","ADL","JFK","ADL","JFK","ADL","TIA","ADL","TIA","AUA","JFK",
            "ANU","TIA","AUA","JFK","AUA","JFK","AUA","TIA","AUA","TIA","AXA","TIA","EZE","AXA","TIA","EZE","JFK",
            "AXA","TIA","EZE","JFK","AXA","TIA","JFK","EZE","TIA","JFK","EZE","TIA","JFK","TIA","JFK","AUA","SYD"
        )
        Assertions.assertEquals(expectedResult, result)
    }
}