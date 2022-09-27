package com.example.BookSearch.network

import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.SandwichInitializer
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Response

@RunWith(JUnit4::class)
class ApiResponseTest {

    @Test
    fun exception() {
        val exception = Exception("exception found")
        val apiResponse = ApiResponse.error<String>(exception)
        MatcherAssert.assertThat(apiResponse.message, CoreMatchers.`is`("exception found"))
    }

    @Test
    fun success() {
        val apiResponse =
            ApiResponse.of(SandwichInitializer.successCodeRange) { Response.success("Api response was successful!") }
        if (apiResponse is ApiResponse.Success) {
            MatcherAssert.assertThat(apiResponse.data, CoreMatchers.`is`("Api response was successful!"))
        }
    }
}
