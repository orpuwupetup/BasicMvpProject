package com.example.orpuwupetup.basicmvpproject.data.di

import android.arch.persistence.room.Room
import com.example.orpuwupetup.basicmvpproject.BasicMVPProjectApplication
import com.example.orpuwupetup.basicmvpproject.data.source.local.BasicDatabase
import com.example.orpuwupetup.basicmvpproject.utils.executors.AppExecutors
import com.example.orpuwupetup.basicmvpproject.utils.executors.DiskIOThreadExecutor
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
class RoomModule {

    private val numberOfThreads = 3

    @Singleton
    @Provides
    fun provideDb(app: BasicMVPProjectApplication): BasicDatabase {
        return Room
            .databaseBuilder(app, BasicDatabase::class.java, "BasicDatabase.db")

                // every time the database version is changed, all tables will be dropped because of this next line
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    internal fun provideAppExecutors(): AppExecutors {
        return AppExecutors(
            DiskIOThreadExecutor(),
            Executors.newFixedThreadPool(numberOfThreads),
            AppExecutors.MainThreadExecutor())
    }
}