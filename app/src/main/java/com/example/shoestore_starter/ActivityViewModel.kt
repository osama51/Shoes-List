package com.example.shoestore_starter

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoestore_starter.modeks.Shoe

class ActivityViewModel : ViewModel() {

    val flag = MutableLiveData<Boolean>(false)
    val name = MutableLiveData<String>()
    val company = MutableLiveData<String>()
    val size = MutableLiveData<String>()
    val description = MutableLiveData<String>()

    var shoesList: MutableList<Shoe>

    init {
        Log.i("ShoeDetailViewModel", "ShoeDetailViewModel Created")
        shoesList = ArrayList()
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("ShoeDetailViewModel", "ShoeDetailViewModel destroyed")
    }

    fun addShoe() {
        shoesList.add(
            Shoe(
                name.value ?: "",
                size.value ?: "",
                company.value ?: "",
                description.value ?: ""
            )
        )
    }
}