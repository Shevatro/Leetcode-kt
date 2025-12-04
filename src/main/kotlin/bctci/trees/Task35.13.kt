package bctci.trees

import common.IntTreeNode
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.math.abs

class Task35_13 {
    fun getBSTNearestValue(root: IntTreeNode?, target: Int): Int {
        return Solution(target).getBSTNearestValue(root)
    }

    private class Solution(private val target: Int) {
        private var closestTarget = Int.MIN_VALUE
        private var minDiff = Int.MAX_VALUE
        fun getBSTNearestValue(root: IntTreeNode?): Int {
            var p = root
            while (p != null) {
                val diff = abs(p.`val` - target)
                if (diff == 0) return p.`val`
                if (diff < minDiff || (diff == minDiff && p.`val` < closestTarget)) {
                    minDiff = diff
                    closestTarget = p.`val`
                }
                p = if (target > p.`val`) p.right else p.left
            }
            return closestTarget
        }
    }
}

private class Task35_13Test {
    private val task = Task35_13()

    @Test
    fun test1() {
        val tree = IntTreeNode(5).apply {
            left = IntTreeNode(2).apply {
                right = IntTreeNode(4)
            }
            right = IntTreeNode(9).apply {
                left = IntTreeNode(9).apply {
                    right = IntTreeNode(9)
                }
                right = IntTreeNode(11)
            }
        }
        Assertions.assertEquals(4, task.getBSTNearestValue(tree, 4))
    }

    @Test
    fun test2() {
        val tree = IntTreeNode(5).apply {
            left = IntTreeNode(2).apply {
                right = IntTreeNode(4)
            }
            right = IntTreeNode(9).apply {
                left = IntTreeNode(9).apply {
                    right = IntTreeNode(9)
                }
                right = IntTreeNode(11)
            }
        }
        Assertions.assertEquals(2, task.getBSTNearestValue(tree, 3))
    }

    @Test
    fun test3() {
        val tree = IntTreeNode(5).apply {
            left = IntTreeNode(2).apply {
                right = IntTreeNode(4)
            }
            right = IntTreeNode(9).apply {
                left = IntTreeNode(9).apply {
                    right = IntTreeNode(9)
                }
                right = IntTreeNode(11)
            }
        }
        Assertions.assertEquals(5, task.getBSTNearestValue(tree, 6))
    }

    @Test
    fun test4() {
        val tree = IntTreeNode(5).apply {
            left = IntTreeNode(2).apply {
                right = IntTreeNode(4)
            }
            right = IntTreeNode(9).apply {
                left = IntTreeNode(9).apply {
                    right = IntTreeNode(9)
                }
                right = IntTreeNode(11)
            }
        }
        Assertions.assertEquals(9, task.getBSTNearestValue(tree, 9))
    }

    @Test
    fun test5() {
        val tree = IntTreeNode(1)
        Assertions.assertEquals(1, task.getBSTNearestValue(tree, 1))
    }

    @Test
    fun test6() {
        val tree = IntTreeNode(1)
        Assertions.assertEquals(1, task.getBSTNearestValue(tree, 2))
    }

    @Test
    fun test7() {
        val tree = IntTreeNode(4).apply {
            left = IntTreeNode(2).apply {
                left = IntTreeNode(1)
                right = IntTreeNode(3)
            }
            right = IntTreeNode(6).apply {
                left = IntTreeNode(5)
                right = IntTreeNode(7)
            }
        }
        Assertions.assertEquals(5, task.getBSTNearestValue(tree, 5))
    }

    @Test
    fun test8() {
        val tree = IntTreeNode(4).apply {
            left = IntTreeNode(2).apply {
                left = IntTreeNode(1)
                right = IntTreeNode(3)
            }
            right = IntTreeNode(6).apply {
                left = IntTreeNode(5)
                right = IntTreeNode(7)
            }
        }
        Assertions.assertEquals(7, task.getBSTNearestValue(tree, 8))
    }

    @Test
    fun test9() {
        val tree = IntTreeNode(5).apply {
            left = IntTreeNode(3).apply {
                left = IntTreeNode(2).apply {
                    left = IntTreeNode(1)
                }
                right = IntTreeNode(4)
            }
        }
        Assertions.assertEquals(1, task.getBSTNearestValue(tree, 1))
    }

    @Test
    fun test10() {
        val tree = IntTreeNode(5).apply {
            left = IntTreeNode(3).apply {
                left = IntTreeNode(2).apply {
                    left = IntTreeNode(1)
                }
                right = IntTreeNode(4)
            }
        }
        Assertions.assertEquals(5, task.getBSTNearestValue(tree, 5))
    }

    @Test
    fun test11() {
        val tree = IntTreeNode(5).apply {
            left = IntTreeNode(3).apply {
                left = IntTreeNode(2).apply {
                    left = IntTreeNode(1)
                }
                right = IntTreeNode(4)
            }
        }
        Assertions.assertEquals(5, task.getBSTNearestValue(tree, 6))
    }

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun testExampleFromBook(input: Int, expected: Int) {
        val tree = IntTreeNode(8).apply {
            left = IntTreeNode(6).apply {
                left = IntTreeNode(5).apply {
                    left = IntTreeNode(2)
                    right = IntTreeNode(6)
                }
                right = IntTreeNode(8).apply {
                    left = IntTreeNode(8)
                    right = IntTreeNode(8)
                }
            }
            right = IntTreeNode(12).apply {
                left = IntTreeNode(10).apply {
                    left = IntTreeNode(9)
                }
            }
        }
        Assertions.assertEquals(expected, task.getBSTNearestValue(tree, input))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(9, 9),
                Arguments.of(13, 12),
                Arguments.of(1, 2),
                Arguments.of(8, 8),
                Arguments.of(6, 6),
                Arguments.of(7, 6),
                Arguments.of(11, 10),
                Arguments.of(4, 5)
            )
        }
    }
}