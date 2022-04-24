package com.hk.loodosassigment.data.source

import androidx.lifecycle.LiveData
import com.hk.loodosassigment.data.model.Coin

interface BaseDataSource {
    suspend fun getCoinList(): Result<LiveData<List<Coin>>>
    suspend fun updateCoinPrices(coins:List<Coin>)
}