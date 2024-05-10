package com.example.bmicalculator.fragments

import android.content.Context
import android.os.Bundle
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.view.marginTop
import androidx.core.view.setMargins
import androidx.fragment.app.Fragment
import com.example.bmicalculator.MainActivity
import com.example.bmicalculator.databinding.FragmentSelectGenderBinding


class SelectGenderFragment : Fragment() {
    private lateinit var selectGenderBinding: FragmentSelectGenderBinding
    var gender: String = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        selectGenderBinding = FragmentSelectGenderBinding.inflate(inflater, container, false)

        selectGenderBinding.cardViewMale.setOnClickListener {
            gender = "male"
            selectGenderBinding.cardViewFemale.visibility = View.GONE
            selectGenderBinding.viewCancelOrChange.visibility = View.VISIBLE
            selectGenderBinding.changeLottie.visibility = View.VISIBLE
            selectGenderBinding.changeText.visibility = View.VISIBLE
        }
        selectGenderBinding.cardViewFemale.setOnClickListener {
            gender = "female"
            selectGenderBinding.cardViewMale.visibility = View.GONE
            selectGenderBinding.viewCancelOrChange.visibility = View.VISIBLE
            selectGenderBinding.changeLottie.visibility = View.VISIBLE
            selectGenderBinding.changeText.visibility = View.VISIBLE

            //setting Margin of card view
            val param = selectGenderBinding.cardViewMale.layoutParams as ViewGroup.MarginLayoutParams
            param.setMargins(0, 10, 0, 0)
            selectGenderBinding.cardViewFemale.layoutParams = param
        }

        //Sending Gender Data to MainActivity
        selectGenderBinding.genderButton.setOnClickListener {
            if (gender.isBlank()) {
                Toast.makeText(activity, "Choose a Gender to Continue", Toast.LENGTH_SHORT).show()
            } else if (gender.equals("male")) {
                (activity as MainActivity).sendDataToAddValuesFragments(gender)
            } else if (gender.equals("female")) {
                (activity as MainActivity).sendDataToAddValuesFragments(gender)
            }
        }

        //Change Gender
        selectGenderBinding.viewCancelOrChange.setOnClickListener {
            (activity as MainActivity).setFragment(SelectGenderFragment())
//            (activity as MainActivity).onBackPressed()
        }
        return selectGenderBinding.root;
    }
}