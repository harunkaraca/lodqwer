package com.hk.loodosassigment.data.source.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.hk.loodosassigment.data.model.Coin
import com.hk.loodosassigment.data.source.BaseDataSource
import com.hk.loodosassigment.data.source.Result
import com.hk.loodosassigment.data.source.local.database.CoinDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalDataSource internal constructor(
    private val coinDao: CoinDao,
    private val ioDispatcher:CoroutineDispatcher = Dispatchers.IO
) : BaseDataSource {

    suspend fun saveCoin(coin: Coin) {
        coinDao.insertCoin(coin)
    }

    override suspend fun getCoinList(): Result<LiveData<List<Coin>>> = withContext(ioDispatcher) {
        return@withContext try {
            Result.Success(coinDao.getCoinList())
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun updateCoinPrices(coins: List<Coin>) {
        for(coin in coins){
            coinDao.updateCoin(coin)
        }
    }

}