package com.cinnamancy.extraction

data class PageContents(
    val links: List<String>,
    val posts: List<Any>
) {
    companion object {
        fun empty(): PageContents {
            return PageContents(links = listOf(), posts=listOf())
        }
    }
}
