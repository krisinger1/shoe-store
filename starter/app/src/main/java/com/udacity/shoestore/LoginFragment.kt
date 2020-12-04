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

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.login_fragment,
            container,
            false
        )

        binding.lifecycleOwner=this
        viewModel=ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.loginViewModel=viewModel


//        binding.loginButton.setOnClickListener{
//            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
//        }
//
//        binding.newLoginButton.setOnClickListener{
//            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
//        }

        //observers
        // go to welcome screen when login clicked
        viewModel.isLoggedIn.observe(viewLifecycleOwner, Observer{ loggedIn ->
            if (loggedIn) goToWelcome()
        })

        return binding.root
    }

    private fun goToWelcome(){
        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
    }
}