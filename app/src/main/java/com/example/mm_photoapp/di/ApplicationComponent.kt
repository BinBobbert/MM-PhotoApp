package com.example.mm_photoapp.di
import android.content.Context
import com.example.mm_photoapp.BaseApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Exists across the whole lifespan of the app
 */
@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class, ApplicationModule::class, AlbumModule::class, NetworkModule::class]
)
interface ApplicationComponent: AndroidInjector<BaseApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): ApplicationComponent
    }
}