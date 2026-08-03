package io.github.funnyphatguy.exchangerate.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.funnyphatguy.exchangerate.data.repository.CurrencyRepositoryImpl
import io.github.funnyphatguy.exchangerate.domain.repository.CurrencyRepository
import javax.inject.Singleton

class RepositoryModule {
    @Module
    @InstallIn(SingletonComponent::class)
    abstract class RepositoryModule {
        @Binds
        @Singleton
        abstract fun provideRepositry(
            repository: CurrencyRepositoryImpl
        ): CurrencyRepository
    }
}