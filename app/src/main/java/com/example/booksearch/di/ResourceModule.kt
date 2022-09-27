package com.example.booksearch.di

import android.content.Context
import androidx.annotation.StringRes
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
class ResourcesProvider @Inject constructor(
    @ApplicationContext private val context: Context
) {
    @Singleton
    fun getString(@StringRes stringResId: Int): String {
        return context.getString(stringResId)
    }
}