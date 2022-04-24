package com.hk.loodosassigment.data

import androidx.lifecycle.LiveData
import com.hk.loodosassigment.data.model.Coin
import com.hk.loodosassigment.di.AppModule
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import com.hk.loodosassigment.data.source.Result.Success
import com.hk.loodosassigment.data.source.BaseDataSource
import com.hk.loodosassigment.data.source.Result
import com.hk.loodosassigment.data.source.Result.Error
import timber.log.Timber
import java.lang.IllegalStateException
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap

class DefaultRepository @Inject constructor(
    @AppModule.LocalDataSource private val localDataSource: BaseDataSource,
    @AppModule.RemoteDataSource private val remoteDataSource: BaseDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO): BaseRepository {

    override suspend fun getCoinList(): Result<LiveData<List<Coin>>> {
        val coinList=remoteDataSource.getCoinList()
        when(coinList){
            is Error-> Timber.w("Remote data source fetch failed "+coinList.exception)
            is Success->{
                refreshCoinPrices(coinList.data.value)
                return coinList
            }
            else->throw IllegalStateException()
        }
        return Error(Exception("Error fetching from remote"))
    }

    private suspend fun refreshCoinPrices(value: List<Coin>?) {
        localDataSource.updateCoinPrices(value!!)
    }
}