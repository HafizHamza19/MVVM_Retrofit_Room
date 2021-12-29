package com.bmcsolution.mvvmretrofitcoroutines.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bmcsolution.mvvmretrofitcoroutines.repository.QuotesRepository

class MainViewModelFactory(private val repository: QuotesRepository,private val pageNo:Int):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(repository = repository,pageNo = pageNo) as T
    }
}