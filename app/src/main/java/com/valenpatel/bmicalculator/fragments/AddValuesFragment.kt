package com.valenpatel.bmicalculator.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import com.valenpatel.bmicalculator.activities.MainActivity
import com.valenpatel.bmicalculator.R
import com.valenpatel.bmicalculator.databinding.FragmentAddValuesBinding
import kotlin.math.roundToInt


class AddValuesFragment : Fragment() {
    private lateinit var addValuesBinding: FragmentAddValuesBinding //view binding Variable
    var assumeWeight: Int = 65  //default weight
    var assumeAge: Int = 26  //default age
    var currentProgress: Double = 0.0 // progressbar height
    var assumeHeight: Double = 170.0 // default height
    lateinit var bmiRanges: String
    lateinit var healthyWeight: String
    lateinit var selectedItem : String
//    var generic = arrayOf("Kg-Ft", "lb-Cm")

    @SuppressLint("DefaultLocale", "UseCompatLoadingForDrawables")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        addValuesBinding = FragmentAddValuesBinding.inflate(inflater, container, false)

        //getting this from select gender fragment
        //there is function on Main activity which is helping to pass this data to this fragment using bundle, the data is passed from Select Gender Fragment
        val gender = arguments?.getString("gender")

        if (gender == "male") {
            addValuesBinding.view.background =
                resources.getDrawable(R.drawable.view_male_background_corner_radius)
            addValuesBinding.view2.background =
                resources.getDrawable(R.drawable.view_male_background_corner_radius)
            addValuesBinding.view3.background =
                resources.getDrawable(R.drawable.view_male_background_corner_radius)
        } else {
            addValuesBinding.view.background =
                resources.getDrawable(R.drawable.view_female_background_corner_radius)
            addValuesBinding.view2.background =
                resources.getDrawable(R.drawable.view_female_background_corner_radius)
            addValuesBinding.view3.background =
                resources.getDrawable(R.drawable.view_female_background_corner_radius)
        }

        //to go back Imageview
        addValuesBinding.backToSelectGenderImage.setOnClickListener {
            (activity as MainActivity).setFragment(SelectGenderFragment())
        }

