package com.hk.loodosassigment.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hk.loodosassigment.data.model.Coin

@Database(entities = [Coin::class], version = 1, exportSchema = false)
abstract class MyDatabase : RoomDatabase() {

    abstract fun coinDao(): CoinDao
}