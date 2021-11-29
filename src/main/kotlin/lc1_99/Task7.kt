package lc1_99

class Task7 {
    fun reverse(x: Int): Int {
        val num = Math.abs(x).toString().reversed().toIntOrNull() ?: return 0
        return if (x > 0) num else num * (-1)
    }
}

fun main() {
    println(Task7().reverse(123))
    println(Task7().reverse(-123))
    println(Task7().reverse(120))
    println(Task7().reverse(0))

}