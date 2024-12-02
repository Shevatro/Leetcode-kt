package lc1_999.lc200_299

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

//From Cracking The Coding Interview, Not Solved
//https://leetcode.com/problems/course-schedule-ii/
private class Task210 {
    fun findOrder(numCourses: Int, prerequisites: Array<IntArray>): IntArray {
        return Solution2(numCourses, prerequisites).findOrder()
    }

    private class Solution(private val numCourses: Int, private val prerequisites: Array<IntArray>) {
        private val destinations = HashMap<Int, MutableSet<Int>>()
        private val result = IntArray(numCourses)
        private val state = Array(numCourses) { STATE.NONE }
        private var courseCounter = 0

        fun findOrder(): IntArray {
            fillDestinationMap()
            return if (checkAllCourses()) result else intArrayOf()
        }

        private fun checkAllCourses(): Boolean {
            for (i in 0 until numCourses) {
                if (!dfs(i)) return false
            }
            return true
        }

        private fun dfs(item: Int): Boolean {
            if (state[item] == STATE.DONE) return true
            if (state[item] == STATE.IN_PROGRESS) return false
            val allDestinations = destinations[item] ?: emptySet()
            state[item] = STATE.IN_PROGRESS
            for (destination in allDestinations) {
                if (!dfs(destination)) return false
            }
            state[item] = STATE.DONE
            result[courseCounter] = item
            courseCounter++
            return true
        }

        private fun fillDestinationMap() {
            for (prerequisite in prerequisites) {
                val from = prerequisite[0]
                val to = prerequisite[1]
                if (destinations[from] == null) {
                    destinations[from] = mutableSetOf()
                }
                destinations[from]?.add(to)
            }
        }

        private enum class STATE {
            NONE, IN_PROGRESS, DONE
        }
    }

    private class Solution2(private val numCourses: Int, private val prerequisites: Array<IntArray>) {
        private val destinations = HashMap<Int, MutableSet<Int>>()
        private val referencesRecord = IntArray(numCourses)
        private val queue = ArrayDeque<Int>()
        private val result = IntArray(numCourses)
        private var courseCounter = 0

        fun findOrder(): IntArray {
            fillReverseDestinationMapAndReferences()
            fillQueueWithNoReferenceItems()
            performTopologicalSort()
            return if (courseCounter == numCourses) result else intArrayOf()
        }

        private fun fillReverseDestinationMapAndReferences() {
            for (prerequisite in prerequisites) {
                val from = prerequisite[1]
                val to = prerequisite[0]
                if (destinations[from] == null) {
                    destinations[from] = mutableSetOf()
                }
                destinations[from]?.add(to)
                referencesRecord[to]++
            }
        }

        private fun fillQueueWithNoReferenceItems() {
            for (i in referencesRecord.indices) {
                if (referencesRecord[i] == 0) queue.addLast(i)
            }
        }

        private fun performTopologicalSort() {
            while (queue.isNotEmpty()) {
                val item = queue.removeFirst()
                result[courseCounter] = item
                courseCounter++
                for (destination in destinations[item] ?: emptySet()) {
                    referencesRecord[destination]--
                    if (referencesRecord[destination] == 0) {
                        queue.addLast(destination)
                    }
                }
            }
        }
    }
}

private class Task210Test {
    private val task = Task210()

    @Test
    fun findOrderTest1() {
        val input = arrayOf(intArrayOf(1, 0))
        val expectedResult = intArrayOf(0, 1)
        val result = task.findOrder(2, input)
        Assertions.assertArrayEquals(expectedResult, result)
    }

    @Test
    fun findOrderTest2() {
        val input = emptyArray<IntArray>()
        val expectedResult = intArrayOf(0)
        val result = task.findOrder(1, input)
        Assertions.assertArrayEquals(expectedResult, result)
    }

    @Test
    fun findOrderTest3() {
        val input = emptyArray<IntArray>()
        val result = task.findOrder(2, input)
        Assertions.assertEquals(2, result.size)
        Assertions.assertEquals(true, result.any { it in 0..1 })
    }

    @Test
    fun findOrderTest4() {
        val input = arrayOf(intArrayOf(0, 1))
        val expectedResult = intArrayOf(1, 0)
        val result = task.findOrder(2, input)
        Assertions.assertArrayEquals(expectedResult, result)
    }

    @Test
    fun findOrderTest5() {
        val input = arrayOf(intArrayOf(0, 1), intArrayOf(1, 0))
        val expectedResult = intArrayOf()
        val result = task.findOrder(2, input)
        Assertions.assertArrayEquals(expectedResult, result)
    }

    @Test
    fun findOrderTest6() {
        val input = arrayOf(intArrayOf(0, 2), intArrayOf(1, 0))
        val expectedResult1 = intArrayOf(2, 0, 1, 3)
        val expectedResult2 = intArrayOf(2, 3, 0, 1)
        val result = task.findOrder(4, input)
        val isValid = result.contentEquals(expectedResult1) || result.contentEquals(expectedResult2)
        Assertions.assertEquals(true, isValid)
    }

    @Test
    fun findOrderTest7() {
        val input = arrayOf(intArrayOf(0, 1), intArrayOf(1, 3), intArrayOf(0, 2), intArrayOf(2, 3))
        val expectedResult = intArrayOf(3, 1, 2, 0)
        val result = task.findOrder(4, input)
        Assertions.assertArrayEquals(expectedResult, result)
    }
}