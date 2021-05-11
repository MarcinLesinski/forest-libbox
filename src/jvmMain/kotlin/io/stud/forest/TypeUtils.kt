package io.stud.forest

import java.math.BigInteger

//region "BigInteger"
operator fun BigInteger.times(value: Int): BigInteger {
    val valueAsBigInteger = BigInteger.valueOf(value.toLong())
    return this.multiply(valueAsBigInteger)
}
//endregion

//region "List<T>"
fun <T> List<T>.multiplyBy(factor: (T) -> Long): Long {
    var result = 1L
    for (item in this) {
        result *= factor(item)
    }
    return result
}

//region List<Int>
fun List<Int>.multiply(): BigInteger {
    var result = BigInteger.valueOf(1)
    this.forEach { result *= it }
    return result
}
//endregion

//region List<Int>
fun List<Long>.unique(): Boolean {
    return size == distinct().size
}

fun List<Long>.sumsTo(value: Long): Boolean {
    return sum() == value
}
//endregion
//endregion

//region Array<T>
fun <T> Array<T>.multiplyBy(factor: (T) -> Long): Long {
    var result = 1L
    for (item in this) {
        result *= factor(item)
    }
    return result
}

//endregion

//region Iterable<T>
inline fun <T> Iterable<T>.sumByLong(selector: (T) -> Long): Long {
    var sum = 0L
    for (element in this) {
        sum += selector(element)
    }
    return sum
}

inline fun Iterable<Long>.sum(): Long {
    var sum = 0L
    for (element in this) {
        sum += element
    }
    return sum
}
//endregion

