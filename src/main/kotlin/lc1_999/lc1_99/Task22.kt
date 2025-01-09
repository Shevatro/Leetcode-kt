package lc1_999.lc1_99

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

//From Cracking The Coding Interview, Solved but with a hint
//https://leetcode.com/problems/generate-parentheses/
class Task22 {
    fun generateParenthesis(n: Int): List<String> {
        return Solution2(n).generateParenthesis()
    }

    private class Solution2(n: Int) {
        private var result = mutableListOf<String>()
        private val current = StringBuilder()
        private var amountOpened = n
        private var amountClosed = n
        private val expectedSize = n * 2
        fun generateParenthesis(): List<String> {
            generate()
            return result
        }

        private fun generate() {
            // print(current.toString()+ " "+amountOpened + " "+amountClosed)
            if (current.length == expectedSize) {
                // println(" found:"+current.toString())
                result.add(current.toString())
            } else {
                // println(" continue")
                val amountOpenedSaved = amountOpened
                val amountClosedSaved = amountClosed
                if (amountOpened > 0) {
                    addOpened()
                    generate()
                    restorePreviousState(amountOpenedSaved, amountClosedSaved)
                }
                if (amountClosed > amountOpened) {
                    addClosed()
                    generate()
                    restorePreviousState(amountOpenedSaved, amountClosedSaved)
                }
            }
        }

        private fun addOpened(){
            current.append('(')
            amountOpened--
        }

        private fun addClosed(){
            current.append(')')
            amountClosed--
        }

        private fun restorePreviousState(amountOpenedSaved: Int, amountClosedSaved: Int){
            current.deleteCharAt(current.length - 1)
            amountOpened = amountOpenedSaved
            amountClosed = amountClosedSaved
        }
    }
}

private class Task22Test {
    private val task = Task22()

    @Test
    fun generateParenthesisTest1() {
        val actualResult = task.generateParenthesis(3)
        val expectedResult = listOf("((()))", "(()())", "(())()", "()(())", "()()()")
        Assertions.assertEquals(expectedResult, actualResult)
    }

    @Test
    fun generateParenthesisTest2() {
        val actualResult = task.generateParenthesis(1)
        val expectedResult = listOf("()")
        Assertions.assertEquals(expectedResult, actualResult)
    }


    @Test
    fun generateParenthesisTest3() {
        val actualResult = task.generateParenthesis(4)
        val expectedResult = listOf(
            "(((())))", "((()()))", "((())())", "((()))()", "(()(()))", "(()()())", "(()())()", "(())(())",
            "(())()()", "()((()))", "()(()())", "()(())()", "()()(())", "()()()()"
        )
        Assertions.assertEquals(emptySet<Int>(), actualResult.subtract(expectedResult))
    }
}