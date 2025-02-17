package lc1_999.lc100_199

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

// Solved
//https://leetcode.com/problems/lru-cache/description/
class Task146 {
    class LRUCache(private val capacity: Int) {
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

}