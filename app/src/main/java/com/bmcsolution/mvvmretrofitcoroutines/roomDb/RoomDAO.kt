package com.bmcsolution.mvvmretrofitcoroutines.roomDb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.bmcsolution.mvvmretrofitcoroutines.models.Result

@Dao
interface RoomDAO {
    @Insert
    suspend fun insertQuotes(quotes:List<Result>)

    @Query("DELETE FROM quotes")
    fun deleteQuotes()

    @Query("Select * from quotes")
    fun getQuotes():List<Result>
}