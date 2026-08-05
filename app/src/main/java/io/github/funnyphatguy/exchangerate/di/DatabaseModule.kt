package io.github.funnyphatguy.exchangerate.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.funnyphatguy.exchangerate.data.database.AppDatabase
import io.github.funnyphatguy.exchangerate.data.database.CurrencyDao
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, AppDatabase.DATABASE_NAME)
            .build()
    }

    @Provides
    fun provideCurrencyDao(
        database: AppDatabase
    ): CurrencyDao {
        return database.currencyDao()
    }
}