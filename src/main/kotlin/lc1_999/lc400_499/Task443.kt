package lc1_999.lc400_499

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

//From Cracking The Coding Interview, Solved
//https://leetcode.com/problems/string-compression/

class Task443 {
    fun compress(chars: CharArray): Int {
        val compressedStr = chars.compress()
        chars.fillFromString(compressedStr)
        return compressedStr.length
    }

    private fun CharArray.compress(): String {
        val sb = StringBuilder()
        var amountRepeat = 1
        var previousCh = this[0]
        for (i in 1 until this.size) {
            if (previousCh == this[i]) {
                amountRepeat++
            } else {
                sb.appendPeace(previousCh, amountRepeat)
                amountRepeat = 1
                previousCh = this[i]
            }
        }
        if (amountRepeat != 0) {
            sb.appendPeace(previousCh, amountRepeat)
        }
        return sb.toString()
    }

    private fun StringBuilder.appendPeace(ch: Char, amountRepeat: Int) {
        append(ch)
        if (amountRepeat != 1) {
            append(amountRepeat)
        }
    }

    private fun CharArray.fillFromString(str: String) {
        for (i in str.indices) {
            this[i] = str[i]
        }
    }
}

private class Task443Test {
    private val task = Task443()

    @Test
    fun compress() {
        compress(charArrayOf('a', 'a', 'b', 'b', 'c', 'c', 'c'), 6)
        compress(charArrayOf('a'), 1)
        compress(charArrayOf('a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'), 4)
    }

    private fun compress(actualInp: CharArray, expected: Int) {
        val actual = task.compress(actualInp)
        println(actual)
        Assertions.assertEquals(expected, actual)
    }
}