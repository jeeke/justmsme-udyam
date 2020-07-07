package com.justmsme.udyam

import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme_Light)
        super.onCreate(savedInstanceState)
        window?.requestFeature(Window.FEATURE_PROGRESS)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

//        val fab: FloatingActionButton = findViewById(R.id.fab)
//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home,
                R.id.nav_about,
                R.id.nav_contact,
                R.id.nav_faqs,
                R.id.nav_process
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onBackPressed() {
        return when (navController.currentDestination?.id) {
            R.id.nav_home_second -> {
                this.showDialog("Are you sure you want to go back?", "Go Back", "Cancel", {
                    super.onBackPressed()
                }, {})
            }
            R.id.nav_home -> {
                this.showDialog("Are you sure you want to exit the app?", "Exit", "Cancel", {
                    super.onBackPressed()
                }, {})
            }
            else -> super.onBackPressed()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return if (navController.currentDestination?.id == R.id.nav_home_second) {
            this.showDialog("Are you sure you want to go back?", "Go Back", "Cancel", {
                navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
            }, {})
            true
        } else navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}