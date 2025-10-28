package bctci.stacks_and_queues

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.math.min

class Task32_5() {
    fun performActions(actions: Array<Action>): String {
        return Solution(actions).performActions()
    }

    class Solution(private val actions: Array<Action>) {
        private val mainStack = ArrayDeque<String>()
        private val forwardStack = ArrayDeque<String>()
        fun performActions(): String {
            //the first action is always 'go'
            mainStack.addFirst(actions[0].meta as String)
            for (k in 1 until actions.size) {
                val action = actions[k]
                when (action.type) {
                    "go" -> performGoAction(action.meta as String)
                    "back" -> performBackAction(action.meta as Int)
                    "forward" -> performForwardAction(action.meta as Int)
                    else -> throw IllegalArgumentException("Unsupported action type")
                }
            }
            return mainStack.first()
        }

        private fun performGoAction(url: String) {
            mainStack.addFirst(url)
            forwardStack.clear()
        }

        private fun performBackAction(times: Int) {
            //we always need to keep at least one url in our stack
            val times = min(mainStack.size - 1, times)
            repeat(times) {
                val removedItem = mainStack.removeFirst()
                forwardStack.addFirst(removedItem)
            }
        }

        private fun performForwardAction(times: Int) {
            //we can keep forwardStack empty
            val times = min(forwardStack.size, times)
            repeat(times) {
                val removedItem = forwardStack.removeFirst()
                mainStack.addFirst(removedItem)
            }
        }
    }

    data class Action(
        val type: String,
        var meta: Any
    )
}

private class Task35_4Test {
    private val task = Task32_5()

    @Test
    fun test() {
        val actions = arrayOf(
            Task32_5.Action("go", "google.com"),
            Task32_5.Action("go", "wikipedia.com"),
            Task32_5.Action("back", 1),
            Task32_5.Action("forward", 1),
            Task32_5.Action("back", 3),
            Task32_5.Action("go", "netflix.com"),
            Task32_5.Action("forward", 3)
        )
        Assertions.assertEquals("netflix.com", task.performActions(actions))
    }

    @Test
    fun test2() {
        val actions = arrayOf(
            Task32_5.Action("go", "example.com"),
            Task32_5.Action("forward", 1)
        )
        Assertions.assertEquals("example.com", task.performActions(actions))
    }

    @Test
    fun test3() {
        val actions = arrayOf(
            Task32_5.Action("go", "site1.com"),
            Task32_5.Action("go", "site2.com"),
            Task32_5.Action("back", 1),
            Task32_5.Action("forward", 1),
            Task32_5.Action("back", 1)
        )
        Assertions.assertEquals("site1.com", task.performActions(actions))
    }
}