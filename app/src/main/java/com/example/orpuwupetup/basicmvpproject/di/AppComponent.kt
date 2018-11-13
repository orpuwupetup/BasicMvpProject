package com.example.orpuwupetup.basicmvpproject.di

import com.example.orpuwupetup.basicmvpproject.BasicMVPProjectApplication
import com.example.orpuwupetup.basicmvpproject.api.di.NetModule
import com.example.orpuwupetup.basicmvpproject.data.di.MyObjectRepositoryModule
import com.example.orpuwupetup.basicmvpproject.data.di.RoomModule
import com.example.orpuwupetup.basicmvpproject.utils.di.UtilsModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, AndroidSupportInjectionModule::class, RoomModule::class, NetModule::class, ActivityBindingModule::class, UtilsModule::class
,MyObjectRepositoryModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: BasicMVPProjectApplication): Builder

        fun build(): AppComponent
    }

    fun inject(application: BasicMVPProjectApplication)
}