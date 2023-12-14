package com.utd.indoorairmonitor.interactors

import com.utd.indoorairmonitor.data.QuestionnaireRepository

class QuestionnaireUseCases (private val repository: QuestionnaireRepository){
    suspend fun setAnswer(answers: IntArray) = repository.setAnswers(answers)

    fun getAnswer() = repository.getAnswers()
}