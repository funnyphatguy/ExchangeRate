package io.github.funnyphatguy.exchangerate.common

import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Locale

fun LocalTime.withoutMillis(): String {
    val outputFormatter = DateTimeFormatter.ofPattern("HH:mm", Locale.getDefault())
    return try {
        format(outputFormatter)
    } catch (e: Exception) {
        "N/A"
    }
}