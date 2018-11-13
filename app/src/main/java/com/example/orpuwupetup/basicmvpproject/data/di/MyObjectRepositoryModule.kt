package com.example.orpuwupetup.basicmvpproject.data.di

import com.example.orpuwupetup.basicmvpproject.api.BasicApiService
import com.example.orpuwupetup.basicmvpproject.data.source.MyObjectDataSource
import com.example.orpuwupetup.basicmvpproject.data.source.local.BasicDatabase
import com.example.orpuwupetup.basicmvpproject.data.source.local.Local
import com.example.orpuwupetup.basicmvpproject.data.source.local.MyObjectLocalDataSource
import com.example.orpuwupetup.basicmvpproject.data.source.local.dao.MyObjectDao
import com.example.orpuwupetup.basicmvpproject.data.source.remote.MyObjectRemoteDataSource
import com.example.orpuwupetup.basicmvpproject.data.source.remote.Remote
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MyObjectRepositoryModule {

    @Singleton
    @Provides
    @Remote
    internal fun provideMyObjectRemoteDataSource(basicApiService: BasicApiService): MyObjectDataSource {
        return MyObjectRemoteDataSource(basicApiService)
    }

    @Singleton
    @Provides
    @Local
    internal fun provideMyObjectLocalDataSource(dao: MyObjectDao): MyObjectDataSource{
        return MyObjectLocalDataSource(dao)
    }

    @Singleton
    @Provides
    internal fun provideMyObjectDao(database: BasicDatabase): MyObjectDao {
        return database.myObjectDao()
    }
}