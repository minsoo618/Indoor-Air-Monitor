package com.utd.indoorairmonitor.framework.db.dao

import com.utd.indoorairmonitor.domain.DataStore
import com.utd.indoorairmonitor.framework.db.utility.DAOException
import java.sql.Connection
import java.sql.Date
import java.sql.SQLException

interface CloudDataStoreDAO {
    @Throws(SQLException::class, DAOException::class)
    fun create(connection: Connection?, dataStore: DataStore?): DataStore?

    @Throws(SQLException::class, DAOException::class)
    fun delete(connection: Connection?, id: Long?): Int

    @Throws(SQLException::class, DAOException::class)
    fun getAll(connection: Connection?): List<DataStore?>?

    @Throws(SQLException::class, DAOException::class)
    fun getByDate(connection: Connection?, start: Date?, end: Date?): List<DataStore?>?

    @Throws(SQLException::class, DAOException::class)
    fun getLatest(connection: Connection?): DataStore?

    @Throws(SQLException::class, DAOException::class)
    fun getOldestId(connection: Connection?): Long?

    @Throws(SQLException::class, DAOException::class)
    fun count(connection: Connection?): Int
}