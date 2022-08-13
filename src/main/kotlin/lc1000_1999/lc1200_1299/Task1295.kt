package lc1000_1999.lc1200_1299

//From a learning section, Solved
//https://leetcode.com/problems/find-numbers-with-even-number-of-digits/
class Task1295 {
    fun findNumbers(nums: IntArray): Int {
        var count = 0
        for (num in nums) {
            if (num.toString().length % 2 == 0) {
                count++
            }
        }
        return count
    }
}


fun main() {
    val obj = Task1295()
    println(obj.findNumbers(intArrayOf(12, 345, 2, 6, 7896)))
    println(obj.findNumbers(intArrayOf(555, 901, 482, 1771)))
}