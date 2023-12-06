package com.example.galleryapp.data.preferences

import android.content.SharedPreferences

class PreferencesUtils(private var preferences: SharedPreferences) {
    fun getString(key: Preferences, default: String? = null): String {
        return preferences.getString(key.name, default).orEmpty()
    }
    fun getInt(key: Preferences, default: Int = 0): Int {
        return preferences.getInt(key.name, default)
    }
    fun getBoolean(key: Preferences, default: Boolean = false): Boolean {
        return preferences.getBoolean(key.name, default)
    }

    fun remove(key: Preferences) {
        preferences.edit().remove(key.name).apply()
    }

    fun saveString(key: Preferences, value: String) {
        val editor = preferences.edit()
        editor.putString(key.name, value)
        editor.apply()
    }
    fun saveBoolean(key: Preferences, value: Boolean) {
        val editor = preferences.edit()
        editor.putBoolean(key.name, value)
        editor.apply()
    }
    fun saveInt(key: Preferences, value: Int) {
        val editor = preferences.edit()
        editor.putInt(key.name, value)
        editor.apply()
    }
}