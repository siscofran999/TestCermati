package com.siscofran.testcermati.di

import com.siscofran.testcermati.data.ApiRepository
import com.siscofran.testcermati.data.GithubRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindsApiRepository(repository: GithubRepository): ApiRepository
}