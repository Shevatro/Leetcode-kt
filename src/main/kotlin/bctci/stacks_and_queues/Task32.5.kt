package bctci.stacks_and_queues

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.math.min

class Task32_5() {
    fun performActions(actions: Array<Action>): String {
        return Solution(actions).performActions()
    }

    class Solution(private val actions: Array<Action>) {
        private val storage = ArrayList<String>()
        private var maxAvailablePointer = 0
        private var pointer = -1
        fun performActions(): String {
            for (action in actions) {
                when (action.type) {
                    "go" -> performGoAction(action.meta as String)
                    "back" -> performBackAction(action.meta as Int)
                    "forward" -> performForwardAction(action.meta as Int)
                    else -> throw IllegalArgumentException("Unsupported action type")
                }
            }
            return storage[pointer]
        }

        private fun performGoAction(url: String) {
            pointer++
            maxAvailablePointer = pointer
            if (storage.size == maxAvailablePointer) {
                storage.add(url)
            } else {
                storage[pointer] = url
            }
        }

        private fun performBackAction(times: Int) {
            pointer -= min(pointer, times)
        }

        private fun performForwardAction(times: Int) {
            pointer += min(maxAvailablePointer - pointer, times)
        }
    }

    data class Action(
        val type: String,
        var meta: Any
    )
}

private class Task35_5Test {
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