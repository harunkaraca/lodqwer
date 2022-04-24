package com.hk.loodosassigment.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hk.loodosassigment.data.model.Coin
import com.hk.loodosassigment.data.service.Api
import com.hk.loodosassigment.data.source.BaseDataSource
import com.hk.loodosassigment.data.source.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import com.hk.loodosassigment.data.source.Result.Success
import com.hk.loodosassigment.data.source.Result.Error
class RemoteDataSource internal constructor(private val api: Api,private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO) : BaseDataSource {
    companion object{
        var token=""
    }

    override suspend fun getCoinList(): Result<LiveData<List<Coin>>> {
        var data = MutableLiveData<List<Coin>>()
        try {
            api.getCoins(token).let {response->
                if(response.isSuccessful){
                    data.value=response.body()!!.data!!
//                    localDataSource.updateCoins(response.body()!!.data!!)
                    return Success(data)
                }else return Error(Exception("Error occured"))
            }
        } catch (cause: Exception) {
            return Error(cause)
        }
    }

    override suspend fun updateCoinPrices(coins: List<Coin>) {
        TODO("Not yet implemented")
    }


}
