package com.udacity.shoestore

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.ShoeBinding
import com.udacity.shoestore.databinding.ShoeListFragmentBinding
import com.udacity.shoestore.models.Shoe

class ShoeListFragment : Fragment() {

    private lateinit var binding : ShoeListFragmentBinding
    private lateinit var itemBinding : ShoeBinding
    private lateinit var viewModel : ShoeListViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.shoe_list_fragment,
                container,
                false
        )

        // get the viewModel
        // make viewModel Activity based so detail fragment can use as well
        viewModel= ViewModelProvider(requireActivity()).get(ShoeListViewModel::class.java)
        Log.i("ShoeListFragment", "got the viewModel")


        binding.shoeListViewModel=viewModel
        binding.lifecycleOwner=this

//        if (activity is AppCompatActivity) {
//            (activity as AppCompatActivity?)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        }

        //observers
        // go to detail screen when button clicked
        viewModel.eventGoToAddShoe.observe(viewLifecycleOwner, Observer{ goToAdd ->
            if (goToAdd) goToAddShoe()
        })

        //update the shoe list when shoe list changes
        viewModel.shoeList.observe(viewLifecycleOwner, Observer{list->
            updateShoeList(inflater, list)
        })
        setHasOptionsMenu(true)

        return binding.root
    }



    private fun goToAddShoe() {
        NavHostFragment.findNavController(this).navigate(ShoeListFragmentDirections.actionShoeListToShoeDetail())
        viewModel.onGoToAddShoeComplete()
    }

    private fun createShoeItem(inflater: LayoutInflater, shoe : Shoe): View{
        //inflate detail layout into the linear layout of ShoeListFragment
        itemBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.shoe,
            binding.shoeListLinearLayout,
            false
        )
        itemBinding.shoeBrandText.text = shoe.company
        itemBinding.shoeDescriptionText.text = shoe.description
        itemBinding.shoeNameText.text = shoe.name
        itemBinding.shoeSizeText.text = shoe.size.toString()

        return itemBinding.root
    }

    private fun updateShoeList(inflater : LayoutInflater, list : MutableList<Shoe>) {
        //binding.shoeListLinearLayout.addView(createShoeItem(inflater, shoe))
        Log.i("ShoeListFragment", "in updateShoeList")
        Log.i("ShoeListFragment", "${viewModel.shoeList.value}")


        for (thisShoe in list){
            binding.shoeListLinearLayout.addView(createShoeItem(inflater, thisShoe))
            Log.i("for", "adding view")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.logout_menu, menu)

        //super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //val navController = findNavController
        return NavigationUI.onNavDestinationSelected(item,findNavController()) || super.onOptionsItemSelected(item)
        //return super.onOptionsItemSelected(item)
    }

}