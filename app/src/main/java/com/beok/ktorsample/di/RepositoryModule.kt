package com.beok.ktorsample.di

import com.beok.ktorsample.data.GithubRepository
import com.beok.ktorsample.data.GithubService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesGithubRepository(service: GithubService): GithubRepository =
        GithubRepository(service = service)
}
