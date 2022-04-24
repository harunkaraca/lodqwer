package com.hk.loodosassigment.data.service

import com.hk.loodosassigment.data.model.Coin
import com.example.mybaseproject.data.model.BaseApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface Api {
    @GET("/refreshToken")
    suspend fun refreshToken(@Header("x-token") xToken: String): Response<BaseApiResponse<String>>

    @GET("/harunkaraca/mockdata/main/mockdatacrypto.json")
    suspend fun getCoins(@Header("token") token:String):Response<BaseApiResponse<List<Coin>>>
}