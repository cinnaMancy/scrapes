package com.cinnamancy.repository

interface Repository {

    suspend fun save(item: Any)
    suspend fun save(items: Collection<Any>)
}
