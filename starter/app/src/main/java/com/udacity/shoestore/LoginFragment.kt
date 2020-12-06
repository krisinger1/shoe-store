package com.udacity.shoestore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.LoginFragmentBinding

class LoginFragment : Fragment(){

    private lateinit var binding :LoginFragmentBinding
    private lateinit var viewModel : LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel=ViewModelProvider(this).get(LoginViewModel::class.java)

        // Inflate the layout for this fragment

        binding = LoginFragmentBinding.inflate(inflater, container,false)
        binding.lifecycleOwner=this
        binding.loginViewModel=viewModel

        //observers
        // go to welcome screen when login clicked
        viewModel.isLoggedIn.observe(viewLifecycleOwner, Observer{ loggedIn ->
            if (loggedIn) goToWelcome()
        })

        return binding.root
    }

    private fun goToWelcome(){
        findNavController().navigate(LoginFragmentDirections.actionLoginToWelcome())
        viewModel.onLoginDone()

    }
}