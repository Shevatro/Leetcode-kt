package lc1_999.lc200_299

import common.IntSinglyNode
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import toIntSinglyNode

//From a learning section, Solved
//https://leetcode.com/problems/palindrome-linked-list/
class Task234 {
    fun isPalindrome(head: IntSinglyNode?): Boolean {
        var headPointer = head
        var middlePointer = head
        val size = getSize(head)
        for (i in 1 until size / 2) {
            val node = middlePointer?.next
            middlePointer?.next = middlePointer?.next?.next
            headPointer = node?.apply { next = headPointer }
        }
        middlePointer = middlePointer?.next
        if (size % 2 == 1) middlePointer = middlePointer?.next

        while (middlePointer != null) {
            if (headPointer?.`val` != middlePointer.`val`) {
                return false
            }
            headPointer = headPointer.next
            middlePointer = middlePointer.next
        }
        return true
    }

    private fun getSize(head: IntSinglyNode?): Int {
        var size = 0
        var node = head
        while (node != null) {
            size++
            node = node.next
        }
        return size
    }
}

private class Task234Test {
    private val task = Task234()

    @Test
    fun isPalindrome() {
        isPalindrome(intArrayOf(1, 2, 2, 1).toIntSinglyNode(), true)
        isPalindrome(intArrayOf(1, 2).toIntSinglyNode(), false)
        isPalindrome(intArrayOf(1, 2, 3).toIntSinglyNode(), false)
        isPalindrome(intArrayOf(2, 2).toIntSinglyNode(), true)
        isPalindrome(intArrayOf(1, 2, 1).toIntSinglyNode(), true)
        isPalindrome(intArrayOf(1, 2, 3, 2, 1).toIntSinglyNode(), true)
        isPalindrome(intArrayOf(1).toIntSinglyNode(), true)
        isPalindrome(intArrayOf(1, 0, 0).toIntSinglyNode(), false)
        isPalindrome(intArrayOf(1, 2, 3, 4, 3, 2, 1).toIntSinglyNode(), true)
        isPalindrome(intArrayOf(1, 2, 3, 4, 3, 2, 2).toIntSinglyNode(), false)
        isPalindrome(intArrayOf(1, 2, 3, 3, 2, 1).toIntSinglyNode(), true)
        isPalindrome(intArrayOf(1, 2, 3, 3, 1, 1).toIntSinglyNode(), false)
    }

    private fun isPalindrome(actualInp: IntSinglyNode?, expected: Boolean) {
        val actual = task.isPalindrome(actualInp)
        println(actual)
        Assertions.assertEquals(expected, actual)
    }
}