package com.hk.loodosassigment.di

import android.content.Context
import android.content.SharedPreferences
import com.example.mybaseproject.di.AppModuleBinds
import com.hk.loodosassigment.data.BaseRepository
import com.hk.loodosassigment.data.service.Api
import com.hk.loodosassigment.main.di.MainComponent
import dagger.BindsInstance
import dagger.Component
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, AppModuleBinds::class,ViewModelBuilderModule::class,SubcomponentsModule::class])
interface AppComponent {

//    fun inject(app: MyApplication)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }

    fun mainComponent(): MainComponent.Factory

    val retrofit:Retrofit
    val okHttpClient: OkHttpClient
    val httpLoggingInterceptor: HttpLoggingInterceptor
    val baseRepository: BaseRepository
    val sharedPreferences: SharedPreferences

}