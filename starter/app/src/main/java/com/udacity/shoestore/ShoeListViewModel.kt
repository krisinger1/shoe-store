package com.udacity.shoestore

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeListViewModel : ViewModel(){

    private val _shoeList = MutableLiveData<MutableList<Shoe>>()
    val shoeList : LiveData<MutableList<Shoe>>
        get()=_shoeList


    private val _eventGoToAddShoe = MutableLiveData<Boolean>()
    val eventGoToAddShoe : LiveData<Boolean>
        get()=_eventGoToAddShoe

    init{
        Log.i("ShoeListViewModel", "in init block")
        _shoeList.value = mutableListOf(
            Shoe("name1",5.0,"Nike", "describe shoe 1"),
            Shoe("name2",6.0,"Addidas", "describe shoe 2"),
            Shoe("name3",7.0,"Champion", "describe shoe 3"),
            Shoe("name4",8.0,"Avia", "describe shoe 4"))

        _eventGoToAddShoe.value=false
    }


    public fun onGoToAddShoe(){

        _eventGoToAddShoe.value=true
        Log.i("ShoeListViewModel", "Add shoe?"+_eventGoToAddShoe)
    }

    public fun onGoToAddShoeComplete(){
        _eventGoToAddShoe.value=false
    }

    public fun addShoeToList(name : String, size : Double=0.0, company:String ="", description: String = ""){
        val tempList = mutableListOf<Shoe>()
        _shoeList.value?.toCollection(tempList) as Collection<Shoe>
        Log.i("ShoeListViewModel", " in addShoeToList ${name}, $size, $company, $description, ${tempList?.size}")
        tempList.add(Shoe(name, size, company, description))
        _shoeList.value=tempList
        Log.i("ShoeListViewModel", "${_shoeList.value?.size} ${_shoeList.value}")
    }
}