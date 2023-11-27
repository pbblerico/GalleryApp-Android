package com.example.galleryapp

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.galleryapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)


        setContentView(binding.root)

        sharedPref = this.getPreferences(Context.MODE_PRIVATE) ?: return

        val nav = findNavController(R.id.nav_host_fragment)
        binding.bottomNavigation.setupWithNavController(nav)

        bottomBarVisibility(nav)
    }

    private fun changeAuth() {
        val auth = sharedPref.getBoolean("auth", false)
        with(sharedPref.edit()) {
            putBoolean("auth", !auth)
        }
    }



    //todo change with enum
    private fun bottomBarVisibility(nav: NavController) {
        nav.addOnDestinationChangedListener {_, destination, _ ->
            when(destination.id) {
                R.id.searchFragment,
                R.id.homeFragment,
                R.id.accountFragment -> binding.bottomNavigation.visibility = View.VISIBLE
                else -> binding.bottomNavigation.visibility = View.GONE
            }
        }
    }

}