package com.example.galleryapp

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.galleryapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
//        val splashScreen = installSplashScreen()
//        setupSplashScreen(splashScreen = splashScreen)

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)


        setContentView(binding.root)

        sharedPref = this.getPreferences(Context.MODE_PRIVATE) ?: return

        val nav = findNavController(R.id.nav_host_fragment)
        binding.bottomNavigation.setupWithNavController(nav)
    }


    override fun onStart() {
        super.onStart()
        loadData()
    }

    override fun onStop() {
        super.onStop()
//        if(!binding.et.text.isNullOrBlank()) {
//            saveData()
//        }
    }
    private fun loadData() {
        Profile.name = sharedPref.getString(getString(R.string.name), null)
    }
}