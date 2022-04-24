package com.hk.loodosassigment.main

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hk.loodosassigment.data.BaseRepository
import com.hk.loodosassigment.data.model.Coin
import com.hk.loodosassigment.data.source.Result
import com.hk.loodosassigment.data.source.Result.Success
import com.hk.loodosassigment.data.source.Result.Error
import com.hk.loodosassigment.data.source.Result.Loading
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel  @Inject constructor(private val baseRepository: BaseRepository,private val sharedPreferences: SharedPreferences):ViewModel() {

    private val _items = MutableLiveData<List<Coin>>().apply { value = emptyList() }
    val items: LiveData<List<Coin>> = _items

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _isDataLoadingError = MutableLiveData<Boolean>()
    val isDataLoadingError = _isDataLoadingError

   init {
//       loadCountry()
   }
    fun refresh(){
        loadCoins()
    }
    fun loadCoins(){
        Log.i("MainViewModel","run MainViewModel")
        viewModelScope.launch {
            val result=baseRepository.getCoinList()
            if(result is Success){
                _isDataLoadingError.value = false
                _items.value = result.data.value
            }else{
                _isDataLoadingError.value = false
                _items.value = emptyList()
            }
            _dataLoading.value = false
        }
    }
}