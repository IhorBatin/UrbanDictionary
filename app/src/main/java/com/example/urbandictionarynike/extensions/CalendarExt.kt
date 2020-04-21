package com.example.urbandictionarynike.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Calendar.toPrettyText(pattern: String = "yyyy-MM-dd"): String =
    SimpleDateFormat(pattern, Locale.getDefault()).format(time)
