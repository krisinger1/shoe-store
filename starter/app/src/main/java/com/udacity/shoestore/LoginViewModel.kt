package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    private val _loginEmail = MutableLiveData<String>()


    fun onLogin(email : String, password : String){
        //save login info
        //navigate to Welcome screen
    }

    fun onCreateAccount(){
        //save login info
        //navigate to Welcome screen
    }
}