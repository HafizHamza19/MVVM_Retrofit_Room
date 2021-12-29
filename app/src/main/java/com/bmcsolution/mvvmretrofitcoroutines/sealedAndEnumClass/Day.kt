package com.bmcsolution.mvvmretrofitcoroutines.sealedAndEnumClass

import android.util.Log


//Enum mai hum values ko restrict krrhe hai
//Set of constant values
//values is class ki instance hoti hai
//Single instance kisi ek ko use krte hai
enum class Day {
    SUNDAY,
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY
}

//Constructor
enum class Day1(val number:Int) {
    SUNDAY(1),
    MONDAY(2),
    TUESDAY(3),
    WEDNESDAY(4),
    THURSDAY(5),
    FRIDAY(6),
    SATURDAY(7);

    //Method

    fun printFormattedDay()
    {
        Log.d("Tag","Day is $this")
    }
}