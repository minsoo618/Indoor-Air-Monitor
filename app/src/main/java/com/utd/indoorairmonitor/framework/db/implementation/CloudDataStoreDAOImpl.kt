package com.utd.indoorairmonitor.framework.db.implementation

import com.utd.indoorairmonitor.domain.DataStore
import com.utd.indoorairmonitor.framework.db.dao.CloudDataStoreDAO
import com.utd.indoorairmonitor.framework.db.utility.DAOException
import java.sql.*
import java.sql.Date
import java.util.*

class CloudDataStoreDAOImpl : CloudDataStoreDAO {
    @Throws(SQLException::class, DAOException::class)
    override fun create(connection: Connection?, dataStore: DataStore?): DataStore? {
        if (dataStore!!.id != Types.NULL.toLong()) {
            throw DAOException("Trying to insert Data Store with non-NULL ID")
        }

        var ps: PreparedStatement? = null
        return try {
            ps = connection!!.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS)
            ps.setDate(1, dataStore.date)
            ps.setTime(2, dataStore.time)
            ps.setString(3, dataStore.airQualityDeviceId)
            ps.setFloat(4, dataStore.airQualityPM2_5)
            ps.setFloat(5, dataStore.airQualityPM10_0)
            ps.setFloat(6, dataStore.peakFlowValue)
            ps.setInt(7, dataStore.questionnaireAnswers1)
            ps.setInt(8, dataStore.questionnaireAnswers2)
            ps.setInt(9, dataStore.questionnaireAnswers3)
            ps.setInt(10, dataStore.questionnaireAnswers4)
            ps.setInt(11, dataStore.questionnaireAnswers5)
            ps.setInt(12, dataStore.questionnaireAnswers6)
            ps.setFloat(13, dataStore.weatherTemperature)
            ps.setFloat(14, dataStore.weatherHumidity)
            ps.executeUpdate()
            val keyRS = ps.generatedKeys
            keyRS.next()
            val lastKey = keyRS.getInt(1)
            dataStore.id = lastKey.toLong()
            dataStore
        } finally {
            if (ps != null && !ps.isClosed) {
                ps.close()
            }
        }
    }

    @Throws(SQLException::class, DAOException::class)
    override fun delete(connection: Connection?, id: Long?): Int {
        if (id == null) {
            throw DAOException("Trying to delete Data Store with NULL ID")
        }
        var ps: PreparedStatement? = null
        return try {
            ps = connection!!.prepareStatement(deleteSQL)
            ps.setLong(1, id)
            ps.executeUpdate()
        } finally {
            if (ps != null && !ps.isClosed) {
                ps.close()
            }
        }
    }

    @Throws(SQLException::class, DAOException::class)
    override fun getAll(connection: Connection?): List<DataStore?>? {
        val result: MutableList<DataStore?> = ArrayList()
        var ps: PreparedStatement? = null
        return try {
            ps = connection!!.prepareStatement(getAllSQL)
            val rs = ps.executeQuery()
            while (rs.next()) {
                val dataStore = extractFromRS(rs)
                result.add(dataStore)
            }
            result
        } finally {
            if (ps != null && !ps.isClosed) {
                ps.close()
            }
        }
    }

    @Throws(SQLException::class, DAOException::class)
    override fun getByDate(connection: Connection?, start: Date?, end: Date?): List<DataStore?>? {
        val result: MutableList<DataStore?> = ArrayList()
        var ps: PreparedStatement? = null
        return try {
            ps = connection!!.prepareStatement(getAllSQL)
            ps.setDate(1, Date(start!!.time))
            ps.setDate(2, Date(end!!.time))
            val rs = ps.executeQuery()
            while (rs.next()) {
                val dataStore = extractFromRS(rs)
                result.add(dataStore)
            }
            result
        } finally {
            if (ps != null && !ps.isClosed) {
                ps.close()
            }
        }
    }

    @Throws(SQLException::class, DAOException::class)
    override fun getLatest(connection: Connection?): DataStore? {
        var ps: PreparedStatement? = null
        return try {
            ps = connection!!.prepareStatement(getLatestSQL)
            val rs = ps.executeQuery()
            if (!rs.next()) {
                return null
            }
            extractFromRS(rs)
        } finally {
            if (ps != null && !ps.isClosed) {
                ps.close()
            }
        }
    }

    @Throws(SQLException::class, DAOException::class)
    override fun getOldestId(connection: Connection?): Long? {
        var ps: PreparedStatement? = null
        return try {
            ps = connection!!.prepareStatement(getOldestSQL)
            val rs = ps.executeQuery()
            if (!rs.next()) {
                return null
            }
            val (id) = extractFromRS(rs)
            id
        } finally {
            if (ps != null && !ps.isClosed) {
                ps.close()
            }
        }
    }

    @Throws(SQLException::class, DAOException::class)
    override fun count(connection: Connection?): Int {
        var ps: PreparedStatement? = null
        return try {
            ps = connection!!.prepareStatement(countSQL)
            val rs = ps.executeQuery()
            if (!rs.next()) {
                throw DAOException("No Count Returned")
            }
            rs.getInt(1)
        } finally {
            if (ps != null && !ps.isClosed) {
                ps.close()
            }
        }
    }

    @Throws(SQLException::class)
    private fun extractFromRS(rs: ResultSet): DataStore {
        val millis = System.currentTimeMillis()
        val date = Date(millis)
        val time = Time(millis)
        val dataStore = DataStore(Types.NULL.toLong(), date, time, "", Types.NULL.toFloat(), Types.NULL.toFloat(), Types.NULL.toFloat(), Types.NULL,
                Types.NULL, Types.NULL, Types.NULL, Types.NULL, Types.NULL,Types.NULL.toFloat(), Types.NULL.toFloat())
        dataStore.id = rs.getLong("id")
        dataStore.date = rs.getDate("date")
        dataStore.time = rs.getTime("time")
        dataStore.airQualityDeviceId = rs.getString("device_id")
        dataStore.airQualityPM2_5 = rs.getFloat("pm2_5")
        dataStore.airQualityPM10_0 = rs.getFloat("pm10_0")
        dataStore.peakFlowValue = rs.getFloat("pf_value")
        dataStore.questionnaireAnswers1 = rs.getInt("q_answer_1")
        dataStore.questionnaireAnswers2 = rs.getInt("q_answer_2")
        dataStore.questionnaireAnswers3 = rs.getInt("q_answer_3")
        dataStore.questionnaireAnswers4 = rs.getInt("q_answer_4")
        dataStore.questionnaireAnswers5 = rs.getInt("q_answer_5")
        dataStore.questionnaireAnswers6 = rs.getInt("q_answer_6")
        dataStore.weatherTemperature = rs.getFloat("temperature")
        dataStore.weatherHumidity = rs.getFloat("humidity")
        return dataStore
    }

    companion object {
        private const val insertSQL = ("INSERT INTO datastore (date,"
                + " time, device_id, pm2_5, pm10_0, pf_value, q_answer_1, q_answer_2,"
                + " q_answer_3, q_answer_4, q_answer_5, q_answer_6, temperature, "
                + "humidity) VALUES"
                + " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);")
        const val deleteSQL = "DELETE FROM datastore WHERE id = ?;"
        const val getAllSQL = "SELECT * FROM datastore;"
        const val getByDate = ("Select id, date, time, device_id,"
                + " pm2_5, pm10_0, pf_value, q_answer_1, q_answer_2, q_answer_3,"
                + " q_answer_4, q_answer_5, q_answer_6,"
                + "temperature, humidity FROM datastore WHERE"
                + " date BETWEEN ? and ?")
        const val getLatestSQL = ("SELECT * FROM datastore"
                + " ORDER BY id DESC LIMIT 1;")
        const val getOldestSQL = ("SELECT * FROM datastore"
                + " ORDER BY id ASC LIMIT 1;")
        const val countSQL = "SELECT COUNT(*) FROM datastore"
    }
}