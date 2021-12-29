package com.bmcsolution.mvvmretrofitcoroutines.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bmcsolution.mvvmretrofitcoroutines.models.QuotesResponse
import com.bmcsolution.mvvmretrofitcoroutines.repository.QuotesRepository
import com.bmcsolution.mvvmretrofitcoroutines.sealedAndEnumClass.ResponseGeneric
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: QuotesRepository,private val pageNo:Int):ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO)
        {
            repository.getQuotes(page = pageNo)
        }
    }
    val quotes:LiveData<ResponseGeneric<QuotesResponse>>get() = repository.quotesLiveData
}