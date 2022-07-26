package com.example.shoestore_starter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.shoestore_starter.databinding.FragmentOnboarding1Binding


class OnboardingFragment1 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this
        val binding: FragmentOnboarding1Binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_onboarding1, container, false)
        binding.onboarding1Button.setOnClickListener {
            view?.findNavController()
                ?.navigate(OnboardingFragment1Directions.actionOnboardingFragment1ToOnboardingFragment2())
        }
        return binding.root
    }
}
