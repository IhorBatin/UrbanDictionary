package com.example.urbandictionarynike.extensions

import java.text.SimpleDateFormat
import java.util.*

const val DEFAULT_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"

/**
 * Converts server string to Calendar object or null if not possible
 *
 * @param inputPattern This is the pattern of the timestamp to be converted
 */
fun String.toCalendar(inputPattern: String = DEFAULT_PATTERN): Calendar? {
    val sdf = SimpleDateFormat(inputPattern, Locale.getDefault())
    val date = try {
        sdf.parse(this)
    } catch (e: Exception) {
        null
    }
    return when (date) {
        null -> null
        else -> Calendar.getInstance().apply { time = date }
    }
}