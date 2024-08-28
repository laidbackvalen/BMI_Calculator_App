package com.valenpatel.bmicalculator.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.valenpatel.bmicalculator.R
import com.valenpatel.bmicalculator.activities.MainActivity
import com.valenpatel.bmicalculator.databinding.FragmentMoreBinding

class MoreFragment : Fragment() {
    lateinit var moreBinding: FragmentMoreBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val inflater = LayoutInflater.from(requireContext())
        moreBinding = FragmentMoreBinding.inflate(inflater, container, false)

//        requireActivity().window.statusBarColor = ContextCompat.getColor(requireActivity(), R.color.theme)

        moreBinding.helpNSupport.setOnClickListener {
            sendEmail()
        }
        moreBinding.feedbackView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data =
                    Uri.parse("https://play.google.com/store/apps/details?id=com.valenpatel.bmicalculator&pcampaignid=web_share")
            }

// Check if there's an app to handle this intent
            if (intent.resolveActivity(requireActivity().packageManager) != null) {
                startActivity(intent)
            } else {
                // Handle the case where no app can handle the intent
                Toast.makeText(
                    requireContext(),
                    "No app found to open this link",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        moreBinding.backImageView.setOnClickListener {
            (activity as MainActivity).setFragment(SelectGenderFragment())
        }
        moreBinding.privacyView.setOnClickListener {
            val uri = "https://laidbackvalen.github.io/bmicalcprivacypol/"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse(uri))
            startActivity(intent)
        }
        moreBinding.termsView.setOnClickListener {
            val uri = "https://laidbackvalen.github.io/BMItermsnconditions/"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse(uri))
            startActivity(intent)
        }

        return moreBinding.root
    }

    private fun sendEmail() {
        val emailIntent =
            Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "valen.patel1@gmail.com", null))
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "This is my subject text")
        requireContext().startActivity(Intent.createChooser(emailIntent, null))
    }
}