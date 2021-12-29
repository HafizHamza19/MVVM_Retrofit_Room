package com.bmcsolution.mvvmretrofitcoroutines.apiClient

import com.bmcsolution.mvvmretrofitcoroutines.models.QuotesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("/quotes")
    suspend fun getQuotes(@Query("page")pageNo:Int): Response<QuotesResponse>
}