package com.udacity.shoestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.InstructionsFragmentBinding
import com.udacity.shoestore.databinding.LoginFragmentBinding


class InstructionsFragment : Fragment() {

    private lateinit var binding : InstructionsFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = InstructionsFragmentBinding.inflate(inflater, container, false)

        binding.viewInventoryButton.setOnClickListener {
            findNavController().navigate(InstructionsFragmentDirections.actionInstructionsToShoeList())
        }

        return binding.root
    }

}