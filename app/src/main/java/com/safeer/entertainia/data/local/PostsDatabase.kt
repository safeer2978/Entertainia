package com.safeer.entertainia.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.safeer.entertainia.data.local.dao.PostsDao
import com.safeer.entertainia.model.Post
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized


@Database(entities = [Post::class],
version = 1)
abstract class PostsDatabase : RoomDatabase() {

    /**
     * @return [PostsDao] Foodium Posts Data Access Object.
     */
    abstract fun getPostsDao(): PostsDao

    @InternalCoroutinesApi
    companion object {
        const val DB_NAME = "asdasdas_database"

        @Volatile
        private var INSTANCE: PostsDatabase? = null
        fun getInstance(context: Context): PostsDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PostsDatabase::class.java,
                    DB_NAME
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}
