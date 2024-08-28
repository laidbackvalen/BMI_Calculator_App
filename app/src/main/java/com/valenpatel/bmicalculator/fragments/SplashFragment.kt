package com.valenpatel.bmicalculator.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.valenpatel.bmicalculator.activities.MainActivity
import com.valenpatel.bmicalculator.R

class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_splash, container, false)

        // Ensure activity is not null and is of type MainActivity
        val mainActivity = activity as? MainActivity
        if (mainActivity != null) {
            // Use handler to post a delayed action
            android.os.Handler().postDelayed({
                mainActivity.setFragment(SelectGenderFragment())
            }, 3500)
        } else {
            // Handle the case where activity is not MainActivity or is null
            // Maybe log an error or show a default behavior
        }

        return view
    }
}
