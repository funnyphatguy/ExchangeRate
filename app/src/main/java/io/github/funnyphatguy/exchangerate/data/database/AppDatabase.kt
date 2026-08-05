package io.github.funnyphatguy.exchangerate.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [CurrencyEntity::class], version = 1, exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {

    abstract fun currencyDao(): CurrencyDao

    companion object {
        const val DATABASE_NAME = "CurrencyDatabase"
    }
}


