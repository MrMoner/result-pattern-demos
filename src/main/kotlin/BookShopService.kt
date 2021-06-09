import dev.forkhandles.result4k.*

class BookShopService {
    private val bookService = BookService()
    private val stockService = StockService()

    fun charactersInBookName(id: Int) =
        bookService.getName(id)
            .map { it.count() }

    fun booksWithStock() =
        stockService.getBooksWithStock(1)
            .map { ids -> ids.map { bookService.getName(it) } }
            .map { "Found books with stock: [${it.allValues()}]" }

    fun bookAndStock(id: Int) =
        zip(
            bookService.getName(id),
            stockService.getStockCount(id)
        ) { name, count -> "The book '$name' has a stock count of $count" }

    class BookService {
        fun getName(id: Int) = resultFrom {
            NAMES[id] ?: throw Exception("No name found for $id")
        }

        companion object {
            val NAMES = mapOf(
                1 to "Effective Java",
                2 to "Clean Code",
                3 to "Quarkus Cookbook",
                4 to "Kubernetes Best Practices",
                5 to "Atomic Kotlin"
            )
        }
    }

    class StockService {
        fun getStockCount(id: Int) = resultFrom {
            STOCK.getOrDefault(id, 0)
        }

        fun getBooksWithStock(stockLimit: Int) = resultFrom {
            STOCK.filter { it.value >= stockLimit }.keys.ifEmpty {
                throw Exception("No books found with at least $stockLimit in stock")
            }
        }

        companion object {
            val STOCK = mapOf(
                1 to 10,
                2 to 25,
                3 to 0,
                4 to 7
            )
        }
    }
}