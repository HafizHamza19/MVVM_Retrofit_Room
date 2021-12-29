package com.bmcsolution.mvvmretrofitcoroutines.models

data class QuotesResponse(
    val count: Int,
    val lastItemIndex: Int,
    val page: Int,
    val results: List<Result>,
    val totalCount: Int,
    val totalPages: Int
)