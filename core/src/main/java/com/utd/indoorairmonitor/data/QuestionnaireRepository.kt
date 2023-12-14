package com.utd.indoorairmonitor.data

class QuestionnaireRepository (private val dataSource: QuestionnaireDataSource) {
    suspend fun setAnswers(answers: IntArray) = dataSource.setAnswers(answers)

    fun getAnswers() = dataSource.getAnswers()
}