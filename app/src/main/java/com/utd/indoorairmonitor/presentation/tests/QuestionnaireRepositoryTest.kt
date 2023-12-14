package com.utd.indoorairmonitor.presentation.tests

import com.utd.indoorairmonitor.framework.questionnaire.QuestionnaireDataSourceImpl
import com.utd.indoorairmonitor.domain.Questionnaire
import org.junit.Assert
import org.junit.Test

class QuestionnaireRepositoryTest {
    private val q = QuestionnaireDataSourceImpl()

    @Test
    fun getQuestionnaireAnswers() {
        // Check integrity between questionnaireAnswers and Questionnaire obj
        val qTester = q.questionnaire
        Assert.assertTrue("Not of type Questionnaire", qTester is Questionnaire)

        // Check if questionnaire contains data. If not, print message
        Assert.assertNotNull("Contains no data", qTester)

        // Check if questionnaire is initialized to 0
        Assert.assertEquals("Not initialized to 0", 0, qTester.answers[0])
        Assert.assertEquals("Not initialized to 0", 0, qTester.answers[1])
        Assert.assertEquals("Not initialized to 0", 0, qTester.answers[2])
        Assert.assertEquals("Size error for array", 3, qTester.answers.size)
    }
}