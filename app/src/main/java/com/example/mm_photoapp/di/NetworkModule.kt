package com.example.mm_photoapp.di

import android.provider.ContactsContract
import com.example.mm_photoapp.data.network.PhotoAPI
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

/**
 * Module that injects dependencies used in the network layer
 */
@Module
object NetworkModule{

    val BASE_URL = "https://jsonplaceholder.typicode.com"

    @JvmStatic
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit{
        return Retrofit.Builder()
               .baseUrl(BASE_URL)
               .addConverterFactory(GsonConverterFactory.create())
               .build()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideRetrofitService(retrofit: Retrofit): PhotoAPI{
        return retrofit.create(PhotoAPI::class.java)
    }


}

