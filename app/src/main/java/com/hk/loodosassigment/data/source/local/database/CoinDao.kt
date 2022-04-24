package com.hk.loodosassigment.data.source.local.database
import androidx.lifecycle.LiveData
import androidx.room.*
import com.hk.loodosassigment.data.model.Coin

@Dao
interface CoinDao {

    @Query("Select * from coin")
    fun getCoinList(): LiveData<List<Coin>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoin(countryT: Coin)

    @Update
    suspend fun updateCoin(countryT: Coin):Int
}