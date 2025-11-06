package lc1_999.lc300_399

import lc1_999.lc300_399.Task339.NestedInteger
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

//From Beyond Cracking The Coding Interview, Solved
//https://leetcode.com/problems/nested-list-weight-sum/
class Task339() {
    fun depthSum(nestedList: List<NestedInteger>): Int {
        return calcRec(nestedList, 1)
    }

    private fun calcRec(nestedList: List<NestedInteger>, depth: Int): Int {
        var sum = 0
        for (item in nestedList) {
            sum += if (item.isInteger()) {
                item.getInteger() * depth
            } else {
                calcRec(item.getList(), depth + 1)
            }
        }
        return sum
    }

    //provided
    class NestedInteger {

        private var integer: Int? = null
        private var list: MutableList<NestedInteger>? = null

        constructor()

        constructor(value: Int) {
            this.integer = value
        }

        /** @return true if this NestedInteger holds a single integer, rather than a nested list. */
        fun isInteger(): Boolean = integer != null

        /** @return the single integer that this NestedInteger holds, if it holds a single integer */
        fun getInteger(): Int = integer ?: throw IllegalStateException("This NestedInteger holds a list")

        /** Set this NestedInteger to hold a single integer. */
        fun setInteger(value: Int) {
            this.integer = value
            this.list = null
        }

        /** Set this NestedInteger to hold a nested list and adds a nested integer to it. */
        fun add(ni: NestedInteger) {
            if (list == null) {
                list = mutableListOf()
                integer = null
            }
            list!!.add(ni)
        }

        /** @return the nested list that this NestedInteger holds, if it holds a nested list */
        fun getList(): List<NestedInteger> = list ?: emptyList()
    }

}


private class Task339Test {
    private val task = Task339()

    @Test
    fun test1() {
        val input = listOf(
            NestedInteger().apply {
                add(NestedInteger(1))
                add(NestedInteger(1))
            },
            NestedInteger(2),
            NestedInteger().apply {
                add(NestedInteger(1))
                add(NestedInteger(1))
            }
        )
        Assertions.assertEquals(10, task.depthSum(input))
    }

    @Test
    fun test2() {
        val input = listOf(
            NestedInteger(1),
            NestedInteger().apply {
                add(NestedInteger(4))
                add(NestedInteger().apply { add(NestedInteger(6)) })
            }
        )

        Assertions.assertEquals(27, task.depthSum(input))
    }

    @Test
    fun test3() {
        val input = listOf(NestedInteger(0),)
        Assertions.assertEquals(0, task.depthSum(input))
    }
}