package com.bmcsolution.mvvmretrofitcoroutines.repository

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bmcsolution.mvvmretrofitcoroutines.apiClient.ApiHelper
import com.bmcsolution.mvvmretrofitcoroutines.apiClient.ApiInterface
import com.bmcsolution.mvvmretrofitcoroutines.models.QuotesResponse
import com.bmcsolution.mvvmretrofitcoroutines.roomDb.RoomDb
import com.bmcsolution.mvvmretrofitcoroutines.sealedAndEnumClass.ResponseGeneric
import com.bmcsolution.mvvmretrofitcoroutines.utils.NetworkUtils
import kotlin.random.Random

class QuotesRepository(
    private val apiService: ApiInterface,
    private val roomDb: RoomDb,
    private val applicationContext: Context
) {

    var quotesMutableData = MutableLiveData<ResponseGeneric<QuotesResponse>>()

    val quotesLiveData: LiveData<ResponseGeneric<QuotesResponse>> get() = quotesMutableData

    suspend fun getQuotes(page: Int) {

        try {
            if (NetworkUtils.isInternetAvailable() && NetworkUtils.isNetworkAvailable(applicationContext))
            {
                quotesMutableData.postValue(ResponseGeneric.Loading())
                val result = apiService.getQuotes(pageNo = page)
                if (result.isSuccessful && result?.body() != null)
                {
                    roomDb.roomDao().deleteQuotes()
                    roomDb.roomDao().insertQuotes(result.body()!!.results)
                    quotesMutableData.postValue(ResponseGeneric.Success(result.body()))
                }
                else
                {
                    quotesMutableData.postValue(ResponseGeneric.Error("API Error"))
                }
            }
            else
            {
                val quotesData = roomDb.roomDao().getQuotes()
                val quotesResponse = QuotesResponse(1, 1, 1, quotesData, 1, 1)
                quotesMutableData.postValue(ResponseGeneric.Success(quotesResponse))
            }
        }
        catch (e: Exception)
        {
            quotesMutableData.postValue(ResponseGeneric.Error(e.message.toString()))
            Log.d("Exception", e.message.toString())
        }
    }


    suspend fun getQuotesInBackground()
    {
        val random= (Math.random()*10).toInt()
        val result = apiService.getQuotes(pageNo = random)
        if (result.isSuccessful && result?.body() != null)
        {
            //roomDb.roomDao().deleteQuotes()
            roomDb.roomDao().insertQuotes(result.body()!!.results)
            quotesMutableData.postValue(ResponseGeneric.Success(result.body()))
        }
    }


}