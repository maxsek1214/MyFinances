package com.m.sekoshin.myfinances.data.repository

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class PreferenceRepository(private val sharedPreferences: SharedPreferences) {

    private val themeMode: Int
        get() = when(sharedPreferences.getString(PREFERENCE_THEME_MODE, PREFERENCE_THEME_MODE_DEF_VAL.toString())) {
            "-1" -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
            "1" -> AppCompatDelegate.MODE_NIGHT_NO
            "2" -> AppCompatDelegate.MODE_NIGHT_YES
            else -> PREFERENCE_THEME_MODE_DEF_VAL
        }

    private val _themeModeLive: MutableLiveData<Int> = MutableLiveData()
    val themeModeLive: LiveData<Int>
        get() = _themeModeLive

    private val preferenceChangeListener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
        when(key) {
            PREFERENCE_THEME_MODE -> {_themeModeLive.value = themeMode}
        }
    }

    init{
        // Init preference LiveData objects.
        _themeModeLive.value = themeMode
        sharedPreferences.registerOnSharedPreferenceChangeListener(preferenceChangeListener)
    }

    companion object{
        private const val PREFERENCE_THEME_MODE = "theme"
        private const val PREFERENCE_THEME_MODE_DEF_VAL = AppCompatDelegate.MODE_NIGHT_NO
    }
}