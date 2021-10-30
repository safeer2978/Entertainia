package com.safeer.entertainia.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.safeer.entertainia.data.local.PostsDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
@InternalCoroutinesApi
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(application: Application) = PostsDatabase.getInstance(application)

    @Singleton
    @Provides
    fun providePostsDao(database: PostsDatabase) = database.getPostsDao()
}
