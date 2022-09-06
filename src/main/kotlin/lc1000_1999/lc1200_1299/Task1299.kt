package lc1000_1999.lc1200_1299

import toString2
import kotlin.math.max

//From a learning section, Solved
//https://leetcode.com/problems/replace-elements-with-greatest-element-on-right-side/
class Task1299 {
    fun replaceElements(arr: IntArray): IntArray {
        var max = -1
        for (i in arr.lastIndex downTo 0) {
            val currentItem = arr[i]
            arr[i] = max
            max = max(currentItem, max)
        }
        return arr
    }
}


fun main() {
    val obj = Task1299()
    println(obj.replaceElements(intArrayOf(17, 18, 5, 4, 6, 1)).toString2())
    println(obj.replaceElements(intArrayOf(400)).toString2())
    println(obj.replaceElements(intArrayOf(6, 10, 5, 4, 6, 1)).toString2())
}