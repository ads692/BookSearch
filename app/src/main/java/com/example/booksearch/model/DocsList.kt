package com.example.booksearch.model

data class DocsList(
    val isSuccess: Boolean = false,
    val books: List<Book> = emptyList(),
    val errorMsg: String? = null
)
