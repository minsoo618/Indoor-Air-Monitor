package com.utd.indoorairmonitor.framework

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

object ViewModelFactory : ViewModelProvider.Factory {
  lateinit var application: Application
  lateinit var dependencies: Interactors

  fun inject(application: Application, dependencies: Interactors) {
    ViewModelFactory.application = application
    ViewModelFactory.dependencies = dependencies
  }

  override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    if(ViewModel::class.java.isAssignableFrom(modelClass)) {
      return modelClass.getConstructor(Application::class.java, Interactors::class.java)
        .newInstance(
          application,
          dependencies)
    } else {
      throw IllegalStateException("ViewModel must extend IndoorAirMonitorViewModel")
    }
  }

}
