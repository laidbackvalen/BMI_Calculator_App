package com.example.bmicalculator

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bmicalculator.databinding.FragmentSelectGenderBinding

class SelectGenderFragment : Fragment() {
    private lateinit var selectGenderBinding: FragmentSelectGenderBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        selectGenderBinding = FragmentSelectGenderBinding.inflate(inflater, container, false)

        selectGenderBinding.button.setOnClickListener {
            (activity as MainActivity).sendDataToAddValuesFragments("male")
        }
        return selectGenderBinding.root;
    }


}