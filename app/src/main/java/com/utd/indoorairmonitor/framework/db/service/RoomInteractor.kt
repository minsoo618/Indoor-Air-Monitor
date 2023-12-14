package com.utd.indoorairmonitor.framework.db.service

import android.content.Context
import com.utd.indoorairmonitor.data.RoomDataSource
import com.utd.indoorairmonitor.domain.DataStore
import com.utd.indoorairmonitor.framework.db.RoomDataStoreDatabase
import com.utd.indoorairmonitor.framework.db.RoomDataStoreEntity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RoomInteractor(val context: Context) : RoomDataSource {
    private val roomDao = RoomDataStoreDatabase.getInstance(context).dataStoreDao()

    override fun add(dataStore: DataStore) {
        GlobalScope.launch {
            roomDao.add(RoomDataStoreEntity(
                    dataStore.id,
                    dataStore.date,
                    dataStore.time,
                    dataStore.airQualityDeviceId,
                    dataStore.airQualityPM2_5,
                    dataStore.airQualityPM10_0,
                    dataStore.peakFlowValue,
                    dataStore.questionnaireAnswers1,
                    dataStore.questionnaireAnswers2,
                    dataStore.questionnaireAnswers3,
                    dataStore.questionnaireAnswers4,
                    dataStore.questionnaireAnswers5,
                    dataStore.questionnaireAnswers6,
                    dataStore.weatherTemperature,
                    dataStore.weatherHumidity
            ))
        }
    }

    //May need to put inside a GlobalScope.launch { } in order to make it work.
    override fun getAll(): List<DataStore> = roomDao.getAll().map {
        DataStore(
                it.id,
                it.date,
                it.time,
                it.airQualityDeviceId,
                it.airQualityPM2_5,
                it.airQualityPM10_0,
                it.peakFlowValue,
                it.questionnaireAnswers1,
                it.questionnaireAnswers2,
                it.questionnaireAnswers3,
                it.questionnaireAnswers4,
                it.questionnaireAnswers5,
                it.questionnaireAnswers6,
                it.weatherTemperature,
                it.weatherHumidity
        )
    }

    //May need to put inside a GlobalScope.launch { } in order to make it work.
    override fun getLatest(): DataStore =
            DataStore(
                    roomDao.getLatest().id,
                    roomDao.getLatest().date,
                    roomDao.getLatest().time,
                    roomDao.getLatest().airQualityDeviceId,
                    roomDao.getLatest().airQualityPM2_5,
                    roomDao.getLatest().airQualityPM10_0,
                    roomDao.getLatest().peakFlowValue,
                    roomDao.getLatest().questionnaireAnswers1,
                    roomDao.getLatest().questionnaireAnswers2,
                    roomDao.getLatest().questionnaireAnswers3,
                    roomDao.getLatest().questionnaireAnswers4,
                    roomDao.getLatest().questionnaireAnswers5,
                    roomDao.getLatest().questionnaireAnswers6,
                    roomDao.getLatest().weatherTemperature,
                    roomDao.getLatest().weatherHumidity
            )

    //May need to put inside a GlobalScope.launch { } in order to make it work.
    override fun getOldest(): DataStore =
            DataStore(
                    roomDao.getOldest().id,
                    roomDao.getOldest().date,
                    roomDao.getOldest().time,
                    roomDao.getOldest().airQualityDeviceId,
                    roomDao.getOldest().airQualityPM2_5,
                    roomDao.getOldest().airQualityPM10_0,
                    roomDao.getOldest().peakFlowValue,
                    roomDao.getOldest().questionnaireAnswers1,
                    roomDao.getOldest().questionnaireAnswers2,
                    roomDao.getOldest().questionnaireAnswers3,
                    roomDao.getOldest().questionnaireAnswers4,
                    roomDao.getOldest().questionnaireAnswers5,
                    roomDao.getOldest().questionnaireAnswers6,
                    roomDao.getOldest().weatherTemperature,
                    roomDao.getOldest().weatherHumidity
            )

    override fun count(): Int = roomDao.count()

    override fun remove(dataStore: DataStore) {
        roomDao.remove(RoomDataStoreEntity(
                dataStore.id,
                dataStore.date,
                dataStore.time,
                dataStore.airQualityDeviceId,
                dataStore.airQualityPM2_5,
                dataStore.airQualityPM10_0,
                dataStore.peakFlowValue,
                dataStore.questionnaireAnswers1,
                dataStore.questionnaireAnswers2,
                dataStore.questionnaireAnswers3,
                dataStore.questionnaireAnswers4,
                dataStore.questionnaireAnswers5,
                dataStore.questionnaireAnswers6,
                dataStore.weatherTemperature,
                dataStore.weatherHumidity
        ))
    }
}