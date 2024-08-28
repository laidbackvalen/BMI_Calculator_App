package com.valenpatel.bmicalculator.fragments

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.valenpatel.bmicalculator.activities.MainActivity
import com.valenpatel.bmicalculator.R
import com.valenpatel.bmicalculator.databinding.FragmentResultsBinding


class ResultsFragment : Fragment() {
    lateinit var resultFragmentBinding: FragmentResultsBinding
    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        resultFragmentBinding = FragmentResultsBinding.inflate(inflater, container, false)

        // Inflate the layout for this fragment
        val height = arguments?.getString("height")
        val weight = arguments?.getString("weight")
        val age = arguments?.getString("age")
        val gender = arguments?.getString("gender")
        val bmi = arguments?.getString("bmi")
        val bmiRanges = arguments?.getString("bmiRanges")
        val healthyWeight = arguments?.getString("healthyWeight")

        resultFragmentBinding.weightResultTextView.setText(weight + " Kg")
        resultFragmentBinding.heightResultTextView.setText(height + " cm")
        resultFragmentBinding.ageResultTextView.setText(age)
        resultFragmentBinding.genderresultTextView.setText(gender)
        resultFragmentBinding.yourBmiResultTxt.setText(bmi + " kg/m2")
        resultFragmentBinding.bmiRanges.setText(bmiRanges)
        resultFragmentBinding.healthyWeightResultTextView.setText(healthyWeight)

        if (bmiRanges != null) {
            if (bmiRanges == "Severely obese" || bmiRanges=="Obesity") {
                resultFragmentBinding.bmiRanges.setTextColor(Color.RED)
                resultFragmentBinding.weightResultTextView.setTextColor(Color.RED)
                resultFragmentBinding.yourBmiResultTxt.setTextColor(Color.RED)
                resultFragmentBinding.view4.background = resources.getDrawable(R.drawable.view_result_bg_red)
                resultFragmentBinding.imageView2.setImageResource(R.drawable.redreadingimage)

            } else if(bmiRanges=="Overweight"){
                resultFragmentBinding.bmiRanges.setTextColor(resources.getColor(R.color.yellow))
                resultFragmentBinding.weightResultTextView.setTextColor(resources.getColor(R.color.yellow))
                resultFragmentBinding.yourBmiResultTxt.setTextColor(resources.getColor(R.color.yellow))
                resultFragmentBinding.view4.background = resources.getDrawable(R.drawable.view_result_bg_yellow)
                resultFragmentBinding.imageView2.setImageResource(R.drawable.yellowreadingimage)
            }else if(bmiRanges=="underweight"){
                resultFragmentBinding.bmiRanges.setTextColor(Color.BLUE)
                resultFragmentBinding.weightResultTextView.setTextColor(Color.BLUE)
                resultFragmentBinding.yourBmiResultTxt.setTextColor(Color.BLUE)
                resultFragmentBinding.view4.background = resources.getDrawable(R.drawable.view_result_bg_blue)
                resultFragmentBinding.imageView2.setImageResource(R.drawable.bluereadingimage)
            }else{
                resultFragmentBinding.imageView2.setImageResource(R.drawable.greenreadingimage)
            }

        }

        resultFragmentBinding.closeButton.setOnClickListener {
            (activity as MainActivity).setFragment(SelectGenderFragment())
        }
        return resultFragmentBinding.root
    }

}