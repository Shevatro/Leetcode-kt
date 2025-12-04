package bctci.trees

import common.IntTreeNode
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Task35_12 {
    fun bstSearch(root: IntTreeNode?, target: Int): Boolean {
//        return Solution(target).searchBST(root)
        return Solution(target).searchBSTIterative(root)
    }

    private class Solution(private val target: Int) {
        fun searchBST(root: IntTreeNode?): Boolean {
            if (root == null) return false
            if (root.`val` == target) return true
            val nextItem = if (root.`val` < target) root.right else root.left
            return searchBST(nextItem)
        }

        fun searchBSTIterative(root: IntTreeNode?): Boolean {
            var p = root
            while (p != null && p.`val` != target) {
                p = if (p.`val` < target) p.right else p.left
            }
            return p != null
        }
    }
}

private class Task35_12Test {
    private val task = Task35_12()

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
        Assertions.assertEquals(true, task.bstSearch(tree, 4))
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
        Assertions.assertEquals(false, task.bstSearch(tree, 3))
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
        Assertions.assertEquals(false, task.bstSearch(tree, 6))
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
        Assertions.assertEquals(true, task.bstSearch(tree, 9))
    }

    @Test
    fun test5() {
        Assertions.assertEquals(false, task.bstSearch(null, 1))
    }

    @Test
    fun test6() {
        val tree = IntTreeNode(1)
        Assertions.assertEquals(true, task.bstSearch(tree, 1))
    }

    @Test
    fun test7() {
        val tree = IntTreeNode(2)
        Assertions.assertEquals(false, task.bstSearch(tree, 1))
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
        Assertions.assertEquals(true, task.bstSearch(tree, 5))
    }

    @Test
    fun test9() {
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
        Assertions.assertEquals(false, task.bstSearch(tree, 8))
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
        Assertions.assertEquals(true, task.bstSearch(tree, 1))
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
        Assertions.assertEquals(true, task.bstSearch(tree, 5))
    }

    @Test
    fun test12() {
        val tree = IntTreeNode(5).apply {
            left = IntTreeNode(3).apply {
                left = IntTreeNode(2).apply {
                    left = IntTreeNode(1)
                }
                right = IntTreeNode(4)
            }
        }
        Assertions.assertEquals(false, task.bstSearch(tree, 6))
    }
}