package com.hk.loodosassigment.data

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
    @AppModule.RemoteDataSource private val remoteDataSource: BaseDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO): BaseRepository {
    private var cachedCountries: ConcurrentMap<Int,Coin>?=null


    override suspend fun getCoinList(): Result<List<Coin>> {
        val coinList=remoteDataSource.getCoinList()
        when(coinList){
            is Error-> Timber.w("Remote data source fetch failed "+coinList.exception)
            is Success->{
                return coinList
            }
            else->throw IllegalStateException()
        }
        return Error(Exception("Error fetching from remote"))
    }

    private fun refreshCache(tasks: List<Coin>) {
        cachedCountries?.clear()
        tasks.sortedBy { it.id }.forEach {
            cacheAndPerform(it) {}
        }
    }
    private inline fun cacheAndPerform(country: Coin, perform: (Coin) -> Unit) {
        val cachedCountry = cacheCountry(country)
        perform(cachedCountry)
    }
    private fun cacheCountry(country: Coin): Coin {
        val cachedCountry = Coin(country.id,country.countryName,country.capital)
        // Create if it doesn't exist.
        if (cachedCountries == null) {
            cachedCountries = ConcurrentHashMap()
        }
        cachedCountries?.put(cachedCountry.id, cachedCountry)
        return cachedCountry
    }



}