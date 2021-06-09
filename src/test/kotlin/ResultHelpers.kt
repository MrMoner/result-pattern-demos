import dev.forkhandles.result4k.Failure
import dev.forkhandles.result4k.Result
import dev.forkhandles.result4k.Success
import kotlin.test.fail

inline fun <T, E> Result<T, E>.assertSuccess(f: (T) -> Unit) {
    when (this) {
        is Success<T> -> f(value)
        is Failure<E> -> fail("Expected \"$this\" to be Success but was actually a Failure")
    }
}

inline fun <T, E> Result<T, E>.assertFailure(f: (E) -> Unit) {
    when (this) {
        is Success<T> -> fail("Expected \"$this\" to be Failure but was actually a Success")
        is Failure<E> -> f(reason)
    }
}