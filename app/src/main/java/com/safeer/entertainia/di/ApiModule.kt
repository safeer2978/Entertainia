package com.safeer.entertainia.di



import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.safeer.entertainia.data.remote.api.Service
import retrofit2.Retrofit
import javax.inject.Singleton

import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
class ApiModule {

    @Singleton
    @Provides
    fun provideRetrofitService(): Service = Retrofit.Builder()
        .baseUrl(Service.FOODIUM_API_URL)
        //.addConverterFactory()\
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(Service::class.java)
}
