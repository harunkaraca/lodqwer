package com.hk.loodosassigment.data.source

import com.hk.loodosassigment.data.model.Coin

interface BaseDataSource {
    suspend fun getCoinList(): Result<List<Coin>>
}