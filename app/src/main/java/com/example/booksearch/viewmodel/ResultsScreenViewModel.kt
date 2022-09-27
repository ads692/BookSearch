package com.example.booksearch.viewmodel

import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.*
import com.example.BookSearch.R
import com.example.booksearch.di.ResourcesProvider
import com.example.booksearch.model.DocsList
import com.example.booksearch.repository.SearchResultsRepository
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultsScreenViewModel @Inject constructor(
    private val searchResultsRepository: SearchResultsRepository,
    private val resourcesProvider: ResourcesProvider,
    savedStateHandle: SavedStateHandle) : ViewModel() {

    private val _booksList: MutableStateFlow<DocsList> = MutableStateFlow(
        DocsList()
    )
    val booksList: StateFlow<DocsList>
        get() = _booksList

    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean>
        get() = _isLoading

    private val _title: String? = savedStateHandle["title"]

    private val _tag: String = "ResultsScreenViewModel"

    init {
        _title?.let { getBooks(_title) }
    }

    @WorkerThread
    fun getBooks(title: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.emit(true)
            val response = searchResultsRepository.getBooksList(title)
            response.onSuccess {
                _isLoading.tryEmit(false)
                _booksList.value = DocsList(
                    isSuccess = true,
                    books = this.data.docs
                )
            }.onError {
                _isLoading.tryEmit(false)
                _booksList.value = DocsList(
                    isSuccess = false,
                    errorMsg = resourcesProvider.getString(R.string.book_loading_error)
                )
            }.onException {
                _isLoading.tryEmit(false)
                Log.e(_tag, "${resourcesProvider.getString(R.string.books_list_exception)}, $exception")
                _booksList.value = DocsList(
                    isSuccess = false,
                    errorMsg = resourcesProvider.getString(R.string.books_list_exception)
                )
            }
        }
    }
}