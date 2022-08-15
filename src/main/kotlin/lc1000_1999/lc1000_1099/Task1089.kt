package lc1000_1999.lc1000_1099

import toString2

//From a learning section, Solved but not optimized
//https://leetcode.com/problems/duplicate-zeros/
class Task1089 {

    fun duplicateZeros(arr: IntArray): Unit {
        var (amountZeros, isLastZeroSplit) = countZeros(arr)
        var i = arr.lastIndex
        if (isLastZeroSplit) {
            arr[i] = 0
            i--
        }
        //zeroesAmount != 0 - small optimization
        if (amountZeros != 0) {
            while (i > 0) {
                val item = arr[i - amountZeros]
                arr[i] = item
                i--
                if (item == 0) {
                    amountZeros--
                    if (i > 0) {
                        arr[i] = 0
                        i--
                    }
                }
            }
        }
        println(arr.toString2())
    }

    private fun countZeros(arr: IntArray): Pair<Int, Boolean> {
        var amountZeros = 0
        var amountNonZeros = 0
        var isLastZeroSplit = false
        var i = 0
        while (i < arr.size - amountZeros) {
            if (arr[i] == 0) {
                amountZeros++
            } else {
                amountNonZeros++
            }
            i++
        }
        //We can't fit the last zero completely
        if (arr.size - amountZeros * 2 - amountNonZeros < 0) {
            amountZeros--
            isLastZeroSplit = true
        }
        return amountZeros to isLastZeroSplit
    }
}


fun main() {
    val obj = Task1089()
    obj.duplicateZeros(intArrayOf(1, 0, 2, 3, 0, 4, 5, 0))
    obj.duplicateZeros(intArrayOf(1, 2, 3))
    obj.duplicateZeros(intArrayOf(0, 0, 0))
    obj.duplicateZeros(intArrayOf(0))
    obj.duplicateZeros(intArrayOf(8, 4, 5, 0, 0, 0, 0, 7))
    obj.duplicateZeros(intArrayOf(8, 4, 5, 0, 0, 0, 7))
}

