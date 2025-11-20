package bctci.trees

import common.TreeNode
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Task35_2 {
    fun decodeHiddenMsg(root: TreeNode<String>?): String {
        return Solution(root).decodeHiddenMsg()
    }

    class Solution(private val root: TreeNode<String>?) {
        private var str = ""
        fun decodeHiddenMsg(): String {
            dfs(root)
            return str
        }

        private fun dfs(node: TreeNode<String>?) {
            if (node == null) return
            when (node.`val`[0]) {
                'b' -> {
                    str += node.`val`[1]
                    dfs(node.left)
                    dfs(node.right)
                }

                'a' -> {
                    dfs(node.left)
                    dfs(node.right)
                    str += node.`val`[1]
                }

                'i' -> {
                    dfs(node.left)
                    str += node.`val`[1]
                    dfs(node.right)
                }
                else -> throw IllegalArgumentException("Not supported character")
            }
        }
    }
}


private class Task35_2Test {
    private val task = Task35_2()

    @Test
    fun test1() {
        val tree = TreeNode("bn").apply {
            left = TreeNode("i_").apply {
                left = TreeNode("ae").apply {
                    left = TreeNode("bi")
                    right = TreeNode("bc")
                }
                right = TreeNode("it")
            }
            right = TreeNode("a!").apply {
                left = TreeNode("br").apply {
                    right = TreeNode("ay")
                }
            }
        }
        Assertions.assertEquals("nice_try!", task.decodeHiddenMsg(tree))
    }

    @Test
    fun test2() {
        Assertions.assertEquals("", task.decodeHiddenMsg(null))
    }

    @Test
    fun test3() {
        val tree = TreeNode("bx")
        Assertions.assertEquals("x", task.decodeHiddenMsg(tree))
    }

    @Test
    fun test4() {
        val tree = TreeNode("ix")
        Assertions.assertEquals("x", task.decodeHiddenMsg(tree))
    }

    @Test
    fun test5() {
        val tree = TreeNode("ax")
        Assertions.assertEquals("x", task.decodeHiddenMsg(tree))
    }

    @Test
    fun test6() {
        val tree = TreeNode("b1").apply {
            left = TreeNode("b2").apply {
                left = TreeNode("b4")
                right = TreeNode("b5")
            }
            right = TreeNode("b3").apply {
                left = TreeNode("b6")
                right = TreeNode("b7")
            }
        }
        Assertions.assertEquals("1245367", task.decodeHiddenMsg(tree))
    }

    @Test
    fun test7() {
        val tree = TreeNode("i1").apply {
            left = TreeNode("i2").apply {
                left = TreeNode("i4")
                right = TreeNode("i5")
            }
            right = TreeNode("i3").apply {
                left = TreeNode("i6")
                right = TreeNode("i7")
            }
        }
        Assertions.assertEquals("4251637", task.decodeHiddenMsg(tree))
    }

    @Test
    fun test8() {
        val tree = TreeNode("a1").apply {
            left = TreeNode("a2").apply {
                left = TreeNode("a4")
                right = TreeNode("a5")
            }
            right = TreeNode("a3").apply {
                left = TreeNode("a6")
                right = TreeNode("a7")
            }
        }
        Assertions.assertEquals("4526731", task.decodeHiddenMsg(tree))
    }

    @Test
    fun test9() {
        val tree = TreeNode("bh").apply {
            left = TreeNode("be").apply {
                left = TreeNode("bl")
                right = TreeNode("il")
            }
            right = TreeNode("ao")
        }
        Assertions.assertEquals("hello", task.decodeHiddenMsg(tree))
    }
}