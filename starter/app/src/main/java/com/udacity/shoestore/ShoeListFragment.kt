package com.udacity.shoestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.ShoeBinding
import com.udacity.shoestore.databinding.ShoeListFragmentBinding
import com.udacity.shoestore.models.Shoe

class ShoeListFragment : Fragment() {

    private lateinit var binding : ShoeListFragmentBinding
    private lateinit var itemBinding : ShoeBinding

    private val shoeList : MutableList<Shoe> = mutableListOf(
        Shoe("name1",5.0,"Nike", "describe shoe 1"),
        Shoe("name2",6.0,"Addidas", "describe shoe 2"),
        Shoe("name2",7.0,"Champion", "describe shoe 3"),
        Shoe("name2",8.0,"Avia", "describe shoe 4")
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.shoe_list_fragment,
            container,
            false
        )

        binding.addShoeFab.setOnClickListener {
            findNavController().navigate(ShoeListFragmentDirections.actionShoeListToShoeDetail())
        }

//        var singleShoe = Shoe("cross trainer", 7.0, "Nike", "white athletic shoe" )
//
//        binding.shoeListLinearLayout.addView(createShoeItem(inflater, singleShoe))

        for (thisShoe in shoeList){
            binding.shoeListLinearLayout.addView(createShoeItem(inflater, thisShoe))
        }

        return binding.root
    }

    private fun createShoeItem(inflater: LayoutInflater, shoe : Shoe): View{
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


}