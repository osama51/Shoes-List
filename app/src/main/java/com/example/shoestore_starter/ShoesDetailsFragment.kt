package com.example.shoestore_starter

import android.os.Bundle
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.shoestore_starter.databinding.FragmentShoesDetailsBinding

class ShoesDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentShoesDetailsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_shoes_details, container, false)

        binding.saveButton.setOnClickListener {
            view?.findNavController()
                ?.navigate(ShoesDetailsFragmentDirections.actionShoesDetailsFragmentToShoeListFragment())
        }
        binding.cancelButton.setOnClickListener {
            view?.findNavController()
                ?.navigateUp()
        }
        return binding.root
    }
}
