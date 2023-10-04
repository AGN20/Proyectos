package com.adgonu.shoppinglist.ui

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.net.Uri

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.ContactsContract.Directory
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Adapter
import android.widget.Toast
import androidx.collection.arraySetOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.switchMap
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.room.Room
import androidx.room.RoomDatabase
import com.adgonu.shoppinglist.R
import com.adgonu.shoppinglist.adapters.ProductoAdapter
import com.adgonu.shoppinglist.database.MyDao
import com.adgonu.shoppinglist.database.ProductoDao
import com.adgonu.shoppinglist.database.ProductoDatabase
import com.adgonu.shoppinglist.database.entities.ProductoEntity
import com.adgonu.shoppinglist.databinding.ActivityMainBinding
import com.adgonu.shoppinglist.viewmodel.ProductoViewModel
import java.io.*
import java.nio.channels.FileChannel
import java.nio.file.Files
import java.nio.file.StandardCopyOption

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var productViewModel:ProductoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityMainBinding.inflate(layoutInflater).also { binding = it }.root)

        productViewModel = ViewModelProvider(this)[ProductoViewModel::class.java]

        // NAVIGATION
        setSupportActionBar(binding.toolbar)

        val navHosFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHosFragment.navController

        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.options1,
            R.id.options2,
            R.id.options3,
            R.id.options4,
        )
        )

        NavigationUI.setupWithNavController(binding.toolbar, navController, appBarConfiguration)

    }

    // NAVIGATION
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu);
        return true;
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {




        return when (item.itemId){
            R.id.options2 -> {

                productViewModel.deleteAll()
                showMessage("Base de datos eliminada")

                navController?.navigate(R.id.homeFragment)

                true
            }
            R.id.options3 -> {

                createBackup()

                showMessage("Backup Creado")
                true
            }
            R.id.options4 -> {

                restoreBackup()

                showMessage("Backup restaurado")
                true
            }

            else -> super.onOptionsItemSelected(item)
        }

    }

    private fun createBackup(){
        productViewModel.getBackup()
    }


    private fun restoreBackup(){
        productViewModel.readBackup()
    }




    private fun showMessage(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
    }
}

private fun <E> ArrayList<E>.addAll(elements: MutableLiveData<MutableList<E>>) {

}
