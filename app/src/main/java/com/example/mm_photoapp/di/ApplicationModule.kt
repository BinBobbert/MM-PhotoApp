package com.example.mm_photoapp.di

import com.example.mm_photoapp.data.network.NetworkDataSource
import com.example.mm_photoapp.data.network.NetworkDataSourceImpl
import com.example.mm_photoapp.data.network.PhotoAPI
import com.example.mm_photoapp.data.repository.PhotoAlbumRepo
import com.example.mm_photoapp.data.repository.PhotoAlbumRepoImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Qualifier
import javax.inject.Singleton

/**
 *
 */
@Module(includes = [ApplicationModuleBinds::class])
object ApplicationModule{

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class NetworkDataSourceImpl

    @JvmStatic
    @Singleton
    @NetworkDataSourceImpl
    @Provides
    fun provideNetworkDataSourceImpl(client: PhotoAPI): NetworkDataSource{
        return NetworkDataSourceImpl(client)
    }

}

@Module
abstract class ApplicationModuleBinds{
    @Singleton
    @Binds
    abstract fun bindRepository(repo: PhotoAlbumRepoImpl): PhotoAlbumRepo

}