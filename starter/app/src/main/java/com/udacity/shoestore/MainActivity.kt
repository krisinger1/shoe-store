package com.udacity.shoestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Timber.plant(Timber.DebugTree())
        val navController = findNavController(R.id.nav_host_fragment)
        //val appBarConfiguration = AppBarConfiguration(navController.graph)
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.login_destination,R.id.welcome_destination, R.id.instructions_destination, R.id.shoe_list_destination))

        findViewById<Toolbar>(R.id.toolbar).setupWithNavController(navController, appBarConfiguration)
        setSupportActionBar(toolbar)
        NavigationUI.setupActionBarWithNavController(this, navController,appBarConfiguration)
    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return NavigationUI.navigateUp(navController, AppBarConfiguration(navController.graph))
    }

 //   override fun onBackPressed() {

//        //val f : Fragment? = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
//
//        if (f != null) {
//            Timber.i("${f.id}")
//        }
//        if (f is WelcomeFragment){
//            //do nothing
//        }
//        else {
//            super.onBackPressed()
  //      }
 //   }
}
