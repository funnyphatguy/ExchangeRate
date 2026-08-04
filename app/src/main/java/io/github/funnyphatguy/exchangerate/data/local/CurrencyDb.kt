package io.github.funnyphatguy.exchangerate.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FAVORITES")
data class CurrencyDb(
    @PrimaryKey
    val code: String,
    val name: String,
    val rateInRubles: String,
) {
}