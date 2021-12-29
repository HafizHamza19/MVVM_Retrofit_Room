package com.bmcsolution.mvvmretrofitcoroutines

import android.app.Application
import androidx.constraintlayout.widget.ConstraintSet
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.bmcsolution.mvvmretrofitcoroutines.apiClient.ApiHelper
import com.bmcsolution.mvvmretrofitcoroutines.apiClient.ApiInterface
import com.bmcsolution.mvvmretrofitcoroutines.repository.QuotesRepository
import com.bmcsolution.mvvmretrofitcoroutines.roomDb.RoomDb
import com.bmcsolution.mvvmretrofitcoroutines.workManger.QuotesWorker
import java.util.concurrent.TimeUnit

class ApplicationClass : Application() {


    lateinit var repository: QuotesRepository
    override fun onCreate() {
        super.onCreate()
        initialize()
        setupWorker()
    }

    private fun setupWorker() {
        val constraint= Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        val workerRequest=PeriodicWorkRequest.Builder(QuotesWorker::class.java,1,TimeUnit.SECONDS).setConstraints(constraint).build()
        WorkManager.getInstance(this).enqueue(workerRequest)
    }

    private fun initialize() {
        val apiService = ApiHelper.getInstance().create(ApiInterface::class.java)
        val roomDb = RoomDb.getInstance(applicationContext)
        repository = QuotesRepository(apiService, roomDb, applicationContext)
    }
}