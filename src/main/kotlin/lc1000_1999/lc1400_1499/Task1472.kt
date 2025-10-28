package lc1000_1999.lc1400_1499

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.math.min

//From Beyond Cracking The Coding Interview, Solved
//https://leetcode.com/problems/design-browser-history/

class Task1472 {
    class BrowserHistory(homepage: String) {
        private val mainStack = ArrayDeque<String>()
        private val forwardStack = ArrayDeque<String>()

        init {
            mainStack.addFirst(homepage)
        }

        fun visit(url: String) {
            mainStack.addFirst(url)
            forwardStack.clear()
        }

        fun back(steps: Int): String {
            val times = min(mainStack.size - 1, steps)
            repeat(times) {
                val removedItem = mainStack.removeFirst()
                forwardStack.addFirst(removedItem)
            }
            return mainStack.first()
        }

        fun forward(steps: Int): String {
            //we can keep forwardStack empty
            val times = min(forwardStack.size, steps)
            repeat(times) {
                val removedItem = forwardStack.removeFirst()
                mainStack.addFirst(removedItem)
            }
            return mainStack.first()
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