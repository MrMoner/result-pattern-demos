import dev.forkhandles.result4k.valueOrNull
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals

internal class BuilderServiceTest {

    private val builderService = BuilderService()

    @Test
    fun makeABuilding() = runBlocking {
        val makeABuilding = builderService.makeABuilding()
        assertEquals(true, makeABuilding.valueOrNull())
    }
}