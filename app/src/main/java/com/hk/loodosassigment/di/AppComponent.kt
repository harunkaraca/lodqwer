package com.hk.loodosassigment.di

import android.content.Context
import android.content.SharedPreferences
import androidx.work.ListenableWorker
import com.example.mybaseproject.di.AppModuleBinds
import com.hk.loodosassigment.data.BaseRepository
import com.hk.loodosassigment.data.service.Api
import com.hk.loodosassigment.main.CoinWorker
import com.hk.loodosassigment.main.di.MainComponent
import dagger.*
import dagger.multibindings.IntoMap
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton
import kotlin.reflect.KClass

@Singleton
@Component(modules = [AppModule::class, AppModuleBinds::class,ViewModelBuilderModule::class,SubcomponentsModule::class])
interface AppComponent {

//    fun inject(app: MyApplication)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }

    fun mainComponent(): MainComponent.Factory
    fun inject(coinWorker: CoinWorker)

    val baseRepository: BaseRepository
    val context: Context
    val retrofit:Retrofit
    val okHttpClient: OkHttpClient
    val httpLoggingInterceptor: HttpLoggingInterceptor
    val sharedPreferences: SharedPreferences


}