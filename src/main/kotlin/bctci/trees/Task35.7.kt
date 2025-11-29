package bctci.trees

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.math.max
import kotlin.math.min

class Task35_7 {
    fun evaluateExpressionTree(root: Node): Int {
        return dfs(root)
    }

    private fun dfs(node: Node): Int {
        if (node.kind == "num") return requireNotNull(node.num)
        var accValue = getDefaultValue(node.kind)
        for (child in node.children) {
            accValue = performOperation(accValue, node.kind, dfs(child))
        }
        return accValue
    }

    private fun getDefaultValue(kind: String): Int {
        return when (kind) {
            "sum" -> 0
            "product" -> 1
            "max" -> Int.MIN_VALUE
            "min" -> Int.MAX_VALUE
            else -> throw IllegalArgumentException("Unsupported operation")
        }
    }

    private fun performOperation(accValue: Int, nodeKind: String, childValue: Int): Int {
        return when (nodeKind) {
            "sum" -> accValue + childValue
            "product" -> accValue * childValue
            "max" -> max(accValue, childValue)
            "min" -> min(accValue, childValue)
            else -> throw IllegalArgumentException("Unsupported operation")
        }
    }

    //kind = "num", "sum", "product" (*), "max", or "min"
    class Node(
        val kind: String,
        val num: Int? = null,
        val children: List<Node> = emptyList()
    )
}

private class Task35_7Test {
    private val task = Task35_7()

    @Test
    fun test1() {
        val tree = Task35_7.Node(
            "min",
            children = listOf(
                Task35_7.Node(
                    "max",
                    children = listOf(
                        Task35_7.Node("num", num = 4),
                        Task35_7.Node("num", num = 6),
                        Task35_7.Node(
                            "sum",
                            children = listOf(
                                Task35_7.Node("num", num = 5),
                                Task35_7.Node("num", num = 7),
                            )
                        )
                    )
                ),
                Task35_7.Node(
                    "sum",
                    children = listOf(
                        Task35_7.Node(
                            "product",
                            children = listOf(
                                Task35_7.Node("num", num = 6),
                                Task35_7.Node("num", num = 8),
                            )
                        )
                    )
                )
            )
        )
        Assertions.assertEquals(12, task.evaluateExpressionTree(tree))
    }

    @Test
    fun test2() {
        //(2 + 3) * 4
        val tree = Task35_7.Node(
            "product",
            children = listOf(
                Task35_7.Node(
                    "sum",
                    children = listOf(
                        Task35_7.Node("num", num = 2),
                        Task35_7.Node("num", num = 3)
                    )
                ),
                Task35_7.Node("num", num = 4)
            )
        )
        Assertions.assertEquals(20, task.evaluateExpressionTree(tree))
    }

    @Test
    fun test3() {
        val tree = Task35_7.Node("num", num = 5)
        Assertions.assertEquals(5, task.evaluateExpressionTree(tree))
    }

    @Test
    fun test4() {
        val tree = Task35_7.Node("sum", children = emptyList())
        Assertions.assertEquals(0, task.evaluateExpressionTree(tree))
    }

    @Test
    fun test5() {
        val tree = Task35_7.Node("product", children = emptyList())
        Assertions.assertEquals(1, task.evaluateExpressionTree(tree))
    }

    @Test
    fun test6() {
        // min(2, max(3,4)) + product(1,2,3)
        val tree = Task35_7.Node(
            "sum",
            children = listOf(
                Task35_7.Node(
                    "min",
                    children = listOf(
                        Task35_7.Node("num", num = 2),
                        Task35_7.Node(
                            "max",
                            children = listOf(
                                Task35_7.Node("num", num = 3),
                                Task35_7.Node("num", num = 4),
                            )
                        )
                    )
                ),
                Task35_7.Node(
                    "product",
                    children = listOf(
                        Task35_7.Node("num", num = 1),
                        Task35_7.Node("num", num = 2),
                        Task35_7.Node("num", num = 3)
                    )
                )
            )
        )
        Assertions.assertEquals(8, task.evaluateExpressionTree(tree))
    }

    @Test
    fun test7() {
        val tree = Task35_7.Node(
            "max",
            children = listOf(
                Task35_7.Node("num", num = 5),
                Task35_7.Node("num", num = 3),
                Task35_7.Node("num", num = 8)
            )
        )
        Assertions.assertEquals(8, task.evaluateExpressionTree(tree))
    }
}