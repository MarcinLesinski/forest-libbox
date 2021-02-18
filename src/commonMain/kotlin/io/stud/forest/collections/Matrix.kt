package io.stud.forest.collections

typealias Matrix<T> = Array<Array<T>>

inline fun <reified T> Matrix(width: Int, height: Int, initialValue: T): Matrix<T> {
    val initCell = {_: Int -> initialValue }
    val initRow: (Int) -> Array<T> = { i: Int -> Array(width, initCell) }
    return Matrix(height, initRow)
}

val <T> Matrix<T>.height: Int
    get() = this.size

val <T> Matrix<T>.width: Int
    get() {
        return if (this.height > 1)
            this[0].size
        else
            0
    }

operator fun <T> Matrix<T>.get(x: Int, y: Int): T = this[y][x]

fun <T> Matrix<T>.toText(): String{
    var result = ""
    this.forEach {
        result += '\n';
        it.forEach {
            result += it.toString() + " "
        }
    }
    return result
}