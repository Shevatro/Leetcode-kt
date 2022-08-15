package lc1_999.lc1_99

import toString2

//From a learning section, Solved
//https://leetcode.com/problems/merge-sorted-array/
class Task88 {

    fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int): Unit {
        val nums1Copy = nums1.copyOfRange(0, m)
        var nums1Index = 0
        var nums2Index = 0
        for (k in nums1.indices) {
            if (nums2.getOrNull(nums2Index) == null || nums1Copy.getOrNull(nums1Index) != null && nums1Copy[nums1Index] < nums2[nums2Index]) {
                nums1[k] = nums1Copy[nums1Index]
                nums1Index++
            } else {
                nums1[k] = nums2[nums2Index]
                nums2Index++
            }
        }
        println(nums1.toString2())
    }
}


fun main() {
    val obj = Task88()
    obj.merge(intArrayOf(1, 2, 3, 0, 0, 0), 3, intArrayOf(2, 5, 6), 3)
    obj.merge(intArrayOf(1), 1, intArrayOf(), 0)
    obj.merge(intArrayOf(0), 0, intArrayOf(1), 1)
    obj.merge(intArrayOf(), 0, intArrayOf(), 0)
}

