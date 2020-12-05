package com.udacity.shoestore

import android.util.Log
import androidx.databinding.Bindable
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


    //private val _newShoe = MutableLiveData<Shoe>()
    //val newShoe = MutableLiveData<Shoe>()

    val shoeName= MutableLiveData<String>()
    val shoeBrand = MutableLiveData<String>()
    val shoeSize = MutableLiveData<String>()
    val shoeDescription = MutableLiveData<String>()


    init {
        Timber.i("in init block")
        // shoe examples from Kohls.com website
        _shoeList.value = mutableListOf(
                Shoe("Red Boot", 5.0, "Ugg", "Victoria Mini winter boots from Koolaburra by UGG."),
                Shoe("Ines Winter Boot", 6.0, "Croft & Barrow", "Round toe and zipper closure."),
                Shoe("Block Heel Ankle Boot", 7.0, "Journey", "Lace up front."))

        _eventGoToAddShoe.value = false
    }


    public fun onGoToAddShoe(){
        _eventGoToAddShoe.value=true
    }

    public fun onGoToAddShoeComplete(){
        _eventGoToAddShoe.value=false
        // replace newShoe with a blank Shoe so ready to get new data
        //_newShoe.value=null
        //newShoe.value=null
        shoeName.value=null
        shoeDescription.value=null
        shoeSize.value=null
        shoeBrand.value=null
    }

    public fun addShoeToList(){
        Timber.i("shoe values:  ${shoeName.value} ${shoeBrand.value} ${shoeSize.value} ${shoeDescription.value}")
        // add shoe to list
        _shoeList.value?.add(Shoe(shoeName.value!!,shoeSize.value!!.toDouble(), shoeBrand.value!!, shoeDescription.value!!))
    }
}