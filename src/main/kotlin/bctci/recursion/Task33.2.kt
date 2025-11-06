package bctci.recursion

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@Suppress("UNCHECKED_CAST")
class Task33_2() {

    fun calculateNestedArraySum(arr: List<Any>): Int {
        var sum = 0
        for (item in arr) {
            sum += item as? Int ?: calculateNestedArraySum(item as List<Int>)
        }
        return sum
    }

    fun calculateNestedArraySumLazy(arr: List<Any>): Int {
        return calculateNestedArraySumLazyRec(arr)
    }

    fun calculateNestedArraySumLazyRec(item: Any): Int {
        if (item is Int) return item
        var sum = 0
        for (item in item as List<Any>) {
            sum += calculateNestedArraySumLazyRec(item)
        }
        return sum
    }
}


private class Task33_2Test {
    private val task = Task33_2()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input: List<Any>, expected: Int) {
        Assertions.assertEquals(expected, task.calculateNestedArraySumLazy(input))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(listOf(1, listOf(2, 3), listOf(4, listOf(5)), 6), 21),
                Arguments.of(listOf(listOf(listOf(listOf(1)), 2)), 3),
                Arguments.of(listOf<Any>(), 0),
                Arguments.of(listOf(listOf<Any>(), listOf(1, 2), listOf(), listOf(3)), 6),
                Arguments.of(listOf(-1, listOf(-2, 3), listOf(4, listOf(-5)), 6), 5),
                Arguments.of(listOf(0, listOf(0, listOf(0, listOf(0))), 0), 0)
            )
        }
    }
}