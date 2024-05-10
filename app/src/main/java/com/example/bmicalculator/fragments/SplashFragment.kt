package com.example.bmicalculator.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bmicalculator.MainActivity
import com.example.bmicalculator.R
import java.util.logging.Handler

class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_splash, container, false)


        var handler = android.os.Handler().postDelayed({
            (activity as MainActivity).setFragment(SelectGenderFragment())
        },3500)

        return view;
    }


}