package com.hk.loodosassigment.data.source.remote

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

    override suspend fun getCoinList(): Result<List<Coin>> {
        return try {
            api.getCoins(token).let {response->
                if(response.isSuccessful){
                    when {
                        response.code()==200 ->
                            return Success(response.body()!!.data!!)
                        response.code()==401 -> {
                            api.refreshToken("token").let {tokenResult->
                                if(tokenResult.isSuccessful&&tokenResult.body()!=null){
                                    if(tokenResult.body()!!.successCode==0){
                                        token=tokenResult.body()!!.data!!
                                        return getCoinList()
                                    }else if(tokenResult.body()!!.successCode==1)
                                        return Error(Exception("Hatalı Kullanıcı Adı veya Şifre"))
                                    else
                                        return Error(Exception(tokenResult.body()!!.message))
                                }else
                                    return Error(Exception("Token Error"))
                            }
                        }
                        else ->
                            return Error(Exception("Unhandled http status code returned"))
                    }
                }else return Error(Exception("Error occured"))
            }
        } catch (cause: Exception) {
            return Error(cause)
        }
    }


}
