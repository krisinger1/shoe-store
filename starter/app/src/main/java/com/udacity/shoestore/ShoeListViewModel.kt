package com.udacity.shoestore

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import timber.log.Timber


class ShoeListViewModel : ViewModel(){

    private val _shoeList = MutableLiveData<MutableList<Shoe>>()
    val shoeList : LiveData<MutableList<Shoe>>
        get()=_shoeList


    private val _eventGoToAddShoe = MutableLiveData<Boolean>()
    val eventGoToAddShoe : LiveData<Boolean>
        get()=_eventGoToAddShoe


    private val _newShoe = MutableLiveData<Shoe>()
    val newShoe :LiveData<Shoe>
        get()=_newShoe

    init {
        Timber.i("in init block")
        _shoeList.value = mutableListOf(
                Shoe("name1", 5.0, "Nike", "describe shoe 1"),
                Shoe("name2", 6.0, "Addidas", "describe shoe 2"),
                Shoe("name3", 7.0, "Champion", "describe shoe 3"),
                Shoe("name4", 8.0, "Avia", "describe shoe 4"))

        _eventGoToAddShoe.value = false

        //_newShoe.value = Shoe("name",0.0,"brand","description")
        Timber.i("new Shoe created: ${_newShoe.value}")
    }


    public fun onGoToAddShoe(){

        _eventGoToAddShoe.value=true
        Timber.i("Add shoe?"+_eventGoToAddShoe.value)
    }

    public fun onGoToAddShoeComplete(){
        _eventGoToAddShoe.value=false

    }

    public fun addShoeToList(name : String, size : Double=0.0, company:String ="", description: String = ""){
        Timber.i("in addShoeToList")
        _newShoe.value=Shoe(name, size, company, description)
        // add shoe to list
        _shoeList.value?.add(_newShoe.value!!)
        //
    }
}