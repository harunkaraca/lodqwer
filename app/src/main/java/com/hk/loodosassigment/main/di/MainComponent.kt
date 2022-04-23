package com.hk.loodosassigment.main.di

import com.hk.loodosassigment.data.service.Api
import com.hk.loodosassigment.main.MainActivity
import com.hk.loodosassigment.main.MainFragment
import dagger.Subcomponent

@Subcomponent(modules = [MainModule::class])
interface MainComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): MainComponent
    }
    fun inject(fragment: MainFragment)

}