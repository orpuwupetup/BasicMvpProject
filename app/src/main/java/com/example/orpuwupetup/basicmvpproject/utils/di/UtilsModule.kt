package com.example.orpuwupetup.basicmvpproject.utils.di

import android.content.Context
import com.example.orpuwupetup.basicmvpproject.utils.ui.DensityPixelsProvider
import com.example.orpuwupetup.basicmvpproject.utils.verifiers.CacheExpirationManager
import dagger.Module
import dagger.Provides

@Module
class UtilsModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        fun provideCacheExpirationManager(context: Context): CacheExpirationManager{
            return CacheExpirationManager(context)
        }

        @JvmStatic
        @Provides
        fun provideDensityPixelsProvider(context: Context): DensityPixelsProvider{
            return DensityPixelsProvider(context)
        }
    }
}