package com.adgonu.artnutria.ui

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.adgonu.artnutria.R
import com.adgonu.artnutria.databinding.ActivityHomeBinding
import com.google.firebase.auth.FirebaseAuth

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityHomeBinding.inflate(layoutInflater).also { binding = it }.root)

        setSupportActionBar(binding.toolbar)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        val navHosFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHosFragment.navController

        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.home,
            R.id.myUser,
            R.id.salir
        )
        )

        NavigationUI.setupWithNavController(binding.toolbar, navController, appBarConfiguration)

    }

    // NAVIGATION
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_graph, menu);
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId){
            R.id.home -> {
                navController.navigate(R.id.homeFragment)
                true
            }
            R.id.myUser -> {
                navController.navigate(R.id.myUserFragment)
                true
            }
            R.id.salir -> {
                FirebaseAuth.getInstance().signOut()

                var intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }


    override fun onBackPressed() {
        //super.onBackPressed()
        val fragmento = findNavController(R.id.nav_host_fragment)

        if (fragmento != null && fragmento.currentDestination?.id == R.id.homeFragment ) {

            finishAffinity()

        } else {

            super.onBackPressed()

        }

    }

}