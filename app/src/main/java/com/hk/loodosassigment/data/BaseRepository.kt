package com.hk.loodosassigment.data

import com.hk.loodosassigment.data.model.Coin
import com.hk.loodosassigment.data.source.Result
interface BaseRepository {
    suspend fun getCoinList(): Result<List<Coin>>
}