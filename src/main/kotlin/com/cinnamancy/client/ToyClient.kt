package com.cinnamancy.client

import kotlinx.coroutines.delay
import mu.KotlinLogging

class ToyClient(
    private val delayMillis: Long = 0L,
    private val alwaysFindBody: String = ""
) : Client {

    private val log = KotlinLogging.logger {}

    override suspend fun get(address: String): Page {
        delay(delayMillis)
        log.info { "Received page from $address after ${delayMillis}ms" }
        return Page(address = address, body = alwaysFindBody)
    }
}