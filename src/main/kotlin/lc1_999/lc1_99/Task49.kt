package lc1_999.lc1_99

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.TreeMap

//From Cracking The Coding Interview, Solved
//https://leetcode.com/problems/group-anagrams/
class Task49 {

    fun groupAnagrams(strs: Array<String>): List<List<String>> {
        return Solution4(strs).groupAnagrams()
    }

    private class Solution() {
        fun groupAnagrams(strs: Array<String>): List<List<String>> {
            val result = mutableListOf<MutableList<String>>()
            val map = mutableMapOf<String, Int>()
            for (str in strs) {
                val sortedStr = str.toCharArray().sorted().toString()
                if (map[sortedStr] == null) {
                    result.add(mutableListOf())
                    map[sortedStr] = result.lastIndex
                }
                val index = map[sortedStr]!!
                result[index].add(str)
            }
            return result
        }
    }

    private class Solution2(private val strs: Array<String>) {
        private val map = mutableMapOf<String, MutableList<String>>()
        fun groupAnagrams(): List<List<String>> {
            fillInMap()
            return ArrayList(map.values)
        }

        private fun fillInMap() {
            for (str in strs) {
                val sortedStr = str.toCharArray().sorted().toString()
                if (map[sortedStr] == null) {
                    map[sortedStr] = mutableListOf()
                }
                map[sortedStr]?.add(str)
            }
        }
    }

    private class Solution3(private val strs: Array<String>) {
        private val map = mutableMapOf<String, MutableList<String>>()

        fun groupAnagrams(): List<List<String>> {
            fillInMap()
            return ArrayList(map.values)
        }

        private fun fillInMap() {
            for (str in strs) {
                val sortedStr = generateHash(str)
                if (map[sortedStr] == null) {
                    map[sortedStr] = mutableListOf()
                }
                map[sortedStr]?.add(str)
            }
        }

        private fun generateHash(str: String): String {
            val frequency = fillInFrequency(str)
            val sb = StringBuilder()
            frequency.forEach { (key, value) -> sb.append(key).append(value) }
            return sb.toString()
        }

        private fun fillInFrequency(str: String): Map<Char, Int> {
            val frequency = TreeMap<Char, Int>()
            for (ch in str) {
                frequency[ch] = (frequency[ch] ?: 0) + 1
            }
            return frequency
        }
    }

    private class Solution4(private val strs: Array<String>) {
        private val map = mutableMapOf<String, MutableList<String>>()

        fun groupAnagrams(): List<List<String>> {
            fillInMap()
            return ArrayList(map.values)
        }

        private fun fillInMap() {
            for (str in strs) {
                val sortedStr = generateHash(str)
                if (map[sortedStr] == null) {
                    map[sortedStr] = mutableListOf()
                }
                map[sortedStr]?.add(str)
            }
        }

        private fun generateHash(str: String): String {
            val frequency = fillInFrequency(str)
            val sb = StringBuilder()
            frequency.forEach { sb.append("#").append(it) }
            return sb.toString()
        }

        private fun fillInFrequency(str: String): IntArray {
            val frequency = IntArray(26)
            for (ch in str) {
                val code = ch - 'a'
                frequency[code] = frequency[code] + 1
            }
            return frequency
        }
    }
}

private class Task49Test {
    private val task = Task49()

    @Test
    fun test1() {
        val input = arrayOf("eat", "tea", "tan", "ate", "nat", "bat")
        val actualResult = task.groupAnagrams(input)
        val expectedResult = listOf(
            listOf("bat"), listOf("nat", "tan"), listOf("ate", "eat", "tea")
        )
        Assertions.assertEquals(expectedResult.size, actualResult.size)
        val expectedSorted = expectedResult.map { it.sorted() }.sortedBy { it[0] }
        val actualSorted = actualResult.map { it.sorted() }.sortedBy { it[0] }
        Assertions.assertEquals(expectedSorted, actualSorted)
    }

    @Test
    fun test2() {
        val input = arrayOf("")
        val actualResult = task.groupAnagrams(input)
        val expectedResult = listOf(listOf(""))
        Assertions.assertEquals(expectedResult.size, actualResult.size)
        Assertions.assertEquals(expectedResult, actualResult)
    }

    @Test
    fun test3() {
        val input = arrayOf("a")
        val actualResult = task.groupAnagrams(input)
        val expectedResult = listOf(listOf("a"))
        Assertions.assertEquals(expectedResult.size, actualResult.size)
        Assertions.assertEquals(expectedResult, actualResult)
    }

    @Test
    fun test4() {
        val input = arrayOf("eat", "tea", "tan", "eta", "nat", "bat")
        val actualResult = task.groupAnagrams(input)
        val expectedResult = listOf(
            listOf("bat"), listOf("nat", "tan"), listOf("eta", "eat", "tea")
        )
        Assertions.assertEquals(expectedResult.size, actualResult.size)
        val expectedSorted = expectedResult.map { it.sorted() }.sortedBy { it[0] }
        val actualSorted = actualResult.map { it.sorted() }.sortedBy { it[0] }
        Assertions.assertEquals(expectedSorted, actualSorted)
    }

    @Test
    fun test5() {
        val input = arrayOf("bdddddddddd", "bbbbbbbbbbc")
        val actualResult = task.groupAnagrams(input)
        val expectedResult = listOf(
            listOf("bbbbbbbbbbc"), listOf("bdddddddddd")
        )
        Assertions.assertEquals(expectedResult.size, actualResult.size)
        val expectedSorted = expectedResult.map { it.sorted() }.sortedBy { it[0] }
        val actualSorted = actualResult.map { it.sorted() }.sortedBy { it[0] }
        Assertions.assertEquals(expectedSorted, actualSorted)
    }
}