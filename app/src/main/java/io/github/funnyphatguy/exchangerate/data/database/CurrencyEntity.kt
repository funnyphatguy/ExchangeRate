package io.github.funnyphatguy.exchangerate.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FAVORITES")
data class CurrencyEntity(
    @PrimaryKey
    val code: String,
    val name: String,
    val rateInRubles: String,
)