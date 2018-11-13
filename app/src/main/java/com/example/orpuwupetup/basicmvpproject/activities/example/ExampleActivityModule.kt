package com.example.orpuwupetup.basicmvpproject.activities.example

import com.example.orpuwupetup.basicmvpproject.di.annotations.ActivityScoped
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
abstract class ExampleActivityModule {

    @Binds
    @ActivityScoped
    internal abstract fun provideExamplePresenter(presenter: ExamplePresenter): ExampleContract.Presenter

    @Module
    companion object {

        @JvmStatic
        @Provides
        @ActivityScoped
        fun provideCompositeDisposable(): CompositeDisposable {
            return CompositeDisposable()
        }
    }
}