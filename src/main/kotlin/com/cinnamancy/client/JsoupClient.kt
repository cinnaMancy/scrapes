package com.cinnamancy.client

import mu.KotlinLogging
import org.jsoup.Jsoup

class JsoupClient : Client {

    private val log = KotlinLogging.logger {}

    override suspend fun get(address: String): Page {
        log.info { "Requesting page $address" }
        val jsoupPage = Jsoup.connect(address).ignoreContentType(true).get()
        log.info { "Received page $address" }
        return Page(address = jsoupPage.location(), body = jsoupPage.body().html())
    }
}