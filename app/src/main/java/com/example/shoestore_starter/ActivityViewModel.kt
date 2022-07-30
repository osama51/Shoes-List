package com.example.shoestore_starter

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoestore_starter.modeks.Shoe

class ActivityViewModel : ViewModel() {

    //    private val _newShoe = MutableLiveData<Shoe>()
//    private val _shoesList = MutableLiveData<MutableList<Shoe>>()
    val flag = MutableLiveData<Boolean>(false)
    val name = MutableLiveData<String>()
    val company = MutableLiveData<String>()
    val size = MutableLiveData<String>()
    val description = MutableLiveData<String>()

    val shoesList = MutableLiveData<MutableList<Shoe>>()
//    val shoesList: LiveData<MutableList<Shoe>>
//        get() = _shoesList
//    val newShoe: LiveData<Shoe>
//        get() = _newShoe

    init {
        Log.i("ShoeDetailViewModel", "ShoeDetailViewModel Created")
        shoesList.value = ArrayList()
//        _shoesList.value = mutableListOf<Shoe>()
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("ShoeDetailViewModel", "ShoeDetailViewModel destroyed")
    }

    fun addShoe() {
        shoesList.value?.add(
            Shoe(
                name.value ?: "",
                size.value ?: "",
                company.value ?: "",
                description.value ?: ""
            )
        )
    }
}