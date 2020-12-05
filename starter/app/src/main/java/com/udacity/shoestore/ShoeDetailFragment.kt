package com.udacity.shoestore

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.ShoeDetailFragmentBinding
import timber.log.Timber
import java.time.Duration

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
        Timber.i("Got the viewModel")

        //var shoe= viewModel.newShoe.value

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.shoe_detail_fragment, container, false)
        binding.lifecycleOwner=this
        binding.shoeListViewModel=viewModel

        binding.shoeNameEditText.requestFocus()

        binding.addShoeButton.setOnClickListener {
            // if a shoe has been entered, add to the list
            Timber.i("name is: ${binding.shoeNameEditText.text.toString()}")
            if (!binding.shoeNameEditText.text.isNullOrBlank()) {
                viewModel.addShoeToList()
                // then navigate back to the ShoeListFragment
                findNavController().navigate(ShoeDetailFragmentDirections.actionAddShoeToShoeList())

                //hide the keyboard
                val imm= context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view?.windowToken,0)
            }
            else {
                Toast.makeText(context, "PLease enter a shoe name", Toast.LENGTH_SHORT).show()

            }

        }

       return binding.root
    }


}