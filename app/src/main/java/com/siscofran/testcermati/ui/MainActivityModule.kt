package com.siscofran.testcermati.ui

import android.app.Application
import com.siscofran.testcermati.data.ApiRepository
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    fun provideMainViewModel(apiRepository: ApiRepository, application: Application): MainViewModel {
        return MainViewModel(apiRepository, application)
    }

}