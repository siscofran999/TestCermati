package com.siscofran.testcermati.di

import com.siscofran.testcermati.ui.MainActivity
import com.siscofran.testcermati.ui.MainActivityModule
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.ContributesAndroidInjector

@Module(includes = [AndroidInjectionModule::class])
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

}