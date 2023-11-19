package com.example.galleryapp

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.galleryapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPref = this.getPreferences(Context.MODE_PRIVATE) ?: return

//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
//        val navController = navHostFragment.navController
//        val navView: BottomNavigationView = binding.bottomNavigation
//        navView.setupWithNavController(navController)
        val nav = findNavController(R.id.nav_host_fragment)
        binding.bottomNavigation.setupWithNavController(nav)

    }

    override fun onStart() {
        super.onStart()
        loadData()
        Profile.name?.let {
            showData()
        }
    }

    override fun onStop() {
        super.onStop()
//        if(!binding.et.text.isNullOrBlank()) {
//            saveData()
//        }
    }

    private fun saveData() {
//        if(!binding.et.text.toString().isNullOrBlank()) {
//            Profile.name = binding.et.text.toString()
//            with(sharedPref.edit()) {
//                putString(getString(R.string.name), Profile.name)
//                apply()
//            }
//        }
    }

    private fun showData() {
//        Profile.name?.let {
//            binding.name.text = Profile.name
//        }
    }

    private fun loadData() {
        Profile.name = sharedPref.getString(getString(R.string.name), null)
    }
}