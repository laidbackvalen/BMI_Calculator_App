package com.example.bmicalculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.bmicalculator.databinding.FragmentAddValuesBinding


class AddValuesFragment : Fragment() {
    private lateinit var addValuesBinding: FragmentAddValuesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        addValuesBinding = FragmentAddValuesBinding.inflate(inflater, container, false)

        val data = arguments?.getString("data")

        Toast.makeText(activity, ""+data, Toast.LENGTH_SHORT).show()
        addValuesBinding.displayTxt.text = data

        return addValuesBinding.root


    }

}