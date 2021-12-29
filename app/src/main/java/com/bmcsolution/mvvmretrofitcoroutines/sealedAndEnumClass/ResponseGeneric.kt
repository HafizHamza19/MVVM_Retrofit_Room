package com.bmcsolution.mvvmretrofitcoroutines.sealedAndEnumClass

import com.bmcsolution.mvvmretrofitcoroutines.models.QuotesResponse

//Isme hum type ko restrict krte hai
//Instance multiple bna skte hai or state bhi change hoti hai
//or ye predictable hai

// this is not generic
/*sealed class ResponseGeneric(val data:QuotesResponse?=null,val errorMessage: String?=null){
    class Loading:ResponseGeneric()
    class Success(response:QuotesResponse):ResponseGeneric(data = response)
    class Error( errorMessage:String):ResponseGeneric(errorMessage = errorMessage)
}*/


//this is generic
//error handling
sealed class ResponseGeneric<T>(val data:T?=null,val errorMessage: String?=null){
    class Loading<T>:ResponseGeneric<T>()
    class Success<T>(data: T?):ResponseGeneric<T>(data = data)
    class Error<T>( errorMessage:String):ResponseGeneric<T>(errorMessage = errorMessage)
}
