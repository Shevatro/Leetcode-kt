package lc500_599
//Black list, wrong solution
//https://leetcode.com/problems/perfect-number/

class Task507 {
    fun checkPerfectNumber(num: Int): Boolean {
        var sum = 0
        for (curNum in num - 1 downTo 1) {
            if (num % curNum == 0) {
                sum += curNum
            }
        }
        return sum == num
    }
}

fun main() {
    val obj = Task507()
    println(obj.checkPerfectNumber(2))
}