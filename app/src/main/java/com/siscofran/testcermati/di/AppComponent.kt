package com.siscofran.testcermati.di

import android.app.Application
import com.siscofran.testcermati.TestCermatiApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class, ActivityModule::class, ViewModelModule::class, ApiModule::class, RepositoryModule::class])
interface AppComponent : AndroidInjector<DaggerApplication> {

    fun inject(app: TestCermatiApp)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
}
