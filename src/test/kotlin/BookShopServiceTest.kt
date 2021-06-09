import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

internal class BookShopServiceTest {

    private val bookShopService = BookShopService()

    @Test
    fun `get number of characters in book name`() {
        val result = bookShopService.charactersInBookName(1)
        result.assertSuccess {
            assertEquals(Int::class, it::class)
            assertEquals(14, it)
        }
    }

    @Test
    fun `failure on characters in book where unknown book`() {
        val result = bookShopService.charactersInBookName(0)
        result.assertFailure {
            assertEquals(Exception::class, it::class)
            assertEquals("No name found for 0", it.localizedMessage)
        }
    }

    @Test
    fun `get books with stock`() {
        val result = bookShopService.booksWithStock()
        result.assertSuccess {
            assertNotNull(it)
            assertEquals(String::class, it::class)
            assertEquals(
                "Found books with stock: [Success(value=[Effective Java, Clean Code, Kubernetes Best Practices])]",
                it
            )
        }
    }

    @Test
    fun `get book name and associated stock`() {
        val result = bookShopService.bookAndStock(1)
        result.assertSuccess {
            assertNotNull(it)
            assertEquals(String::class, it::class)
            assertEquals("The book 'Effective Java' has a stock count of 10", it)
        }
    }

    @Test
    fun `get book name and associated stock where stock is unknown`() {
        val result = bookShopService.bookAndStock(5)
        result.assertSuccess {
            assertNotNull(it)
            assertEquals(String::class, it::class)
            assertEquals("The book 'Atomic Kotlin' has a stock count of 0", it)
        }
    }

    @Test
    fun `failure on book name and associated stock where unknown book`() {
        val result = bookShopService.bookAndStock(0)
        result.assertFailure {
            assertNotNull(it)
            assertEquals("No name found for 0", it.localizedMessage)
        }
    }
}
