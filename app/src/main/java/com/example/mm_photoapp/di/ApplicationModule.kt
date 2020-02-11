package com.example.mm_photoapp.di

import android.content.Context
import androidx.room.Room
import com.example.mm_photoapp.data.db.AppDatabase
import com.example.mm_photoapp.data.network.NetworkDataSource
import com.example.mm_photoapp.data.network.NetworkDataSourceImpl
import com.example.mm_photoapp.data.network.PhotoAPI
import com.example.mm_photoapp.data.repository.PhotoAlbumRepo
import com.example.mm_photoapp.data.repository.PhotoAlbumRepoImpl
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
    annotation class NetworkDataSourceInj

    @JvmStatic
    @Singleton
    @Provides
    fun providePhotoAlbumRepoImpl(
        db: AppDatabase,
        dataSource: NetworkDataSourceImpl
    ): PhotoAlbumRepo {
        return PhotoAlbumRepoImpl(dataSource, db.albumDao())
    }

    @JvmStatic
    @Singleton
    @NetworkDataSourceInj
    @Provides
    fun provideNetworkDataSourceImpl(client: PhotoAPI): NetworkDataSource{
        return NetworkDataSourceImpl(client)
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideDataBase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "PhotoAlbum.db"
        ).build()
    }
}

@Module
abstract class ApplicationModuleBinds{

}