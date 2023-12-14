package com.utd.indoorairmonitor.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.utd.indoorairmonitor.R
import com.utd.indoorairmonitor.databinding.ActivityMainBinding
import com.utd.indoorairmonitor.data.RoomDataStoreRepository
import com.utd.indoorairmonitor.framework.db.service.RoomInteractor

class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this,
                R.layout.activity_main)
        val roomDataStoreRepository = RoomDataStoreRepository(RoomInteractor(this))
        // Navigation drawer binding
        drawerLayout = binding.drawerLayout
        // let navigation controller controls drawer navigation and action bar navigation
        val navController = this.findNavController(R.id.myNavHostFragment)
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        NavigationUI.setupWithNavController(binding.navView, navController)

        // supportFragmentManager.beginTransaction().add(R.id.homeFragment, HomeFragment.newInstance(),"AirMonitor")
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        //return navController.navigateUp()
        return NavigationUI.navigateUp(navController, drawerLayout)
    }
}