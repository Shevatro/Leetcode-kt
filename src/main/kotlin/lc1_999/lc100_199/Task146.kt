package lc1_999.lc100_199

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

//From Cracking The Coding Interview, Solved
//https://leetcode.com/problems/lru-cache/description/
class Task146 {
    class LRUCache(private val capacity: Int) {
        private var head = Node(-1, -1)
        private var tail = head
        private val map = HashMap<Int, Node>()
        fun get(key: Int): Int {
            val node = map[key] ?: return -1
            if (node != tail) {
                removeNode(node)
                addNode(key, node.value)
            }
            return node.value
        }

        fun put(key: Int, value: Int) {
            if (map[key] != null) {
                removeNode(map[key]!!)
            } else if (map.size == capacity) {
                val node = head.next!!
                val nodeKey = node.key
                removeNode(head.next!!)
                map.remove(nodeKey)
            }
            addNode(key, value)
        }

        private fun removeNode(node: Node) {
            //don't want to lose a tail
            if (node == tail) tail = tail.prev!!
            val prev = node.prev
            val next = node.next
            prev?.next = next
            next?.prev = prev
        }

        private fun addNode(key: Int, value: Int) {
            val newNode = Node(key, value, tail)
            tail.next = newNode
            tail = newNode
            map[key] = tail
        }

        private class Node(
            val key: Int,
            val value: Int,
            var prev: Node? = null,
            var next: Node? = null
        )
    }

    class LRUCacheFirstSolution(private val capacity: Int) {
        private val map = mutableMapOf<Int, Int>()
        fun get(key: Int): Int {
            val temp = map[key] ?: return -1
            map.remove(key)
            map[key] = temp
            return temp
        }

        fun put(key: Int, value: Int) {
            if (map[key] != null) {
                map.remove(key)
            } else if (map.size == capacity) {
                val first = map.keys.first()
                map.remove(first)
            }
            map[key] = value
        }

    }

    class LRUCacheSecondSolution(capacity: Int) {
        private val map = object : LinkedHashMap<Int, Int>(capacity, 0.75f, true) {
            override fun removeEldestEntry(eldest: Map.Entry<Int, Int>): Boolean {
                return size > capacity
            }
        }

        fun get(key: Int): Int {
            return map[key] ?: -1
        }

        fun put(key: Int, value: Int) {
            map[key] = value
        }
    }
}

private class Task146Test {

    @Test
    fun test1() {
        val lruCache = Task146.LRUCache(2)
        lruCache.apply {
            put(1, 1)
            put(2, 2)
        }
        Assertions.assertEquals(1, lruCache.get(1))
        lruCache.put(3, 3)
        Assertions.assertEquals(-1, lruCache.get(2))
        lruCache.put(4, 4)
        Assertions.assertEquals(-1, lruCache.get(1))
        Assertions.assertEquals(3, lruCache.get(3))
        Assertions.assertEquals(4, lruCache.get(4))
    }

    @Test
    fun test2() {
        val lruCache = Task146.LRUCache(2)
        Assertions.assertEquals(-1, lruCache.get(2))
        lruCache.put(2, 6)
        Assertions.assertEquals(-1, lruCache.get(1))
        lruCache.apply {
            put(1, 5)
            put(1, 2)
        }
        Assertions.assertEquals(2, lruCache.get(1))
        Assertions.assertEquals(6, lruCache.get(2))
    }

    @Test
    fun test3() {
        val lruCache = Task146.LRUCache(2)
        lruCache.apply {
            put(2, 1)
            put(1, 1)
            put(2, 3)
            put(4, 1)
        }
        Assertions.assertEquals(-1, lruCache.get(1))
        Assertions.assertEquals(3, lruCache.get(2))
    }

    @Test
    fun test4() {
        val lruCache = Task146.LRUCache(1)
        lruCache.put(2, 1)
        Assertions.assertEquals(1, lruCache.get(2))
        lruCache.put(3, 2)
        Assertions.assertEquals(-1, lruCache.get(2))
        Assertions.assertEquals(2, lruCache.get(3))
    }

}