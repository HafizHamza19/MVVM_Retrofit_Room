package com.bmcsolution.mvvmretrofitcoroutines.workManger

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.bmcsolution.mvvmretrofitcoroutines.ApplicationClass
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuotesWorker(private val context: Context,private val params:WorkerParameters):Worker(context,params) {
    override fun doWork(): Result {
        Log.d("WorkMangerTag","Work Start")
        val repository=(context as ApplicationClass).repository
        CoroutineScope(Dispatchers.IO).launch {
            repository.getQuotesInBackground(1)
        }
        Log.d("WorkMangerTag","Work Start")

        return Result.success()
    }
}