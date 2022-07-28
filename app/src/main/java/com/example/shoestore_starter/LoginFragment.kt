package com.example.shoestore_starter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.shoestore_starter.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_login, container, false

        val binding: FragmentLoginBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_login, container, false
        )

        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
        binding.loginButton.setOnClickListener {
            view?.findNavController()
                ?.navigate(LoginFragmentDirections.actionLoginFragment3ToShoeListFragment())
        }
        binding.registerButton.setOnClickListener {
            view?.findNavController()
                ?.navigate(LoginFragmentDirections.actionLoginFragment3ToOnboardingFragment1())
        }
        return binding.root
    }

}
