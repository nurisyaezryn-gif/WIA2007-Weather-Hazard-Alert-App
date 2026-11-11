package com.example.animalloverapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Toolbar becomes the app bar
        setSupportActionBar(findViewById<Toolbar>(R.id.toolbar))

        // Find the NavHost and its controller
        val navHost = supportFragmentManager
            .findFragmentById(R.id.NHFMain) as NavHostFragment
        navController = navHost.navController

        // Home and About App are top-level (no back arrow); Cat and Dog get a back arrow
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.DestHome, R.id.DestAboutApp)
        )
        setupActionBarWithNavController(navController, appBarConfiguration)

        // Bottom bar navigates by matching menu item IDs to destination IDs
        findViewById<BottomNavigationView>(R.id.bottom_nav)
            .setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}