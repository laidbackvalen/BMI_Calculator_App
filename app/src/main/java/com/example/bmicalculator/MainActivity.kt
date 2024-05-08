package com.example.bmicalculator

import android.os.Bundle
import android.widget.FrameLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.bmicalculator.databinding.ActivityMainBinding

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
        setFragment(SelectGenderFragment())
    }

    fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(frameLayout.getId(), fragment).commit()
    }

    fun sendDataToAddValuesFragments(data: String) {
        val bundle = Bundle()
        bundle.putString("data", data)

        val fragment = AddValuesFragment()
        fragment.arguments = bundle
        setFragment(fragment)
    }
}


