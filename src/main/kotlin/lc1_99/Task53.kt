package lc1_99
//Not solved, repeat
//https://leetcode.com/problems/maximum-subarray/
import java.lang.Integer.max

class Task53 {
    var sum = Int.MIN_VALUE
    fun maximumSubarray(list: List<Int>): Int {
        if (list.isEmpty()) return 0
        if (list.size == 1) return list[0]
        val part1 = list.subList(0, list.size / 2)
        val part2 = list.subList(list.size / 2, list.size)
        val sumPart1 = maximumSubarray(part1)
        val sumPart2 = maximumSubarray(part2)
        val sum = sumPart1 + sumPart2
//        println(part1.toString()+"**"+part2.toString()+"**"+sum+"**"+sumPart1+"**"+sumPart2)
        println(part1.toString() + "**" + part2.toString())
        println(sum.toString() + ">" + this.sum + "=" + (sum > this.sum))
        if (sum > this.sum) {
            this.sum = sum
        }
//        val result = if (sum > sumPart1 && sum > sumPart2) {
//            sum
//        } else if (sumPart2 > sumPart1) {
//            sumPart2
//        } else {
//            sumPart1
//        }
//        println("result="+result)
        return sum
    }

    fun maximumSubarrayLinear(list: List<Int>): Int {
        var curSum = list[0]
        var sum = list[0]
        for (i in 1 until list.size) {
            curSum = max(curSum + list[i], list[i])
            sum = max(curSum, sum)
            println(curSum.toString() + "====" + sum)
        }
        return sum
    }
}

fun main() {
    val obj = Task53()
    val list1 = listOf(-2, 1, -3, 4, -1, 2, 1, -5, 4)
    println(obj.maximumSubarrayLinear(list1))
    val list2 = listOf(1)
    println(obj.maximumSubarrayLinear(list2))
    val list3 = listOf(5, 4, -1, 7, 8)
    println(obj.maximumSubarrayLinear(list3))
//    obj.maximumSubarray(list)
//    println(obj.sum)

}