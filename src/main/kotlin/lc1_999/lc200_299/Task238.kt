package lc1_999.lc200_299
//Not solved, repeat
//https://leetcode.com/problems/product-of-array-except-self/

import toString2

class Task238 {
    fun productExceptSelfWithDivision(nums: IntArray): List<Int> {
        val list = mutableListOf<Int>()
        val (mult, zeroCount) = findMultiplication(nums)
        println(mult.toString() + " " + zeroCount)
        for (num in nums) {
            if (zeroCount == 2) {
                list.add(0)
            } else if (zeroCount == 1) {
                if (num == 0) {
                    list.add(mult)
                } else {
                    list.add(0)
                }
            } else {
                list.add(mult / num)
            }
        }
        return list
    }

    //найти общее умножение и количество нулей
    private fun findMultiplication(nums: IntArray): Pair<Int, Int> {
        var mult = 1
        var zeroCount = 0
        for (num in nums) {
            if (num != 0) {
                mult *= num
            } else {
                zeroCount++
            }
        }
        return mult to zeroCount
    }

//    fun productExceptSelf(nums: IntArray): IntArray {
//        val arr = IntArray(nums.size)
//        var mult = nums[0]
//        arr[0] = 1
//        for (i in 1 until nums.size) {
//            arr[i] = mult
//            mult *= nums[i]
//        }
//        ////
//        mult = nums[nums.size - 1]
//        for (i in nums.size - 2 downTo 0) {
//            arr[i] = mult * arr[i]
//            mult *= nums[i]
//        }
//        return arr
//    }

    fun productExceptSelf(nums: IntArray): IntArray {
        val arr = setToZero(nums)
        var multFromStart = 1
        var multFromEnd = 1
        for (i in nums.indices) {
            val j = nums.size - 1 - i
            arr[i] *= multFromStart
            arr[j] *= multFromEnd
            multFromStart *= nums[i]
            multFromEnd *= nums[j]
        }
        return arr
    }

    private fun setToZero(nums: IntArray): IntArray {
        return IntArray(nums.size).apply {
            fill(1)
        }
    }
}

fun main() {
    val obj = Task238()
    println(obj.productExceptSelf(intArrayOf(1, 2, 3, 4)).toString2())
    println("------------")
    println(obj.productExceptSelf(intArrayOf(1, 2, 3, 4, 5)).toString2())
    println("------------")
    println(obj.productExceptSelf(intArrayOf(-1, 1, 0, -3, 3)).toString2())
    println("------------")
    println(obj.productExceptSelf(intArrayOf(5, 2, 3, 4)).toString2())
    println("------------")
    println(obj.productExceptSelf(intArrayOf(1, 2)).toString2())
}

