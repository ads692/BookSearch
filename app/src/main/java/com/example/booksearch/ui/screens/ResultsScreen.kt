package com.example.booksearch.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.BookSearch.R
import com.example.booksearch.model.Book
import com.example.booksearch.viewmodel.ResultsScreenViewModel

@Composable
fun ResultsScreen(viewModel: ResultsScreenViewModel) {
    val searchResults by viewModel.booksList.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    if (searchResults.isSuccess && !isLoading) {
        if (searchResults.books.isEmpty()) {
            Text(text = stringResource(R.string.no_books_found_message))
        } else {
            LazyColumn(
                contentPadding = PaddingValues(all = 15.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(items = searchResults.books, key = { it.key }) { item ->
                    BookRowItem(item)
                }
            }
        }
    } else if (!isLoading) {
        Text(text = "${searchResults.errorMsg}")
    } else {
        Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
}

@Composable
private fun BookRowItem(book: Book) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        BookCardItem(book)
    }
}

@Composable
private fun BookCardItem(book: Book) {
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier.fillMaxWidth().background(Color.LightGray)
    ) {
        BookDetails(book)
    }
}

@Composable
private fun BookDetails(book: Book) {
    Column(modifier = Modifier
        .fillMaxWidth()
    ) {
        val author = if (book.author_name?.isEmpty()?.not() == true) {
            "by ${book.author_name.joinToString()}"
        } else ""
        Text("${book.title} $author")
    }
}