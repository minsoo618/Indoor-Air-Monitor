package com.utd.indoorairmonitor.framework

import android.app.Application
import com.utd.indoorairmonitor.data.*
import com.utd.indoorairmonitor.framework.asthmaMLModel.AsthmaMLModel
import com.utd.indoorairmonitor.framework.db.service.CloudDataStoreInteractor
import com.utd.indoorairmonitor.framework.db.service.RoomInteractor
import com.utd.indoorairmonitor.framework.openWeather.OpenWeatherAPI
import com.utd.indoorairmonitor.framework.purpleAirMonitor.PurpleAirMonitorAPI
import com.utd.indoorairmonitor.framework.peakflow.PeakFlowDataSourceImpl
import com.utd.indoorairmonitor.framework.questionnaire.QuestionnaireDataSourceImpl
import com.utd.indoorairmonitor.interactors.*

class Application : Application()  {

    override fun onCreate() {
        val airMonitorRepository = AirMonitorRepository(PurpleAirMonitorAPI())
        val weatherRepository = WeatherRepository(OpenWeatherAPI())
        val mlModelRepository = MLModelRepository(AsthmaMLModel())
        val peakFlowRepository = PeakFlowRepository(PeakFlowDataSourceImpl())
        val questionnaireRepository
                = QuestionnaireRepository(QuestionnaireDataSourceImpl())
        val roomDataStoreRepository = RoomDataStoreRepository(RoomInteractor(this))
        val cloudDataStoreRepository = CloudDataStoreRepository(CloudDataStoreInteractor(this))

        ViewModelFactory.inject(this,
                Interactors(
                        AirMonitorUseCases(airMonitorRepository),
                        WeatherUseCases(weatherRepository),
                        MLModelUseCases(mlModelRepository),
                        PeakFlowUseCases(peakFlowRepository),
                        QuestionnaireUseCases(questionnaireRepository),
                        CloudDBUseCases(cloudDataStoreRepository),
                        RoomDBUseCases(roomDataStoreRepository)
                )
        )
        super.onCreate()
    }
}