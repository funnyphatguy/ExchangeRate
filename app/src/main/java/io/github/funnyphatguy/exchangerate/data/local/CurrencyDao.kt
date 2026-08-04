package io.github.funnyphatguy.exchangerate.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrencyDao {
    @Query("SELECT * FROM FAVORITES ORDER BY code")
    fun observeFavorites(): Flow<List<CurrencyDb>>

    @Insert
    suspend fun insert(currency: CurrencyDb)

    @Query("DELETE FROM FAVORITES WHERE code = :code")
    suspend fun delete(code: String)
}