package lc1_999.lc400_499

import common.IntTreeNode
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

//From Cracking The Coding Interview, Solved but not optimal
//https://leetcode.com/problems/path-sum-iii/
class Task437 {
    fun pathSum(root: IntTreeNode?, targetSum: Int): Int {
        return Solution2(root, targetSum).pathSum()
//        return Solution(root, targetSum).pathSum()
    }

    //Note: Not optimal
    private class Solution(private val root: IntTreeNode?, private val targetSum: Int) {
        private var amount = 0
        fun pathSum(): Int {
            findAllSums(root)
            return amount
        }

        private fun findAllSums(node: IntTreeNode?): MutableList<Long> {
            if (node == null) return mutableListOf()
            val left = findAllSums(node.left)
            val right = findAllSums(node.right)

            left.addAll(right)
            val sum = left.map { it + node.`val` }.toMutableList()
            sum.add(node.`val`.toLong())
            amount += sum.count { it == targetSum.toLong() }
            println(node.`val`.toString() + " amount:$amount return:$sum")
            return sum
        }
    }

    private class Solution2(private val root: IntTreeNode?, private val targetSum: Int) {
        private val sumMap = hashMapOf(0L to 1)
        fun pathSum(): Int {
            return dfs(root, 0L)
        }

        private fun dfs(node: IntTreeNode?, sum: Long): Int {
            if (node == null) return 0
            val currentSum = sum + node.`val`
            var pathSum = sumMap.getOrDefault(currentSum - targetSum, 0)
            sumMap.merge(currentSum, 1, Integer::sum) //= sumMap[currentSum] = sumMap.getOrDefault(currentSum, 0) + 1
            pathSum += dfs(node.left, currentSum)
            pathSum += dfs(node.right, currentSum)
            sumMap.merge(currentSum, -1, Integer::sum) //= sumMap[currentSum] = sumMap.getOrDefault(currentSum, 0) - 1
            return pathSum
        }
    }
}

private class Task437Test {
    private val task = Task437()

    @Test
    fun pathSumTest1() {
        val root = IntTreeNode(10).apply {
            left = IntTreeNode(5).apply {
                left = IntTreeNode(3).apply {
                    left = IntTreeNode(3)
                    right = IntTreeNode(-2)
                }
                right = IntTreeNode(2).apply {
                    right = IntTreeNode(1)
                }
            }
            right = IntTreeNode(-3).apply {
                right = IntTreeNode(11)
            }
        }

        val result = task.pathSum(root, 8)
        Assertions.assertEquals(3, result)
    }

    @Test
    fun pathSumTest2() {
        val root = IntTreeNode(5).apply {
            left = IntTreeNode(4).apply {
                left = IntTreeNode(11).apply {
                    left = IntTreeNode(7)
                    right = IntTreeNode(2)
                }
            }
            right = IntTreeNode(8).apply {
                left = IntTreeNode(13)
                right = IntTreeNode(4).apply {
                    left = IntTreeNode(5)
                    right = IntTreeNode(1)
                }
            }
        }

        val result = task.pathSum(root, 22)
        Assertions.assertEquals(3, result)
    }

    @Test
    fun pathSumTest3() {
        val root = IntTreeNode(1)

        val result = task.pathSum(root, 1)
        Assertions.assertEquals(1, result)
    }

    @Test
    fun pathSumTest4() {
        val root = IntTreeNode(1).apply {
            left = IntTreeNode(2)
        }

        val result = task.pathSum(root, 1)
        Assertions.assertEquals(1, result)
    }

    @Test
    fun pathSumTest5() {
        val root = IntTreeNode(1000000000).apply {
            left = IntTreeNode(1000000000).apply {
                left = IntTreeNode(294967296).apply {
                    left = IntTreeNode(1000000000).apply {
                        left = IntTreeNode(1000000000).apply {
                            left = IntTreeNode(1000000000)
                        }
                    }
                }
            }
        }

        val result = task.pathSum(root, 0)
        Assertions.assertEquals(0, result)
    }
}