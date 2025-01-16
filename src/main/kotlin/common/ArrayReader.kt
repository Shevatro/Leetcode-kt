package common

class IntArrayReader(private val arr: IntArray) {
    fun get(index: Int): Int {
        return arr.getOrElse(index) { Int.MAX_VALUE }
    }
}