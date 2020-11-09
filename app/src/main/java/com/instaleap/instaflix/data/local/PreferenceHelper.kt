package com.instaleap.instaflix.data.local

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

class PreferenceHelper(application: Application) {

    private val sharedPreferences: SharedPreferences = application.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun save(KEY_NAME: String, text: String) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(KEY_NAME, text)
        editor.apply()
    }

    fun save(KEY_NAME: String, value: Int) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putInt(KEY_NAME, value)
        editor.apply()
    }

    fun save(KEY_NAME: String, status: Boolean) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putBoolean(KEY_NAME, status)
        editor.apply()
    }

    fun getValueString(KEY_NAME: String): String? = sharedPreferences.getString(KEY_NAME, null)

    fun getValueInt(KEY_NAME: String): Int = sharedPreferences.getInt(KEY_NAME, 0)

    fun getValueBoolean(KEY_NAME: String, defaultValue: Boolean): Boolean = sharedPreferences.getBoolean(KEY_NAME, defaultValue)

    fun clearSharedPreference() {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

    fun removeValue(KEY_NAME: String) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.remove(KEY_NAME)
        editor.apply()
    }

    companion object {
        private const val PREFS_NAME = "instaflix_preferences"
    }
}