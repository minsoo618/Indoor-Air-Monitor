package com.utd.indoorairmonitor.framework.db.utility

import org.apache.commons.dbcp2.BasicDataSource
import java.io.IOException
import java.util.*
import javax.sql.DataSource

object CloudDataSource {
    /*private var singletonDS: BasicDataSource? = null

    //singletonDS.setUrl(url);
    //singletonDS.setUsername(id);
    //singletonDS.setPassword(password);
    @get:Throws(IOException::class)
    @get:Synchronized
    val dataSource: DataSource?
        get() {
            if (singletonDS == null) {
                val properties = propertiesFromFile
                val url = properties.getProperty("url")
                if (url == null || url.isEmpty()) {
                    throw RuntimeException("'url' not found in configuration file")
                }
                val id = properties.getProperty("id")
                if (id == null || id.isEmpty()) {
                    throw RuntimeException("'id' not found in configuration file")
                }
                val password = properties.getProperty("password")
                if (password == null || password.isEmpty()) {
                    throw RuntimeException("'password' not found in configuration file")
                }
                singletonDS = BasicDataSource()
                singletonDS.setUrl(url);
                singletonDS.setUsername(id);
                singletonDS.setPassword(password);
            }
            return singletonDS
        }

    private const val fileName = "dbconfig.properties"

    @get:Throws(IOException::class)
    val propertiesFromFile: Properties
        get() {
            val properties = Properties()
            val inputStream = CloudDataSource::class.java.classLoader.getResourceAsStream(fileName)
                    ?: throw RuntimeException("file '$fileName' not found in the classpath")
            properties.load(inputStream)
            return properties
        }*/
}