package com.example.shoestore_starter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.shoestore_starter.databinding.FragmentOnboarding2Binding


class OnboardingFragment2 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this
        val binding: FragmentOnboarding2Binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_onboarding2, container, false)

        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
        binding.onboarding2Button.setOnClickListener {
            view?.findNavController()
                ?.navigate(OnboardingFragment2Directions.actionOnboardingFragment2ToShoeListFragment())
        }

        binding.onboarding2Text.setOnClickListener{
            Toast.makeText(activity, "I swear there's text here!!", Toast.LENGTH_SHORT).show()
        }
        return binding.root
    }
}
