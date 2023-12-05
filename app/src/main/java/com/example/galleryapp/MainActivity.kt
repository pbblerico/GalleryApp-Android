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
import com.example.galleryapp.utils.enums.Destination
import com.example.galleryapp.utils.enums.MenuItems
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPref: SharedPreferences

    //todo loading view

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)


        setContentView(binding.root)

        sharedPref = this.getPreferences(Context.MODE_PRIVATE) ?: return


        val nav = findNavController(R.id.nav_host_fragment)
        binding.bottomNavigation.setupWithNavController(nav)

        bottomBarVisibility(nav)
    }

    private fun bottomBarVisibility(nav: NavController) {
        val authorized = false
        //todo hide elements of bottom bar
        binding.bottomNavigation.menu.findItem(Destination.ACCOUNT.fragmentId).isVisible =
            !authorized

        nav.addOnDestinationChangedListener { _, destination, _ ->
            if (MenuItems.values()
                    .map { it.fragmentId }
                    .contains(destination.id)
            ) {
                binding.bottomNavigation.visibility = View.VISIBLE
            } else {
                binding.bottomNavigation.visibility = View.GONE
            }
        }
    }

}
