package com.udacity.shoestore

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.ShoeBinding
import com.udacity.shoestore.databinding.ShoeListFragmentBinding
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class ShoeListFragment : Fragment() {

    private lateinit var binding : ShoeListFragmentBinding
    private lateinit var itemBinding : ShoeBinding
    private lateinit var viewModel : ShoeListViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        binding = ShoeListFragmentBinding.inflate(inflater, container,false)

        // get the viewModel
        // make viewModel Activity based so detail fragment can use as well
        viewModel= ViewModelProvider(requireActivity()).get(ShoeListViewModel::class.java)
        Timber.i("got the viewModel")

        with(binding) {
            shoeListViewModel = viewModel
            lifecycleOwner = this@ShoeListFragment
        }

        //observers
        // go to detail screen when button clicked
        viewModel.eventGoToAddShoe.observe(viewLifecycleOwner, Observer{ goToAdd ->
            if (goToAdd) goToAddShoe()
        })

        //update the shoe list when shoe list changes
        viewModel.shoeList.observe(viewLifecycleOwner, Observer{list->
            updateShoeList(inflater, list)
        })

        // this layout has overflow menu
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
        with(itemBinding) {
            shoeBrandText.text = shoe.company
            shoeDescriptionText.text = shoe.description
            shoeNameText.text = shoe.name
            shoeSizeText.text = "Size: ${shoe.size}"
        }
        return itemBinding.root
    }

    private fun updateShoeList(inflater : LayoutInflater, list : MutableList<Shoe>) {
        Timber.i("in updateShoeList")
        for (thisShoe in list){
            binding.shoeListLinearLayout.addView(createShoeItem(inflater, thisShoe))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.logout_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //pop back stack so can't go back after logging out
        findNavController().popBackStack(R.id.shoe_list_destination,true)
        return NavigationUI.onNavDestinationSelected(item,findNavController()) || super.onOptionsItemSelected(item)
    }

}