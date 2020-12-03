package com.udacity.shoestore

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.ShoeDetailFragmentBinding

class ShoeDetailFragment : Fragment() {

    private lateinit var binding : ShoeDetailFragmentBinding
    private lateinit var viewModel : ShoeListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // get shoe list view model to have access to data for shoes
        // make the viewModel Activity based instead of Fragment based so can be used by List as well
        viewModel= ViewModelProvider(requireActivity()).get(ShoeListViewModel::class.java)
        Log.i("ShoeDetailFragment", "Got the viewModel")

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.shoe_detail_fragment, container, false)

        binding.addShoeButton.setOnClickListener {
            if (binding.shoeNameEditText.toString()!="") {
                viewModel.addShoeToList(binding.shoeNameEditText.text.toString(),
                        binding.shoeSizeEditText.text.toString().toDoubleOrNull() ?: 0.0,
                        binding.shoeBrandEditText.text.toString(),
                        binding.shoeDescriptionEditText.text.toString())
            }
            findNavController().navigate(ShoeDetailFragmentDirections.actionAddShoeToShoeList())
        }

       return binding.root
    }

   //TODO close keyboard after input
}