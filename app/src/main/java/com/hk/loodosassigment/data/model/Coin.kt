package com.hk.loodosassigment.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Coin(
    @SerializedName("id")
    @Expose
    var id: Int?,
    @SerializedName("name")
    @Expose
    var countryName: String?,
    @SerializedName("capital")
    @Expose
    var capital: String?
)