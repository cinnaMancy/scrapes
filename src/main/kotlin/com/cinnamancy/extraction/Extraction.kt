package com.cinnamancy.extraction

import com.cinnamancy.client.Page

interface Extraction {

    suspend fun extract(page: Page): PageContents
}
