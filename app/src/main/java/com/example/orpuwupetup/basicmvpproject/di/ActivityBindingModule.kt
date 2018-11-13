package com.example.orpuwupetup.basicmvpproject.di

import com.example.orpuwupetup.basicmvpproject.activities.example.ExampleActivity
import com.example.orpuwupetup.basicmvpproject.activities.example.ExampleActivityModule
import com.example.orpuwupetup.basicmvpproject.di.annotations.ActivityScoped
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    // TODO add here bindings for all the activities added to the application

    @ActivityScoped
    @ContributesAndroidInjector(modules = [(ExampleActivityModule::class)])
    internal abstract fun bindsExampleActivity(): ExampleActivity

}