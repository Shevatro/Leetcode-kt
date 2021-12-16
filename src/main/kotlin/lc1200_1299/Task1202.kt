package lc1200_1299
//From a learning section, wrong
//https://leetcode.com/problems/smallest-string-with-swaps/
import toString2
import java.util.*

class Task1202 {
    fun smallestStringWithSwaps(s: String, pairs: List<List<Int>>): String {
        val sf = StringBuilder()
        val uf = UnionFind(26)
        for (pair in pairs) {
            val item1 = s[pair[0]] - 'a'
            val item2 = s[pair[1]] - 'a'
            println(item1.toString() + " " + item2)
            uf.union(item1, item2)
        }
        println(uf.print())
        val graphs = uf.getAllGraphs()
        println(graphs.toString())
        val indices = createIndices(graphs).toMutableMap()
        println(indices)
        for (ch in s.toCharArray()) {
            val graphId = uf.find(ch - 'a')
            println(ch + " " + graphId)
            val index = indices[graphId]!!
            println("index:$index")
            val item = graphs[graphId]?.elementAt(index)
            println(item)
            sf.append('a' + item!!)
            indices[graphId] = indices[graphId]!! + 1
        }
        return sf.toString()
    }

    private fun createIndices(graphs: Map<Int, SortedSet<Int>>): Map<Int, Int> {
        val map = mutableMapOf<Int, Int>()
        for (entry in graphs.entries) {
            map[entry.key] = 0
        }
        return map
    }
}

class UnionFind(size: Int) {
    private val arr = IntArray(size)
    private val rankArr = IntArray(size)
    private var amountGraphs = size

    init {
        for (i in arr.indices) {
            arr[i] = i
            rankArr[i] = 1
        }
    }

    fun find(item: Int): Int {
        if (item == arr[item]) return item
        arr[item] = find(arr[item])
        return arr[item]
    }

    fun union(item1: Int, item2: Int) {
        val rootItem1 = find(item1)
        val rootItem2 = find(item2)
        if (rootItem1 != rootItem2) {
            if (rankArr[rootItem1] >= rankArr[rootItem2]) {
                arr[rootItem2] = rootItem1
                if (rankArr[rootItem1] == rankArr[rootItem2]) rankArr[rootItem1]++
            } else {
                arr[rootItem1] = rootItem2
            }
            amountGraphs--
        }
    }

    fun isConnected(item1: Int, item2: Int): Boolean {
        return find(item1) == find(item2)
    }

    fun getAllGraphs(): Map<Int, SortedSet<Int>> {
        val map = mutableMapOf<Int, SortedSet<Int>>()
        for (i in arr.indices) {
            val rootChar = find(i)
            val char = i
            if (map[rootChar] == null) {
                map[rootChar] = sortedSetOf()
                map[rootChar]?.add(rootChar)
            }
            if (rootChar != char) {
                map[rootChar]?.add(char)
            }
        }
        return map
    }

    fun print() {
        println(arr.toString2())
    }
}

fun main() {
    val task = Task1202()
    println(task.smallestStringWithSwaps("dcab", listOf(listOf(0, 3), listOf(1, 2))))
    println(task.smallestStringWithSwaps("dcab", listOf(listOf(0, 3), listOf(1, 2), listOf(0, 2))))
    println(task.smallestStringWithSwaps("cba", listOf(listOf(0, 1), listOf(1, 2))))
    println(task.smallestStringWithSwaps("a", listOf()))
    println(task.smallestStringWithSwaps("qdwyt", listOf(listOf(2,3), listOf(3,2), listOf(0,1), listOf(4,0),
        listOf(3,2))))
    println(task.smallestStringWithSwaps("qdwyt", listOf(listOf(2,3), listOf(3,2), listOf(0,1), listOf(4,0),
        listOf(3,2))))
    println(task.smallestStringWithSwaps("udyyek", listOf(listOf(3,3), listOf(3,0), listOf(5,1), listOf(3,1),
        listOf(3,4), listOf(3,5)
    )))
}