package com.utd.indoorairmonitor.framework

import com.utd.indoorairmonitor.interactors.*

data class Interactors(
        val airMonitorUseCases: AirMonitorUseCases,
        val weatherUseCases: WeatherUseCases,
        val mlModelUseCases: MLModelUseCases,
        val PeakFlowUseCases: PeakFlowUseCases,
        val questionnaireUseCases: QuestionnaireUseCases,
        val cloudDBUseCases: CloudDBUseCases,
        val roomDBUseCases: RoomDBUseCases)
