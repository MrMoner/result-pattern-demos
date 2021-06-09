import kotlin.test.Test
import kotlin.test.assertEquals

internal class GreetingServiceTest {
    private val greetingService = GreetingService()

    @Test
    fun `can get a success of string`() {
        val result = greetingService.getGreeting(true)

        result.assertSuccess {
            assertEquals("Hello", it)
        }
    }

    @Test
    fun `can get a failure`() {
        val result = greetingService.getGreeting(false)

        result.assertFailure {
            assertEquals("Bang", it.localizedMessage)
        }
    }
}