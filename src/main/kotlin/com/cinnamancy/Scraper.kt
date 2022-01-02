package com.cinnamancy

import com.cinnamancy.client.Client
import com.cinnamancy.client.Page
import com.cinnamancy.client.ToyClient
import com.cinnamancy.extraction.Extraction
import com.cinnamancy.extraction.PageContents
import com.cinnamancy.extraction.ToyExtraction
import com.cinnamancy.repository.Repository
import com.cinnamancy.repository.ToyRepository
import kotlinx.coroutines.*
import mu.KotlinLogging

//  TODO: When this is done, try to make the suspend functions actually suspending
class Scraper(
    private val client: Client = ToyClient(delayMillis = 500, alwaysFindBody = "Test body"),
    private val extraction: Extraction = ToyExtraction(
        alwaysFindLinks = listOf("https://google.com", "https://google2.com"),
        alwaysFindPosts = listOf("Test post")
    ),
    private val repository: Repository = ToyRepository()
) {

    private val scope = CoroutineScope(Dispatchers.Default + SupervisorJob())
    private val seed = "https://google.com"
    private val log = KotlinLogging.logger {}

    fun start() = runBlocking(Dispatchers.Default) {
        log.info { "Scraping started!" }
        scrape(seed)
        log.info { "Scraping finished!" }
        delay(100_000)
    }

    private suspend fun scrape(address: String) = scope.launch {
        log.info { "A scraping coroutine has started" }
        val page = request(address)
        val contents = extract(page)
        process(contents)
        log.info { "A scraping coroutine has finished" }
    }

    private suspend fun request(address: String): Page {
        log.info { "Getting page: $address" }
        return client.get(address)
    }

    private suspend fun extract(page: Page): PageContents {
        log.info { "Extracting page: ${page.address}" }
        return extraction.extract(page)
    }

    private suspend fun process(contents: PageContents) {
        log.info { "Processing contents of: $contents" }
        repository.save(contents.posts)
        //  TODO: MAKE PARALLEL
        contents.links.forEach { scrape(it) }
    }
}