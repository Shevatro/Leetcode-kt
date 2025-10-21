package bctci.arrays

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class DynamicArray() {
    private var arr = IntArray(10)
    private var curInd = 0
    fun append(item: Int) {
        if (arr.size == curInd) {
            increaseSpace()
        }
        arr[curInd] = item
        curInd++
    }

    fun get(i: Int): Int {
        return arr[i]
    }

    fun set(i: Int, x: Int) {
        arr[i] = x
    }

    fun size() = curInd


    fun popBack(): Int? {
        if (curInd == 0) return null
        val item = arr[curInd - 1]
        curInd--
        arr[curInd] = 0
        if (arr.size / 4 >= curInd) decreaseSpace()
        return item
    }

    private fun increaseSpace() {
        arr = arr.copyOf(arr.size * 2)
    }

    private fun decreaseSpace() {
        arr = arr.copyOf(arr.size / 2)
    }
}

private class DynamicArrayTest {

    @Test
    fun test1() {
        val d = DynamicArray().apply {
            append(1)
            append(2)
        }
        Assertions.assertEquals(1, d.get(0))
        Assertions.assertEquals(2, d.get(1))
        Assertions.assertEquals(2, d.size())
    }

    @Test
    fun test2() {
        val d = DynamicArray().apply {
            append(1)
            set(0, 10)
        }
        Assertions.assertEquals(10, d.get(0))
    }

    @Test
    fun test3() {
        val d = DynamicArray().apply {
            append(1)
            append(2)
        }
        Assertions.assertEquals(2, d.popBack())
        Assertions.assertEquals(1, d.size())
        Assertions.assertEquals(1, d.get(0))
    }
}