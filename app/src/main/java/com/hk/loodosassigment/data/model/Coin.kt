package com.hk.loodosassigment.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
@Entity(tableName = "coin")
data class Coin(
    @PrimaryKey @ColumnInfo(name = "id")
    @SerializedName("id")
    @Expose
    var id: Int?,
    @ColumnInfo(name = "name")
    @SerializedName("name")
    @Expose
    var name: String?,
    @ColumnInfo(name = "price")
    @SerializedName("price")
    @Expose
    var price: String?
)