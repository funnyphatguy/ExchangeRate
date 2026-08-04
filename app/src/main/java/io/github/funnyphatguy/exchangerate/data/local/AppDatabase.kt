package io.github.funnyphatguy.exchangerate.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [CurrencyDb::class], version = 1, exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun currencyDao(): CurrencyDao

    companion object {
        const val DATABASE_NAME = "CurrencyDatabase"
    }
}


