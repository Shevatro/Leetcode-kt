package bctci.arrays

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class DynamicArrayExtras {
    private var arr = IntArray(10)
    private var curInd = 0
    fun append(item: Int) {
        if (arr.size == curInd) {
            increaseSpace()
        }
        arr[curInd] = item
        curInd++
    }

    fun insert(i: Int, item: Int) {
        if (i !in 0..curInd) throw IndexOutOfBoundsException("It should be in 0 .. $curInd range")
        if (i == curInd) {
            append(item)
            return
        } else {
            if (arr.size == curInd) {
                increaseSpace()
            }
            var oldItem = arr[i]
            arr[i] = item
            curInd++

            for (index in i + 1 until curInd) {
                val temp = arr[index]
                arr[index] = oldItem
                oldItem = temp
            }
        }
    }

    fun remove(item: Int): Int {
        val index = indexOf(item)
        if (index != -1) pop(index)
        return index
    }

    private fun indexOf(item: Int): Int {
        for (i in 0 until curInd) {
            if (arr[i] == item) return i
        }
        return -1
    }

    fun get(i: Int): Int {
        if (i in 0 until curInd) return arr[i]
        throw IndexOutOfBoundsException("It should be in 0 .. $curInd range")
    }

    fun set(i: Int, x: Int) {
        if (i !in 0..curInd) throw IndexOutOfBoundsException("It should be in 0 .. $curInd range")
        arr[i] = x
    }

    fun size() = curInd

    fun contains(item: Int): Boolean {
        for (i in 0 until curInd) {
            if (arr[i] == item) return true
        }
        return false
    }


    fun popBack(): Int? {
        if (curInd == 0) return null
        val item = arr[curInd - 1]
        curInd--
        arr[curInd] = 0
        if (arr.size / 4 >= curInd) decreaseSpace()
        return item
    }

    fun pop(i: Int): Int? {
        if (i !in 0 until curInd) throw IndexOutOfBoundsException("It should be in 0 .. $curInd range")
        if (i == curInd - 1) {
            return popBack()
        } else {
            val item = arr[i]
            for (index in i until curInd - 1) {
                arr[index] = arr[index + 1]
            }
            popBack()
            return item
        }
    }

    private fun increaseSpace() {
        arr = arr.copyOf(arr.size * 2)
    }

    private fun decreaseSpace() {
        arr = arr.copyOf(arr.size / 2)
    }
}

private class DynamicArrayExtrasTest {

    @Test
    fun test1() {
        val d = DynamicArrayExtras().apply {
            append(1)
            append(2)
            append(3)
        }
        Assertions.assertEquals(2, d.pop(1))
        Assertions.assertEquals(3, d.get(1))
        Assertions.assertEquals(2, d.size())
    }

    @Test
    fun test2() {
        val d = DynamicArrayExtras().apply {
            append(1)
            append(2)
        }
        Assertions.assertEquals(true, d.contains(1))
        Assertions.assertEquals(false, d.contains(3))
    }

    @Test
    fun test3() {
        val d = DynamicArrayExtras().apply {
            append(1)
            append(2)
            insert(1, 3)
        }
        Assertions.assertEquals(3, d.get(1))
        Assertions.assertEquals(3, d.size())
    }

    @Test
    fun test4() {
        val d = DynamicArrayExtras().apply {
            append(1)
            append(2)
            append(2)
        }
        Assertions.assertEquals(1, d.remove(2))
        Assertions.assertEquals(2, d.get(1))
    }
}