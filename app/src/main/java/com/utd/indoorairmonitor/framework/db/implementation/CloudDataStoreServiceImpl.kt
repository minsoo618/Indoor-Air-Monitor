package com.utd.indoorairmonitor.framework.db.implementation

import com.utd.indoorairmonitor.domain.DataStore
import com.utd.indoorairmonitor.framework.db.dao.CloudDataStoreDAO
import com.utd.indoorairmonitor.framework.db.service.CloudDataStoreService
import com.utd.indoorairmonitor.framework.db.utility.DAOException
import java.sql.Date
import java.sql.SQLException
import javax.sql.DataSource

class CloudDataStoreServiceImpl(private val dataSource: DataSource) : CloudDataStoreService {
    @Throws(SQLException::class, DAOException::class)
    override fun create(dataStore: DataStore?): DataStore? {
        val connection = dataSource.connection
        try {
            val dataStoreDao: CloudDataStoreDAO = CloudDataStoreDAOImpl()
            dataStoreDao.create(connection, dataStore)
        } finally {
            connection.close()
        }
        return dataStore
    }

    @Throws(SQLException::class, DAOException::class)
    override fun delete(id: Long?): Int {
        val connection = dataSource.connection
        val dataStoreDao: CloudDataStoreDAO = CloudDataStoreDAOImpl()
        return try {
            connection!!.autoCommit = false
            dataStoreDao.delete(connection, id)
        } catch (ex: Exception) {
            connection!!.rollback()
            throw ex
        } finally {
            if (connection != null) {
                connection.autoCommit = true
            }
            if (connection != null && !connection.isClosed) {
                connection.close()
            }
        }
    }

    @Throws(SQLException::class, DAOException::class)
    override fun getAll(): List<DataStore?>? {
        val connection = dataSource.connection
        val dataStoreDao: CloudDataStoreDAO = CloudDataStoreDAOImpl()
        return try {
            connection!!.autoCommit = false
            dataStoreDao.getAll(connection)
        } catch (ex: Exception) {
            connection!!.rollback()
            throw ex
        } finally {
            if (connection != null) {
                connection.autoCommit = true
            }
            if (connection != null && !connection.isClosed) {
                connection.close()
            }
        }
    }

    @Throws(SQLException::class, DAOException::class)
    override fun getByDate(start: Date?, end: Date?): List<DataStore?>? {
        val connection = dataSource.connection
        val dataStoreDao: CloudDataStoreDAO = CloudDataStoreDAOImpl()
        return try {
            connection!!.autoCommit = false
            dataStoreDao.getByDate(connection, start, end)
        } catch (ex: Exception) {
            connection!!.rollback()
            throw ex
        } finally {
            if (connection != null) {
                connection.autoCommit = true
            }
            if (connection != null && !connection.isClosed) {
                connection.close()
            }
        }
    }

    @Throws(SQLException::class, DAOException::class)
    override fun getLatest(): DataStore? {
        val connection = dataSource.connection
        val dataStoreDao: CloudDataStoreDAO = CloudDataStoreDAOImpl()
        return try {
            connection!!.autoCommit = false
            dataStoreDao.getLatest(connection)
        } catch (ex: Exception) {
            connection!!.rollback()
            throw ex
        } finally {
            if (connection != null) {
                connection.autoCommit = true
            }
            if (connection != null && !connection.isClosed) {
                connection.close()
            }
        }
    }

    @get:Throws(SQLException::class, DAOException::class)
    override val oldestId: Long?
        get() {
            val connection = dataSource.connection
            val dataStoreDao: CloudDataStoreDAO = CloudDataStoreDAOImpl()
            return try {
                connection!!.autoCommit = false
                dataStoreDao.getOldestId(connection)
            } catch (ex: Exception) {
                connection!!.rollback()
                throw ex
            } finally {
                if (connection != null) {
                    connection.autoCommit = true
                }
                if (connection != null && !connection.isClosed) {
                    connection.close()
                }
            }
        }

    @Throws(SQLException::class, DAOException::class)
    override fun count(): Int {
        val connection = dataSource.connection
        val dataStoreDao: CloudDataStoreDAO = CloudDataStoreDAOImpl()
        return try {
            connection!!.autoCommit = false
            dataStoreDao.count(connection)
        } catch (ex: Exception) {
            connection!!.rollback()
            throw ex
        } finally {
            if (connection != null) {
                connection.autoCommit = true
            }
            if (connection != null && !connection.isClosed) {
                connection.close()
            }
        }
    }

}