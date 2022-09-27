package com.example.booksearch.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.example.booksearch.di.ResourcesProvider
import com.example.booksearch.model.BookSearchResponse
import com.example.booksearch.repository.SearchResultsRepository
import com.example.booksearch.utils.TestCoroutineRule
import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ResultsScreenViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private var searchResultsRepository = mock(SearchResultsRepository::class.java)
    private var resourcesProvider = mock(ResourcesProvider::class.java)
    private var savedStateHandle = mock(SavedStateHandle::class.java)

    private lateinit var viewModel: ResultsScreenViewModel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        viewModel = ResultsScreenViewModel(searchResultsRepository, resourcesProvider, savedStateHandle)
    }

    @Test
    fun testResponseOnSuccess() = runTest {
        `when`(searchResultsRepository.getBooksList("test"))
            .thenReturn(ApiResponse.of { Response.success((BookSearchResponse(emptyList()))) })
        viewModel.getBooks("test")
        assert(viewModel.booksList.value.books.isEmpty())
    }
}