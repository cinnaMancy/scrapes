package com.cinnamancy.client

interface Client {

    suspend fun get(address: String): Page
}