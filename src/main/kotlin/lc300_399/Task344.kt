package lc300_399

//Task was changed, repeat
//https://leetcode.com/problems/reverse-string/
class Task344 {
    fun reverseString(s: String): String {
        return StringBuilder(s).reverse().toString()
    }
}

fun main() {
    println(Task344().reverseString("hello"))
}