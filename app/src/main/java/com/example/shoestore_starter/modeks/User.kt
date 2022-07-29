package com.example.shoestore_starter.modeks

import android.os.Parcelable
import androidx.lifecycle.MutableLiveData
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Shoe(
    var name: String, var size: String, var company: String, var description: String

) : Parcelable
//val images: List<String> = mutableListOf()