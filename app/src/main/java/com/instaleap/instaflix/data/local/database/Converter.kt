package com.instaleap.instaflix.data.local.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converter {

    @TypeConverter
    fun fromList(value : List<Int>): String {
        val type = object: TypeToken<List<Int>>() {}.type
        return Gson().toJson(value, type)
    }

    @TypeConverter
    fun toList(value: String): List<Int> {
        val type = object: TypeToken<List<Int>>() {}.type
        return Gson().fromJson(value, type)
    }

}