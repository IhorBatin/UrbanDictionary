package com.example.urbandictionarynike.repository.local

import androidx.room.TypeConverter
import com.example.urbandictionarynike.extensions.toCalendar
import com.example.urbandictionarynike.extensions.toPrettyText
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.util.*

class Converters {

    // Creates Moshi adapter for List<String>
    private val stringListAdapter: JsonAdapter<List<String>> by lazy {
        Types.newParameterizedType(List::class.java, String::class.java)
            .let { Moshi.Builder().build().adapter<List<String>>(it) }
    }

    @TypeConverter
    fun fromServerTimeString(value: String): Calendar? = value.toCalendar()

    @TypeConverter
    fun fromCalendar(calendar: Calendar) = calendar.toPrettyText("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")

    @TypeConverter
    fun fromJsonString(json: String): List<String> = stringListAdapter.fromJson(json) ?: listOf()

    @TypeConverter
    fun fromStringList(strings: List<String>): String = stringListAdapter.toJson(strings)
}
