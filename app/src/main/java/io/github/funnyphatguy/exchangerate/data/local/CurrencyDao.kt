package io.github.funnyphatguy.exchangerate.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrencyDao {
    @Query("SELECT * FROM FAVORITES ORDER BY code")
    fun observeFavorites(): Flow<List<CurrencyDb>>

    @Upsert
    suspend fun insert(currency: CurrencyDb)

    @Query("DELETE FROM FAVORITES WHERE code = :code")
    suspend fun delete(code: String)
}