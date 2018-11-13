package com.example.orpuwupetup.basicmvpproject.di

import android.content.Context
import com.example.orpuwupetup.basicmvpproject.BasicMVPProjectApplication
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {

    // module binding used for providing context through whole app
    @Binds
    internal abstract fun bindContext(application: BasicMVPProjectApplication): Context
}