package lc1_999.lc700_799

import common.IntArrayReader
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

//From Cracking The Coding Interview, Solved
//https://leetcode.com/problems/search-in-a-sorted-array-of-unknown-size/description/
class Task702 {

    fun search(reader: IntArrayReader, target: Int): Int {
        var start = 0
        var end = 9999
        while (start <= end) {
            val mid = (start + end) / 2
            val item = reader.get(mid)
            if (item == target) return mid
            if (item > target) {
                end = mid - 1
            } else {
                start = mid + 1
            }
        }
        return -1
    }
}

private class Task702Test {
    private val task = Task702()

    @Test
    fun test1() {
        val input = IntArrayReader(intArrayOf(-1, 0, 3, 5, 9, 12))
        val actualResult = task.search(input, 9)
        Assertions.assertEquals(4, actualResult)
    }

    @Test
    fun test2() {
        val input = IntArrayReader(intArrayOf(-1, 0, 3, 5, 9, 12))
        val actualResult = task.search(input, 2)
        Assertions.assertEquals(-1, actualResult)
    }
}