package lc1_999.lc1_99

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

//From Cracking The Coding Interview, Solved
//https://leetcode.com/problems/group-anagrams/
class Task49 {

    fun groupAnagrams(strs: Array<String>): List<List<String>> {
        return Solution2(strs).groupAnagrams()
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
}