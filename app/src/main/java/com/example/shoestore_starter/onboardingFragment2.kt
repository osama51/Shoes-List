package com.example.shoestore_starter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        binding.onboarding2Button.setOnClickListener {
            view?.findNavController()
                ?.navigate(OnboardingFragment2Directions.actionOnboardingFragment2ToShoeListFragment())
        }
        return binding.root
    }
}
