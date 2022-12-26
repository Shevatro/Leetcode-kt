package lc1_999.lc100_199

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

//Not solved, repeat
//https://leetcode.com/problems/largest-number/
class Task179 {
    fun largestNumber(nums: IntArray): String {
        val customComparator = Comparator { s1: String, s2: String -> (s2 + s1).compareTo(s1 + s2) }
        val numsStr = nums.map { it.toString() }.sortedWith(customComparator)
        val sb = StringBuilder()
        for (num in numsStr) {
            sb.append(num)
        }
        val result = sb.toString()
        if (result.isNotEmpty() && result[0] == '0') {
            return "0"
        }
        return result
    }
}

private class Task179Test {
    private val task = Task179()

    @Test
    fun largestNumber() {
        largestNumber(intArrayOf(10, 2), "210")
        largestNumber(intArrayOf(3, 30, 34, 5, 9), "9534330")
        largestNumber(intArrayOf(30, 3, 34, 5, 9), "9534330")
        largestNumber(intArrayOf(100, 3, 30, 34, 5, 9), "9534330100")
        largestNumber(intArrayOf(100, 3, 30, 34, 5, 9), "9534330100")
        largestNumber(intArrayOf(111311, 1113), "1113111311")
        largestNumber(intArrayOf(0, 0), "0")
    }

    private fun largestNumber(actualInp: IntArray, expected: String) {
        val actual = task.largestNumber(actualInp)
        println(actual)
        Assertions.assertEquals(expected, actual)
    }
}