package com.example.bmicalculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.bmicalculator.databinding.ActivityMainBinding
import com.example.bmicalculator.fragments.AddValuesFragment
import com.example.bmicalculator.fragments.ResultsFragment
import com.example.bmicalculator.fragments.SplashFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var frameLayout: FrameLayout;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        frameLayout = binding.frameLayoutMain
        setFragment(SplashFragment())
    }

    fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(frameLayout.getId(), fragment).commit()
    }

    fun sendDataToAddValuesFragments(gender: String) {
        val bundle = Bundle()
        bundle.putString("gender", gender)

        val fragment = AddValuesFragment()
        fragment.arguments = bundle
        setFragment(fragment)
    }

    fun sendGenderToRequiredFragment(gender: String,fragment: Fragment){
        val bundle = Bundle()
        bundle.putString("gender",  gender)

        val requiredFragment = fragment
        requiredFragment.arguments = bundle
        setFragment(requiredFragment)
    }

    fun sendDataToResultsFragment(
        gender: String,
        height: String,
        weight: String,
        age: String,
        bmi: String,
        bmiRanges: String,
        healthyWeight: String,
    ) {
        val bundle = Bundle()
        bundle.putString("gender", gender)
        bundle.putString("height", height)
        bundle.putString("weight", weight)
        bundle.putString("age", age)
        bundle.putString("bmi", bmi)
        bundle.putString("bmiRanges", bmiRanges)
        bundle.putString("healthyWeight", healthyWeight)

        val resultFragment = ResultsFragment()
        resultFragment.arguments = bundle
        setFragment(resultFragment)
    }

    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
        var alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Do yo want to exit this app?")
//            .setMessage("yes")
            .setCancelable(false)
            .setPositiveButton("Yes") { dialog, which ->
                super.onBackPressed()
            }
            .setNegativeButton("No") { dialog, which ->
                dialog.dismiss()
                Toast.makeText(
                    this,
                    "Select buttons and icons to navigate through the App.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        var dialog = alertDialog.create()
        dialog.show()
    }
}


