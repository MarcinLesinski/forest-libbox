package io.stud.forest

sealed class Try<out T> {
    abstract fun isSuccess(): Boolean
    abstract fun isFailure(): Boolean
    abstract fun get(): T

    companion object {
        operator fun <T> invoke(body: () -> T): Try<T> {
            return try {
                Success(body())
            } catch (e: Exception) {
                Failure(e)
            }
        }
    }
}

data class Success<out T>(val value: T) : Try<T>() {
    override fun isSuccess(): Boolean = true
    override fun isFailure(): Boolean = false
    override fun get(): T = value
}

data class Failure<out T>(val error: Throwable) : Try<T>() {
    override fun isSuccess(): Boolean = false
    override fun isFailure(): Boolean = true
    override fun get(): T = throw error
}
