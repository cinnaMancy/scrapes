package com.cinnamancy.repository

import mu.KotlinLogging

class ToyRepository : Repository {

    private val log = KotlinLogging.logger {}

    override suspend fun save(item: Any) {
        log.info { "Saved item: $item" }
    }

    override suspend fun save(items: Collection<Any>) {
        log.info { "Saved (${items.size}) items: $items"}
    }
}
