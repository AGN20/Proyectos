package com.adgonu.p3_navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.adgonu.p3_navigation.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var barraConfiguracion: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.sleep(3000)

        setTheme(R.style.Theme_P3_Navigation)

        super.onCreate(savedInstanceState)

        setContentView(ActivityMainBinding.inflate(layoutInflater).also { binding = it }.root)

        setSupportActionBar(binding.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navigationView: NavigationView = binding.navView

        val fragmentoNavegacionPrincipal = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val ControladorNavegacion = fragmentoNavegacionPrincipal.navController

        //Se añaden los fragmentos que queramos que cuando le demos atras se valla al fragmento principal
        barraConfiguracion = AppBarConfiguration(setOf(
            //Top-level destinations
            R.id.KantoFragment,
            R.id.johtoFragment,
            R.id.hoennFragment
            ),
            drawerLayout
        )

        NavigationUI.setupWithNavController(navigationView, ControladorNavegacion)
        NavigationUI.setupWithNavController(binding.toolbar, ControladorNavegacion, barraConfiguracion)

        val controladorNavegacionBotones =
            (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment?)!!.navController

        NavigationUI.setupWithNavController(binding.bottomNavView, controladorNavegacionBotones);
        NavigationUI.setupWithNavController(binding.toolbar, controladorNavegacionBotones);

    }
}