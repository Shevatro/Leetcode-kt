package lc1000_1999.lc1300_1399

//From a learning section, Solved
//https://leetcode.com/problems/check-if-n-and-its-double-exist/
class Task1346 {
    fun checkIfExist(arr: IntArray): Boolean {
        arr.sort()
        for (i in arr.indices) {
            val pos = arr.binarySearch(arr[i] * 2)
            if (pos >= 0 && pos != i) return true
        }
        return false
    }
}


fun main() {
    val obj = Task1346()
    println(obj.checkIfExist(intArrayOf(10, 2, 5, 3)))
    println(obj.checkIfExist(intArrayOf(7, 1, 14, 11)))
    println(obj.checkIfExist(intArrayOf(3, 1, 7, 11)))
    println(obj.checkIfExist(intArrayOf(-10, 12, -20, -8, 15)))
    println(obj.checkIfExist(intArrayOf(0, 2)))
}

