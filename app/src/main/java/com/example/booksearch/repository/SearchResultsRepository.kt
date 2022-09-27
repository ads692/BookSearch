package com.example.booksearch.repository

import com.example.booksearch.network.ApiService
import java.io.IOException
import javax.inject.Inject

class SearchResultsRepository @Inject constructor(private val apiService: ApiService) {
    @Throws(IOException::class)
    suspend fun getBooksList(title: String) = apiService.getBooksList(title)
}