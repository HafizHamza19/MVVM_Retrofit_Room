package com.bmcsolution.mvvmretrofitcoroutines

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bmcsolution.mvvmretrofitcoroutines.AlarmManager.AlarmService
import com.bmcsolution.mvvmretrofitcoroutines.ViewModel.MainViewModel
import com.bmcsolution.mvvmretrofitcoroutines.ViewModel.MainViewModelFactory
import com.bmcsolution.mvvmretrofitcoroutines.sealedAndEnumClass.ResponseGeneric


class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel
    val qoutesTxt: TextView get() = findViewById(R.id.quotesTxt)
    val nextButton: Button get() = findViewById(R.id.nextPage)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val repository = (application as ApplicationClass).repository

        mainViewModel = ViewModelProvider(this, MainViewModelFactory(repository, 1)).get(
            MainViewModel::class.java
        )
        mainViewModel.quotes.observe(this, Observer {
            when (it) {
                is ResponseGeneric.Error -> {
                    qoutesTxt.text = it.errorMessage.toString()
                }
                is ResponseGeneric.Loading -> {

                    Log.d("TagLoading", "Loading")
                }
                is ResponseGeneric.Success -> {
                    it.data?.let {
                        qoutesTxt.text = it.results.toString()
                        Log.d("Tag", it.results.toString())
                    }

                }
            }




        })

        var next=0
        nextButton.setOnClickListener {
            next++
            mainViewModel = ViewModelProvider(this, MainViewModelFactory(repository, next)).get(
                MainViewModel::class.java
            )
            mainViewModel.quotes.observe(this, Observer {
                when (it) {
                    is ResponseGeneric.Error -> {
                        qoutesTxt.text = it.errorMessage.toString()
                    }
                    is ResponseGeneric.Loading -> {


                    }
                    is ResponseGeneric.Success -> {
                        it.data?.let {
                            qoutesTxt.text = it.results.toString()
                            Log.d("Tag", it.results.toString())
                        }

                    }
                }
            })
        }
    }
}