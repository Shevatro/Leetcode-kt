package lc1_999.lc900_999

//From a learning section, Solved
//https://leetcode.com/problems/valid-mountain-array/
class Task941 {
    fun validMountainArray(arr: IntArray): Boolean {
        if (arr.size < 3) return false
        var isIncreasing = true
        for (i in 1 until arr.size) {
            if (arr[i] == arr[i - 1]) {
                return false
            }
            if (isIncreasing) {
                if (arr[i] < arr[i - 1]) {
                    if (i == 1) {
                        return false
                    } else {
                        isIncreasing = false
                    }
                }
            } else {
                if (arr[i] > arr[i - 1]) {
                    return false
                }
            }
        }
        return !isIncreasing
    }
}


fun main() {
    val obj = Task941()
    println(obj.validMountainArray(intArrayOf(2, 1)))
    println(obj.validMountainArray(intArrayOf(3, 5, 5)))
    println(obj.validMountainArray(intArrayOf(0, 3, 2, 1)))
    println(obj.validMountainArray(intArrayOf(1, 7, 5, 6)))
    println(obj.validMountainArray(intArrayOf(7, 9, 9, 4)))
    println(obj.validMountainArray(intArrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)))
    println(obj.validMountainArray(intArrayOf(9, 8, 7, 6, 5, 4, 3, 2, 1, 0)))
}