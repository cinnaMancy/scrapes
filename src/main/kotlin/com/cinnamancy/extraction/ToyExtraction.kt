package com.cinnamancy.extraction

import com.cinnamancy.client.Page
import mu.KotlinLogging

class ToyExtraction(
    private val alwaysFindLinks: List<String> = listOf(),
    private val alwaysFindPosts: List<Any> = listOf()
) : Extraction {

    private val log = KotlinLogging.logger {}

    override suspend fun extract(page: Page): PageContents {
        log.info { "Extracted ${alwaysFindLinks.size} link(s): $alwaysFindLinks" }
        log.info { "Extracted ${alwaysFindPosts.size} element(s): $alwaysFindPosts" }
        return PageContents(links = alwaysFindLinks, posts = alwaysFindPosts)
    }

}
