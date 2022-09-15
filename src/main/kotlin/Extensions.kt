fun IntArray.toString2(): String {
    return joinToString(prefix = "[", postfix = "]", separator = " ")
}

fun <T>Array<T>.toString2(): String {
    return joinToString(prefix = "[", postfix = "]", separator = " ")
}

fun CharArray.toString2(): String {
    return joinToString(prefix = "[", postfix = "]", separator = " ")
}