package com.utd.indoorairmonitor.framework.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date;
import java.sql.Time;

@Entity(tableName = "data_store")
data class RoomDataStoreEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "date") val date: Date,
    @ColumnInfo(name = "time") val time: Time,
    @ColumnInfo(name = "air_quality_device_id") val airQualityDeviceId: String,
    @ColumnInfo(name = "air_quality_pm_2_5") val airQualityPM2_5: Float,
    @ColumnInfo(name = "air_quality_pm_10_0") val airQualityPM10_0: Float,
    @ColumnInfo(name = "peak_flow") val peakFlowValue: Float,
    @ColumnInfo(name = "questionnaire_answer_1") val questionnaireAnswers1: Int,
    @ColumnInfo(name = "questionnaire_answer_2") val questionnaireAnswers2: Int,
    @ColumnInfo(name = "questionnaire_answer_3") val questionnaireAnswers3: Int,
    @ColumnInfo(name = "questionnaire_answer_4") val questionnaireAnswers4: Int,
    @ColumnInfo(name = "questionnaire_answer_5") val questionnaireAnswers5: Int,
    @ColumnInfo(name = "questionnaire_answer_6") val questionnaireAnswers6: Int,
    @ColumnInfo(name = "temperature") val weatherTemperature: Float,
    @ColumnInfo(name = "humidity") val weatherHumidity: Float
)