package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    private val _isLoggedIn = MutableLiveData<Boolean>()
    val isLoggedIn : LiveData<Boolean>
        get()= _isLoggedIn

    init{
        _isLoggedIn.value=false
    }


    fun onLogin(){
        _isLoggedIn.value=true
    }

    fun onCreateAccount(){
        _isLoggedIn.value=true
    }
}