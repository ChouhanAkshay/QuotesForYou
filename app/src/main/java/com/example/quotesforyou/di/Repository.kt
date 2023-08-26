package com.example.quotesforyou.di

import com.example.quotesforyou.data.repositoryImpl.MainRepositoryImpl
import com.example.quotesforyou.domain.repository.MainRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class Repository {

    @Binds
    @Singleton
    abstract fun bindMainRepository(mainRepoImpl : MainRepositoryImpl) : MainRepository
}