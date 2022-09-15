package com.example.myapplication.masterDetailFragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class VMMasterDetail: ViewModel() {

    val listNumbers = List(20) { "Item $it" }

    val itemSelected = MutableLiveData("")

    fun clearItemSelected() {
        itemSelected.value = ""
    }


}