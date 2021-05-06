package com.m.sekoshin.myfinances.ui.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.m.sekoshin.myfinances.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
//        addPreferencesFromResource(R.xml.root_preferences)
    }
}