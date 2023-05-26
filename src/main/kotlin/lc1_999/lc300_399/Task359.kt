package lc1_999.lc300_399

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

//Solved
//https://leetcode.com/problems/logger-rate-limiter/
class Task359 {
    private val loggerData = HashMap<String, Int>()
    fun shouldPrintMessage(timestamp: Int, message: String): Boolean {
        val result = loggerData[message] == null || timestamp >= loggerData[message]!! + 10
        if (result) loggerData[message] = timestamp
        return result
    }

    fun getLogger() = loggerData
}

private class Task359Test {
    private val task = Task359()

    @Test
    fun addData() {
        addData(1 to "foo", hashMapOf("foo" to 1), true)
        addData(2 to "bar", hashMapOf("foo" to 1, "bar" to 2), true)
        addData(2 to "bar", hashMapOf("foo" to 1, "bar" to 2), false)
        addData(3 to "foo", hashMapOf("foo" to 1, "bar" to 2), false)
        addData(8 to "bar", hashMapOf("foo" to 1, "bar" to 2), false)
        addData(10 to "foo", hashMapOf("foo" to 1, "bar" to 2), false)
        addData(11 to "foo", hashMapOf("foo" to 11, "bar" to 2), true)
    }

    private fun addData(inp: Pair<Int, String>, expectedData: HashMap<String, Int>, expectedResult: Boolean) {
        val actual = task.shouldPrintMessage(inp.first, inp.second)
        println(task.getLogger())
        Assertions.assertEquals(expectedResult, actual)

        Assertions.assertEquals(expectedData, task.getLogger())
    }
}