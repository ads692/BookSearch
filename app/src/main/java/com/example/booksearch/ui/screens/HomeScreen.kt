package com.example.booksearch.ui.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.BookSearch.R

@Composable
fun HomeScreen(
    onButtonClick: (String) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val title = enterTitle()
            val context = LocalContext.current
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                buttonClick(context, title, onButtonClick)
            }) {
                Text(stringResource(R.string.find_books))
            }
        }
    }
}

@Composable
private fun enterTitle(): String {
    var title by rememberSaveable { mutableStateOf("") }
    OutlinedTextField(
        value = title,
        onValueChange = { title = it },
        label = { Text(stringResource(R.string.label_search_books)) },
        placeholder = { Text(stringResource(R.string.placeholder_search_books)) }
    )
    return title
}

private fun buttonClick(context: Context, title: String, onButtonClick: (String) -> Unit) {
    when {
        title.isNotEmpty() -> onButtonClick(title)
        else -> Toast.makeText(context, context.getString(R.string.empty_string_toast_message), Toast.LENGTH_SHORT).show()
    }
}