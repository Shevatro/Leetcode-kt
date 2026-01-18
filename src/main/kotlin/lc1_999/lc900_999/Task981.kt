package lc1_999.lc900_999

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

//Solved
//https://leetcode.com/problems/time-based-key-value-store/
class Task981 {
    class TimeMap {
        private val map = mutableMapOf<String, MutableList<Pair<Int, String>>>()
        fun set(key: String, value: String, timestamp: Int) {
            if (map[key] == null) map[key] = mutableListOf()
            map[key]?.add(timestamp to value)
        }

        fun get(key: String, timestamp: Int): String {
            if (map[key] == null) return ""
            val index = map[key]!!.binarySearch(timestamp to 0, compareBy { it.first })
            if (index >= 0) return map[key]!![index].second
            //we're interested in a previous item, so we need to take one more extra -1
            val predictedInd = -index - 2
            //if it's index= 0, it means it should be less than our smallest value
            if (predictedInd < 0) return ""
            if (map[key]!![predictedInd].first > timestamp) return "" //20 > 15
            return map[key]!![predictedInd].second
        }
    }
}

private class Task981Test {

    @Test
    fun test1() {
        val timeMap = Task981.TimeMap()
        timeMap.set("foo", "bar", 1) // store the key "foo" and value "bar" along with timestamp = 1.
        Assertions.assertEquals("bar", timeMap.get("foo", 1))
        // return "bar", since there is no value corresponding to foo at timestamp 3 and timestamp 2, then the only value is at timestamp 1 is "bar".
        Assertions.assertEquals("bar", timeMap.get("foo", 3))
        timeMap.set("foo", "bar2", 4) // store the key "foo" and value "bar2" along with timestamp = 4.
        Assertions.assertEquals("bar2", timeMap.get("foo", 4))
        Assertions.assertEquals("bar2", timeMap.get("foo", 5))
    }

    @Test
    fun test2() {
        val timeMap = Task981.TimeMap()
        timeMap.set("love", "high", 10)
        timeMap.set("love", "low", 20)
        Assertions.assertEquals("", timeMap.get("love", 5))
        Assertions.assertEquals("high", timeMap.get("love", 10))
        Assertions.assertEquals("high", timeMap.get("love", 15))
        Assertions.assertEquals("low", timeMap.get("love", 20))
        Assertions.assertEquals("low", timeMap.get("love", 25))
    }
}