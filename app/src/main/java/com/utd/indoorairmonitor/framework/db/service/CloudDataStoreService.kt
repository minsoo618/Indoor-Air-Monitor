package com.utd.indoorairmonitor.framework.db.service

import com.utd.indoorairmonitor.domain.DataStore
import com.utd.indoorairmonitor.framework.db.utility.DAOException
import java.sql.Date
import java.sql.SQLException

interface CloudDataStoreService {
    @Throws(SQLException::class, DAOException::class)
    fun create(dataStore: DataStore?): DataStore?

    @Throws(SQLException::class, DAOException::class)
    fun delete(id: Long?): Int

    @Throws(SQLException::class, DAOException::class)
    fun getAll(): List<DataStore?>?

    @Throws(SQLException::class, DAOException::class)
    fun getByDate(start: Date?, end: Date?): List<DataStore?>?

    @Throws(SQLException::class, DAOException::class)
    fun getLatest(): DataStore?

    @get:Throws(SQLException::class, DAOException::class)
    val oldestId: Long?

    @Throws(SQLException::class, DAOException::class)
    fun count(): Int
}