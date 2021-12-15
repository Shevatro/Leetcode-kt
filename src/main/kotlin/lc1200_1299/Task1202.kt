package lc1200_1299

import toString2
import java.util.*

class Task1202 {
    fun smallestStringWithSwaps(s: String, pairs: List<List<Int>>): String {
        val sf = StringBuilder()
        val uf = UnionFind(s.length)
        for (pair in pairs) {
            uf.union(pair[0], pair[1])
        }
        println(uf.print())
        val graphs = uf.getAllGraphs()
        println(graphs.toString())
        val indices = IntArray(graphs.size)
        for (ch in s.toCharArray()) {
            val graphId = uf.find(ch - 'a')
            println(ch + " " + graphId)
            val index = indices[graphId]
            val item = graphs[graphId]?.elementAt(index)
            println(item)
            sf.append('a' + item!!)
            indices[graphId]++
        }
        return sf.toString()
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
            val rootChar = arr[i]
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
}