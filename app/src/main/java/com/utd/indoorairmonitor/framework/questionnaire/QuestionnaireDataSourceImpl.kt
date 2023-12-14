package com.utd.indoorairmonitor.framework.questionnaire

import com.utd.indoorairmonitor.data.QuestionnaireDataSource
import com.utd.indoorairmonitor.domain.Questionnaire

class QuestionnaireDataSourceImpl: QuestionnaireDataSource {

    //each 0 can be 0,1,2 for mild/ moderate/ severe
    internal var questionnaire: Questionnaire
            = Questionnaire(intArrayOf(0,0,0,0,0,0))

    override fun getAnswers(): IntArray = questionnaire.answers

    override suspend fun setAnswers(answers: IntArray) {
        questionnaire.answers = answers
    }
}