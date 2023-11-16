package com.example.galleryapp

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.galleryapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPref = this.getPreferences(Context.MODE_PRIVATE) ?: return

//        with(binding) {
//            saveName.setOnClickListener {
//                saveData()
//                showData()
//            }
//        }
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