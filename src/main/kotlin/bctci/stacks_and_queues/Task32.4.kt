package bctci.stacks_and_queues

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.math.min

class Task32_4() {
    fun performActions(actions: Array<Action>): String {
        return Solution(actions).performActions()
    }

    class Solution(private val actions: Array<Action>) {
        private val stack = ArrayDeque<String>()
        fun performActions(): String {
            //the first action is always 'go'
            stack.addFirst(actions[0].meta as String)
            for (k in 1 until actions.size) {
                val action = actions[k]
                when (action.type) {
                    "go" -> stack.addFirst(action.meta as String)
                    "back" -> performBackAction(action.meta as Int)
                    else -> throw IllegalArgumentException("Unsupported action type")
                }
            }
            return stack.first()
        }

        private fun performBackAction(times: Int) {
            //we always need to keep at least one url in our stack
            val backTimes = min(stack.size - 1, times)
            repeat(backTimes) { stack.removeFirst() }
        }
    }

    data class Action(
        val type: String,
        var meta: Any
    )
}

private class Task32_4Test {
    private val task = Task32_4()

    @Test
    fun test() {
        val actions = arrayOf(
            Task32_4.Action("go", "google.com"),
            Task32_4.Action("go", "wikipedia.com"),
            Task32_4.Action("go", "amazon.com"),
            Task32_4.Action("back", 4),
            Task32_4.Action("go", "youtube.com"),
            Task32_4.Action("go", "netflix.com"),
            Task32_4.Action("back", 1)
        )
        Assertions.assertEquals("youtube.com", task.performActions(actions))
    }

    @Test
    fun test2() {
        val actions = arrayOf(
            Task32_4.Action("go", "google.com"),
            Task32_4.Action("back", 2)
        )
        Assertions.assertEquals("google.com", task.performActions(actions))
    }

    @Test
    fun test3() {
        val actions = arrayOf(
            Task32_4.Action("go", "google.com"),
            Task32_4.Action("back", 1),
            Task32_4.Action("back", 1)
        )
        Assertions.assertEquals("google.com", task.performActions(actions))
    }

    @Test
    fun test4() {
        val actions = arrayOf(
            Task32_4.Action("go", "site1.com"),
            Task32_4.Action("go", "site2.com"),
            Task32_4.Action("back", 1),
            Task32_4.Action("back", 1)
        )
        Assertions.assertEquals("site1.com", task.performActions(actions))
    }
}