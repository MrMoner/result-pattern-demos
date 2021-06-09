import dev.forkhandles.result4k.Failure
import dev.forkhandles.result4k.Result
import dev.forkhandles.result4k.Success

class GreetingService {
    fun getGreeting(shouldSucceed: Boolean) = if (shouldSucceed) getSuccess() else getFailure()

    private fun getSuccess(): Result<String, Exception> = Success("Hello")
    private fun getFailure(): Result<String, Exception> = Failure(Exception("Bang"))
}