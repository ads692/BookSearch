package com.example.booksearch.di

import com.example.booksearch.network.ApiService
import com.example.booksearch.repository.SearchResultsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
    @Provides
    @ViewModelScoped
    fun provideSearchResultsRepository(
        apiService: ApiService
    ): SearchResultsRepository {
        return SearchResultsRepository(apiService)
    }
}