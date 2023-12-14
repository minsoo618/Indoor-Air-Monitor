package com.utd.indoorairmonitor.presentation.questionaire

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.utd.indoorairmonitor.R
import com.utd.indoorairmonitor.databinding.FragmentCompleteQuestionBinding

class CompleteQuestionFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding: FragmentCompleteQuestionBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_complete_question, container, false)
        (activity as AppCompatActivity).supportActionBar?.title = "Questionnaire Completed"

        // Set on click listener on button
        binding.backToHomeButton.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_completeQuestionFragment_to_homeFragment)
        }

        return binding.root
    }
}