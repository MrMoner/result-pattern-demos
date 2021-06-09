import dev.forkhandles.result4k.map
import dev.forkhandles.result4k.mapFailure

val greetingService = GreetingService()

fun main(args: Array<String>) {
    val result = greetingService.getGreeting(true)
        .map { "$it world!" }
        .mapFailure { Exception("We failed with a new exception") }

    println(result)
}

