package com.siscofran.testcermati.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.siscofran.testcermati.ViewModelProviderFactory
import com.siscofran.testcermati.ui.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

//    @Binds
//    abstract fun bindViewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory
}
