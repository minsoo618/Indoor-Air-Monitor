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
import com.utd.indoorairmonitor.databinding.FragmentQuestionaireBinding

class QuestionaireFragment : Fragment() {
    data class Question(
        val text: String,
        val answers: List<String>)

    // Fields of the class QuestionnaireFragment
    private val choices = listOf("Mild", "Moderate", "Severe")
    private val questions: MutableList<Question> = mutableListOf(
        Question(text = "Are you feeling breathless?",
                answers = choices),
        Question(text = "Are you having coughs?",
                answers = choices),
        Question(text = "Are you wheezing?",
                answers = choices),
        Question(text = "Are you having chest pains?",
                answers = choices),
        Question(text = "How much Sputum are you producing?",
                answers = choices),
        Question(text = "Are you having any heartburn?",
                answers = choices))

    lateinit var currentQuestion: Question
    lateinit var answers: MutableList<String>
    private var questionIndex = 0
    private val numQuestions = questions.size

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentQuestionaireBinding>(
               inflater, R.layout.fragment_questionaire, container, false
        )

        setFirstQuestion()

        // Bind this fragment class to the layout
        binding.questinoaire = this

        // Set onClickListener for the submit button
        binding.submitButton.setOnClickListener @Suppress("UNUSED_ANONYMOUS_PARAMETER")
        {view: View ->
            val checkedId = binding.questionRadioGroup.checkedRadioButtonId

            var answerIndex = 0
            when (checkedId) {
                R.id.firstAnswerRadioButton -> answerIndex = 0
                R.id.secondAnswerRadioButton -> answerIndex = 1
                R.id.thirdAnswerRadioButton -> answerIndex = 2
            }

            // go to the next question or go to the next fragment
            questionIndex ++
            if (questionIndex < numQuestions){
                //advance to next question
                currentQuestion = questions[questionIndex]
                setFirstQuestion()
                binding.invalidateAll()
            }
            else{
                // go to completeQuestionnaireFragment
                view.findNavController().navigate(
                        R.id.action_questionaireFragment_to_completeQuestionFragment)
            }
        }
        return binding.root
    }
    private fun setFirstQuestion() {
        currentQuestion = questions[questionIndex]
        answers = currentQuestion.answers.toMutableList()
        (activity as AppCompatActivity).supportActionBar?.title = "Questionnaire " + (questionIndex + 1).toString() + "/" + numQuestions.toString()
    }
}