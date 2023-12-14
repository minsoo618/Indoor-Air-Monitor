package com.utd.indoorairmonitor.data

interface QuestionnaireDataSource {
    suspend fun setAnswers(answers: IntArray)

    fun getAnswers(): IntArray
}