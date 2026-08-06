package io.github.funnyphatguy.exchangerate.common

import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Locale

private const val TIME_PATTERN = "HH:mm"

fun LocalTime.withoutMillis(): String = runCatching {
    val outputFormatter = DateTimeFormatter.ofPattern(TIME_PATTERN, Locale.getDefault())
    format(outputFormatter)
}.getOrNull() ?: "N/A"
