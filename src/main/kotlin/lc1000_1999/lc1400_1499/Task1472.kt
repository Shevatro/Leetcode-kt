package lc1000_1999.lc1400_1499

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.math.min

//From Beyond Cracking The Coding Interview, Solved
//https://leetcode.com/problems/design-browser-history/

class Task1472 {
    class BrowserHistory(homepage: String) {
        private val storage = Array<String?>(101) { null }
        private var maxAvailablePointer = 0
        private var pointer = -1

        init {
            visit(homepage)
        }

        fun visit(url: String) {
            pointer++
            maxAvailablePointer = pointer
            storage[pointer] = url
        }

        fun back(steps: Int): String {
            pointer -=  min(pointer, steps)
            return storage[pointer]!!
        }

        fun forward(steps: Int): String {
            pointer += min(maxAvailablePointer - pointer, steps)
            return storage[pointer]!!
        }
    }
}

private class Task1472Test {

    @Test
    fun test1() {

        val browser = Task1472.BrowserHistory("leetcode.com").apply {
            visit("google.com")
            visit("facebook.com")
            visit("youtube.com")
        }
        Assertions.assertEquals("facebook.com", browser.back(1))
        Assertions.assertEquals("google.com", browser.back(1))
        Assertions.assertEquals("facebook.com", browser.forward(1))
        browser.visit("linkedin.com")
        Assertions.assertEquals("linkedin.com", browser.forward(2))
        Assertions.assertEquals("google.com", browser.back(2))
        Assertions.assertEquals("leetcode.com", browser.back(7))
    }
}