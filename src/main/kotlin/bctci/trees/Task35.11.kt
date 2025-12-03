package bctci.trees

import common.IntTreeNode
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.math.max
import kotlin.math.min

class Task35_11 {
    fun getMostProtectedNode(root: IntTreeNode?): Int {
        if (root == null) return 0
        fillOutLongestChainOfDescendants(root)

        var curLevel = -1
        var mostProtectedValue = -1
        val queue = ArrayDeque<IntTreeNode>()
        queue.addLast(root)
        while (queue.isNotEmpty()) {
            curLevel++
            val nodesAtLevel = queue.size
            var indAtLevel = 0
            repeat(nodesAtLevel) {
                val item = queue.removeFirst()
                val rule1And2 = min(curLevel, item.`val`)
                val rule3And4 = min(indAtLevel, nodesAtLevel - indAtLevel - 1)
                mostProtectedValue = max(mostProtectedValue, min(rule1And2, rule3And4))
                indAtLevel++

                item.left?.let { queue.addLast(it) }
                item.right?.let { queue.addLast(it) }
            }
        }
        return mostProtectedValue
    }

    private fun fillOutLongestChainOfDescendants(node: IntTreeNode?): Int {
        if (node == null) return 0
        node.`val` = max(fillOutLongestChainOfDescendants(node.left), fillOutLongestChainOfDescendants(node.right))
        return node.`val` + 1
    }
}

private class Task35_11Test {
    private val task = Task35_11()

    @Test
    fun test1() {
        val tree = IntTreeNode(1).apply {
            left = IntTreeNode(2).apply {
                left = IntTreeNode(3).apply {
                    left = IntTreeNode(4).apply {
                        left = IntTreeNode(5).apply {
                            left = IntTreeNode(6)
                        }
                        right = IntTreeNode(7).apply {
                            left = IntTreeNode(8)
                            right = IntTreeNode(9)
                        }
                    }
                    right = IntTreeNode(10).apply {
                        left = IntTreeNode(11)
                    }
                }
                right = IntTreeNode(12).apply {
                    left = IntTreeNode(13).apply {
                        left = IntTreeNode(14).apply {
                            left = IntTreeNode(15)
                            right = IntTreeNode(16)
                        }
                    }
                }
            }
            right = IntTreeNode(17).apply {
                left = IntTreeNode(18).apply {
                    left = IntTreeNode(19).apply {
                        left = IntTreeNode(20).apply {
                            left = IntTreeNode(21)
                        }
                    }
                }
                right = IntTreeNode(22).apply {
                    left = IntTreeNode(23).apply {
                        left = IntTreeNode(24).apply {
                            left = IntTreeNode(25)
                        }
                    }
                }
            }
        }
        Assertions.assertEquals(2, task.getMostProtectedNode(tree))
    }

    @Test
    fun test2() {
        val tree = IntTreeNode(1).apply {
            left = IntTreeNode(2).apply {
                left = IntTreeNode(3).apply {
                    left = IntTreeNode(4).apply {
                        left = IntTreeNode(5).apply {
                            left = IntTreeNode(6)
                        }
                        right = IntTreeNode(9).apply {
                            left = IntTreeNode(7)
                            right = IntTreeNode(8)
                        }
                    }
                    right = IntTreeNode(11).apply {
                        right = IntTreeNode(10)
                    }
                }
                right = IntTreeNode(12).apply {
                    right = IntTreeNode(13).apply {
                        right = IntTreeNode(14).apply {
                            left = IntTreeNode(15)
                            right = IntTreeNode(16)
                        }
                    }
                }
            }
            right = IntTreeNode(17).apply {
                left = IntTreeNode(18).apply {
                    right = IntTreeNode(20).apply {
                        right = IntTreeNode(22).apply {
                            right = IntTreeNode(24)
                        }
                    }
                }
                right = IntTreeNode(19).apply {
                    right = IntTreeNode(21).apply {
                        right = IntTreeNode(23).apply {
                            right = IntTreeNode(25)
                        }
                    }
                }
            }
        }
        Assertions.assertEquals(2, task.getMostProtectedNode(tree))
    }

    @Test
    fun test3() {
        val tree = IntTreeNode(1)
        Assertions.assertEquals(0, task.getMostProtectedNode(tree))
    }

    @Test
    fun test5() {
        val tree = IntTreeNode(1).apply {
            left = IntTreeNode(2).apply {
                left = IntTreeNode(3).apply {
                    left = IntTreeNode(4)
                }
            }
        }
        Assertions.assertEquals(0, task.getMostProtectedNode(tree))
    }

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun testPerfectTrees(input: Int, expected: Int) {
        Assertions.assertEquals(expected, task.getMostProtectedNode(createPerfectTree(input)))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(1, 0),
                Arguments.of(2, 0),
                Arguments.of(3, 0),
                Arguments.of(4, 1),
                Arguments.of(5, 1),
                Arguments.of(6, 2),
                Arguments.of(7, 3),
                Arguments.of(8, 3),
                Arguments.of(9, 4)
            )
        }
    }

    private fun createPerfectTree(height: Int): IntTreeNode {
        if (height == 1) return IntTreeNode(1)
        return IntTreeNode(1).apply {
            left = createPerfectTree(height - 1)
            right = createPerfectTree(height - 1)
        }
    }
}