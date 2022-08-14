package lc1000_1999.lc1000_1099

//From a learning section, Solved but not optimized
//https://leetcode.com/problems/duplicate-zeros/
class Task1089 {
    fun duplicateZeros(arr: IntArray): Unit {
        val tempArr = arr.clone()
        var k = 0
        var i = 0
        while (k < arr.size) {
            arr[k] = tempArr[i]
            k++
            if (tempArr[i] == 0) {
                if (k < arr.size) {
                    arr[k] = 0
                    k++
                }
            }
            i++
        }
    }
}


fun main() {
    val obj = Task1089()
    obj.duplicateZeros(intArrayOf(1, 0, 2, 3, 0, 4, 5, 0))
    obj.duplicateZeros(intArrayOf(1, 2, 3))
    obj.duplicateZeros(intArrayOf(0, 0, 0))
    obj.duplicateZeros(intArrayOf(0))
    obj.duplicateZeros(intArrayOf(8, 4, 5, 0, 0, 0, 0, 7))
}

