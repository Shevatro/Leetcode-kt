package lc1_999.lc1_99
//Solved, but repeat
//https://leetcode.com/problems/plus-one/

import toString2

class Task66 {
    fun plusOneNotOptimise(digits: IntArray): IntArray {
        for (i in digits.size - 1 downTo 0) {
            if (digits[i] < 9) {
                digits[i]++
                return digits
            } else {
                digits[i] = 0
            }
        }
        ///////////
        val newDigits = digits.copyOf(digits.size + 1)
        newDigits[0] = 1
        for (i in 1 until newDigits.size) {
            newDigits[i] = digits[i - 1]
        }
        return newDigits
    }

    fun plusOne(digits: IntArray): IntArray {
        for (i in digits.size - 1 downTo 0) {
            if (digits[i] < 9) {
                digits[i]++
                return digits
            }
            digits[i] = 0
        }
        return IntArray(digits.size + 1).apply { this[0] = 1 }
    }
}

fun main() {
    println(Task66().plusOne(intArrayOf(0)).toString2())
    println(Task66().plusOne(intArrayOf(1, 2, 3)).toString2())
    println(Task66().plusOne(intArrayOf(1, 2, 9)).toString2())
    println(Task66().plusOne(intArrayOf(1, 9, 9)).toString2())
    println(Task66().plusOne(intArrayOf(9, 9, 9)).toString2())
}