        val measuring = resources.getStringArray(R.array.mesauringTypes)
        val adapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, measuring)
        addValuesBinding.autoCompleteTextView.setAdapter(adapter)


        //CHANGING FRAGMENT ACCORDING TO MEASUREMENT UNIT PASSED BY THE USER
     addValuesBinding.autoCompleteTextView.setOnItemClickListener { adapterView, view, i, l ->
         selectedItem = adapterView.getItemAtPosition(i).toString()
        if (selectedItem == "lbs - cm") {
            (activity as MainActivity).setFragment(PoundsAndCentimetersFragment())
            if (gender != null) {
                //SENDING GENDER TO REQUIRED FRAGMENT
                (activity as MainActivity).sendGenderToRequiredFragment(gender, PoundsAndCentimetersFragment()) // THIS FUNCTION IS DECLARE IN MAIN ACTIVITY
            }
        }
         else if(selectedItem=="kg - ft"){
            (activity as MainActivity).setFragment(KilogramFeetsAndInchesFragment())
            if (gender != null) {
                (activity as MainActivity).sendGenderToRequiredFragment(gender, KilogramFeetsAndInchesFragment())
            }
        }
     }
        //As we are also using EditText i have used addTextChangedListener to get the text from EditText when text changes
        addValuesBinding.weightCountTextAddValues.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                //get the text from EditText and convert it to int
                val weightString = p0.toString()
                try {
                    assumeWeight = weightString.toInt() //convert the text to int
                } catch (e: NumberFormatException) {
                    // Handle the case where the text cannot be converted to an integer
                    assumeWeight = 0 // or any default value you want to set
                }
            }
        })

        //add and remove weight using imageview on click listener
        addValuesBinding.addWeightByOneImage.setOnClickListener {
            assumeWeight ++
            val na = assumeWeight.toString()
            addValuesBinding.weightCountTextAddValues.setText(assumeWeight.toString())
//            Toast.makeText(activity, "" + na, Toast.LENGTH_SHORT).show()
        }
        addValuesBinding.removeWeightByOneImage.setOnClickListener {
            if (assumeWeight > 0) {
                assumeWeight --
                addValuesBinding.weightCountTextAddValues.setText(assumeWeight.toString())
            }
        }

        //listener to get the text from EditText when text changes
        addValuesBinding.ageCounttextView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                val ageString = p0.toString()
                try {
                    assumeAge = ageString.toInt()
                } catch (e: NumberFormatException) {
                    // Handle the case where the text cannot be converted to an integer
                    assumeAge = 0 // or any default value you want to set
                }
            }
        })
        //add and remove age using Imageiew on click
        addValuesBinding.addAgeimageView.setOnClickListener {
            assumeAge ++
            addValuesBinding.ageCounttextView.setText(assumeAge.toString())
        }
        addValuesBinding.removeAgeimageView.setOnClickListener {
            if (assumeAge > 0) {
                assumeAge --
                addValuesBinding.ageCounttextView.setText(assumeAge.toString())
            }
        }

        //listener to get the text from Height EditText when text changes
        addValuesBinding.heightCountTextView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                val heightString = p0.toString()
                try {
                    assumeHeight = heightString.toDouble()
                } catch (e: NumberFormatException) {
                    // Handle the case where the text cannot be converted to an integer
                    assumeHeight = 0.0 // or any default value you want to set
                }
            }
        })

        //getting Height Data using Seekbar Progress
        addValuesBinding.seekbarHeight.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekbar: SeekBar?, progress: Int, fromUser: Boolean) {
                //get the progress from Seekbar
                currentProgress = progress.toDouble() //convert the progress to double
                assumeHeight = currentProgress //set the progress to height
                addValuesBinding.heightCountTextView.setText(currentProgress.toString()) //set the progress to textview
            }

            override fun onStartTrackingTouch(seekbar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekbar: SeekBar?) {
            }
        })

        healthyWeight = ""
        //Calculate Button Onclick Listener
        //passing the data to Result Fragment
        //calculate the bmi using bmiCalculator function
        //passing the data to Result Fragment
        addValuesBinding.calculateButton.setOnClickListener {
            (activity as MainActivity).setFragment(ResultsFragment())
            if (gender != null) {//check if gender is not null
                val bmiCalc = bmiCalculator(assumeWeight, assumeHeight)//calculate the bmi
                val bmi = String.format("%.1f", bmiCalc) //format the bmi to 1 decimal place

                if (bmiCalc.toInt() >= 40) {
                    bmiRanges = "Severely obese"
                } else if (bmiCalc.toInt() in 30..39) {
                    bmiRanges = "Obesity"
                } else if (bmiCalc.toInt() in 25..29) {
                    bmiRanges = "Overweight"
                } else if (bmiCalc.toInt() > 18.5 && bmiCalc.toInt() < 25) {
                    bmiRanges = "Healthy weight"
                } else {
                    bmiRanges = "underweight"
                }
                (activity as MainActivity).sendDataToResultsFragment(
                    gender,
                    assumeHeight.roundToInt().toString(),
                    assumeWeight.toString(),
                    assumeAge.toString(),
                    bmi, bmiRanges, healthyWeightAsHeight(assumeHeight, gender)
                )
            }
        }
        return addValuesBinding.root
    }

    fun bmiCalculator(weight: Int, height: Double): Double {
        //1m = 100cm //we are taking input in cms
        return weight / (height * height) * 10000  //height should be in meters as per formula // So Multiplying 10000 with the result to get result of Kgs/m(square)
    } //bmiCalculator function

    fun healthyWeightAsHeight(height: Double, gender: String): String {
        if (gender == "male") {
            if (height == 152.0) {
                healthyWeight = "50 kg - 54 kg"
            } else if (height <= 155.0 && height > 152.0) {
                healthyWeight = "52 kg - 56 kg"
            } else if (height <= 157.0 && height > 155.0) {
                healthyWeight = "54 kg - 58 kg"
            } else if (height <= 160.0 && height > 157.0) {
                healthyWeight = "56 kg - 60 kg"
            } else if (height <= 163.0 && height > 160.0) {
                healthyWeight = "57 kg - 61 kg"
            } else if (height <= 165.0 && height > 163.0) {
                healthyWeight = "59 kg - 69 kg"
            } else if (height <= 168.0 && height > 165.0) {
                healthyWeight = "61 kg - 65 kg"
            } else if (height <= 170.0 && height > 168.0) {
                healthyWeight = "63 kg - 67 kg"
            } else if (height <= 173.0 && height > 170.0) {
                healthyWeight = "65 kg - 69 kg"
            } else if (height <= 176.0 && height > 173.0) {
                healthyWeight = "67 kg - 71 kg"
            } else if (height <= 177.0 && height > 176.0) {
                healthyWeight = "69 kg - 73 kg"
            } else if (height <= 180.0 && height > 177.0) {
                healthyWeight = "71 kg - 75 kg"
            } else if (height <= 183.0 && height > 180.0) {
                healthyWeight = "73 kg - 77 kg"
            } else if (height <= 185.0 && height > 183.0) {
                healthyWeight = "75 kg - 79 kg"
            } else if (height <= 188.0 && height > 185.0) {
                healthyWeight = "77 kg - 81 kg"
            } else if (height <= 190.0 && height > 188.0) {
                healthyWeight = "79 kg - 83 kg"
            } else if (height <= 193.0 && height > 190.0) {
                healthyWeight = "81 kg - 85 kg"
            }
        } else {
            if (gender == "female") {
                if (height == 152.0) {
                    healthyWeight = "48 kg - 52 kg"
                } else if (height <= 155.0 && height > 152.0) {
                    healthyWeight = "50 kg - 54 kg"
                } else if (height <= 157.0 && height > 155.0) {
                    healthyWeight = "51 kg - 55 kg"
                } else if (height <= 160.0 && height > 157.0) {
                    healthyWeight = "53 kg - 57 kg"
                } else if (height <= 163.0 && height > 160.0) {
                    healthyWeight = "55 kg - 59 kg"
                } else if (height <= 165.0 && height > 163.0) {
                    healthyWeight = "57 kg - 61 kg"
                } else if (height <= 168.0 && height > 165.0) {
                    healthyWeight = "58 kg - 62 kg"
                } else if (height <= 170.0 && height > 168.0) {
                    healthyWeight = "60 kg - 64 kg"
                } else if (height <= 173.0 && height > 170.0) {
                    healthyWeight = "62 kg - 66 kg"
                } else if (height <= 176.0 && height > 173.0) {
                    healthyWeight = "64 kg - 68 kg"
                } else if (height <= 177.0 && height > 176.0) {
                    healthyWeight = "66 kg - 70 kg"
                } else if (height <= 180.0 && height > 177.0) {
                    healthyWeight = "68 kg - 72 kg"
                } else if (height <= 183.0 && height > 180.0) {
                    healthyWeight = "70 kg - 74 kg"
                } else if (height <= 185.0 && height > 183.0) {
                    healthyWeight = "72 kg - 76 kg"
                } else if (height <= 188.0 && height > 185.0) {
                    healthyWeight = "74 kg - 78 kg"
                } else if (height <= 190.0 && height > 188.0) {
                    healthyWeight = "75 kg - 80 kg"
                } else if (height <= 193.0 && height > 190.0) {
                    healthyWeight = "81 kg - 85 kg"
                }
            }
        }
        return healthyWeight
    }

}

