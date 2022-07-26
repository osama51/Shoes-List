package com.example.shoestore_starter

import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.shoestore_starter.databinding.FragmentShoesDetailsBinding
import com.example.shoestore_starter.modeks.ShoeDetailViewModel

class ShoesDetailsFragment : Fragment() {

    private lateinit var viewModel: ShoeDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentShoesDetailsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_shoes_details, container, false)

        Log.i("ShoeDetailViewModel", "ViewModelProvider called!")
        viewModel = ViewModelProvider(requireActivity())[ShoeDetailViewModel::class.java]


        binding.saveButton.setOnClickListener {
            viewModel.addShoe(
                binding.shoeNameEdit.text.toString(),
                binding.shoeSizeEdit.text.toString().toDouble(),
                binding.shoeCompEdit.text.toString(),
                binding.shoeDescriptionEdit.text.toString()
            )
            viewModel.flag.value = true
//            viewModel.flag.value = !viewModel.flag.value!!

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
