import dev.forkhandles.result4k.Result
import dev.forkhandles.result4k.Success
import dev.forkhandles.result4k.flatMap
import dev.forkhandles.result4k.zip
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import java.time.Instant

class BuilderService {

    suspend fun makeABuilding(): Result<Boolean, Nothing> {
        printlnWithTimeInMillis("Start of project")
        return layTheFoundations()
            .flatMap { buildTheWalls() }
            .flatMap {
                zipAsync(
                    { putInTheWindows() },
                    { putInTheDoors(1) },
                    { putOnTheRoof() }
                ) { _, _, _ -> true }
            }
    }

    private suspend fun layTheFoundations() = coroutineScope {
        delay(300)
        printlnWithTimeInMillis("we have a foundation!")
        Success(true)
    }

    private suspend fun buildTheWalls() = coroutineScope {
        delay(600)
        printlnWithTimeInMillis("The walls are up")
        Success(true)
    }

    private suspend fun putInTheWindows() = coroutineScope {
        delay(3000)
        printlnWithTimeInMillis("windows are in")
        Success(true)
    }

    private suspend fun putInTheDoors(numberOfDoors: Int) = coroutineScope {
        delay(3000)
        printlnWithTimeInMillis("doors are in")
        Success(true)
    }

    private suspend fun putOnTheRoof() = coroutineScope {
        delay(3000)
        printlnWithTimeInMillis("the roof is on")
        Success(true)
    }

    private fun printlnWithTimeInMillis(message: String) {
        println("${Instant.now().toEpochMilli() - START_TIME}:\t $message")
    }

    companion object {
        private val START_TIME = Instant.now().toEpochMilli()
    }
}

suspend inline fun <T1, T2, T3, U, E> zipAsync(
    crossinline r1: suspend () -> Result<T1, E>,
    crossinline r2: suspend () -> Result<T2, E>,
    crossinline r3: suspend () -> Result<T3, E>,
    crossinline transform: (T1, T2, T3) -> U
): Result<U, E> = coroutineScope {
    val d1 = async { r1() }
    val d2 = async { r2() }
    val d3 = async { r3() }
    zip(d1.await(), d2.await(), d3.await(), transform)
}
