package com.example.mybaseproject.di

import com.hk.loodosassigment.data.BaseRepository
import com.hk.loodosassigment.data.DefaultRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class AppModuleBinds {

    @Singleton
    @Binds
    abstract fun bindRepository(repo: DefaultRepository): BaseRepository
}
