package com.siscofran.testcermati.di

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.siscofran.testcermati.ViewModelProviderFactory
import com.siscofran.testcermati.data.ApiService
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
abstract class AppModule {

    @Binds
    abstract fun bindContext(application: Application): Context

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory
}