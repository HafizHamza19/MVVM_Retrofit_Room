package com.bmcsolution.mvvmretrofitcoroutines.roomDb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bmcsolution.mvvmretrofitcoroutines.models.Result


@Database(entities = [Result::class], version = 1)

abstract class RoomDb : RoomDatabase() {
    abstract fun roomDao(): RoomDAO

    companion object {
        @Volatile
        private var INSTANCE:RoomDb? = null
       fun getInstance(context: Context):RoomDb
       {
           if (INSTANCE==null)
           {
               synchronized(this)
               {
                   INSTANCE=Room.databaseBuilder(context,RoomDb::class.java,"QuotesDb").build()
               }
           }
           return INSTANCE!!
       }

    }
}