package com.example.booksearch.network

import com.example.booksearch.model.BookSearchResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("search.json")
    suspend fun getBooksList(@Query("title") title: String) : ApiResponse<BookSearchResponse>
}