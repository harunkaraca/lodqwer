package com.hk.loodosassigment.main

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.work.CoroutineWorker
import androidx.work.ListenableWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.hk.loodosassigment.MyApplication
import com.hk.loodosassigment.data.BaseRepository
import com.hk.loodosassigment.data.source.BaseDataSource
import com.hk.loodosassigment.di.AppModule
import com.hk.loodosassigment.di.DaggerAppComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class CoinWorker (appContext: Context, workerParams: WorkerParameters)
    : CoroutineWorker(appContext, workerParams) {
    @Inject
    lateinit var repository: BaseRepository

    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
    private val TAG="CoinWorker"
    init {
        DaggerAppComponent.factory().create(applicationContext).inject(this)
    }
    override suspend fun doWork(): Result {
        Log.d(TAG, "CoinWorker is injected")
        repository.getCoinList()
        return Result.success()
    }
}