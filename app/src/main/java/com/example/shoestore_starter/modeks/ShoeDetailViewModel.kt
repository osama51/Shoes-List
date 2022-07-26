package com.example.shoestore_starter.modeks

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShoeDetailViewModel: ViewModel() {

    val newShoe = MutableLiveData<Shoe>()
    val flag = MutableLiveData<Boolean>()

    lateinit var shoesList: MutableLiveData<List<Shoe>>

    init{
        Log.i("ShoeDetailViewModel", "ShoeDetailViewModel Created")

    }

    override fun onCleared() {
        super.onCleared()
        Log.i("ShoeDetailViewModel", "ShoeDetailViewModel destroyed")
    }

    fun addShoe(name: String, size: Double, company: String, description: String){
        newShoe.value = Shoe(name, size, company, description)
//        shoesList.value?.plus(newShoe.value)
    }
}