package lc1_999.lc700_799

//From a learning section, Solved
//https://leetcode.com/problems/binary-search/
class Task704 {

    fun search(nums: IntArray, target: Int): Int {
        var startPos = 0
        var endPos = nums.lastIndex
        while (startPos <= endPos) {
            val midPos = (endPos + startPos) / 2
            if (nums[midPos] == target) {
                return midPos
            } else if (nums[midPos] < target) {
                startPos = midPos + 1
            } else {
                endPos = midPos - 1
            }
        }
        return -1
    }
}


fun main() {
    val obj = Task704()
    println(obj.search(intArrayOf(-1, 0, 3, 5, 9, 12), 9))
    println(obj.search(intArrayOf(-1, 0, 3, 5, 9, 12), 2))
    println(obj.search(intArrayOf(2), 2))
    println(obj.search(intArrayOf(2, 5), 5))
}

