package com.cinnamancy.extraction

import com.cinnamancy.client.Page

class SingleStrategyExtraction(
    private val handler: (Page) -> PageContents = { PageContents.empty() }
) : Extraction {

    override suspend fun extract(page: Page): PageContents {
        return handler.invoke(page)
    }
}