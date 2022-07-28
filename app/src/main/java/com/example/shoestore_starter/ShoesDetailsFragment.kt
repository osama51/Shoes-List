package com.example.shoestore_starter

import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
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

        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
        Log.i("ShoeDetailViewModel", "ViewModelProvider called!")
        viewModel = ViewModelProvider(requireActivity())[ShoeDetailViewModel::class.java]

        var str_size_to_double = 0.0

        binding.saveButton.setOnClickListener {
            /** had to use this condition when Elvis operator was persistent to cause a crash if the Double field was left empty */
            str_size_to_double = when (binding.shoeSizeEdit.text.toString()) {
                "" -> { 0.0 }
                else -> { binding.shoeSizeEdit.text.toString().toDouble() }
            }
            viewModel.addShoe(
                binding.shoeNameEdit.text.toString() ?: "",
                str_size_to_double,
                binding.shoeCompEdit.text.toString() ?: "",
                binding.shoeDescriptionEdit.text.toString() ?: ""
            )
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
