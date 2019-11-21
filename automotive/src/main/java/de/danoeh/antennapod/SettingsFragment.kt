/*
 * Copyright 2019 Google Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.danoeh.antennapod

import android.app.Application
import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.core.content.ContextCompat.startActivity
import android.icu.lang.UCharacter.GraphemeClusterBreak.T




/**
 * Preference fragment hosted by [SettingsActivity]. Handles events to various preference changes.
 */
class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)

    }
    override fun onPreferenceTreeClick(preference: Preference?): Boolean {
        return when (preference?.key) {
            "newAbo" -> {
                val myIntent = Intent(this, )
                myIntent.putExtra("key", value) //Optional parameters
                this.startActivity(myIntent)
                requireActivity().finish()
                true
            }
            else -> {
                super.onPreferenceTreeClick(preference)
            }
        }
    }



}

/**
 * Basic ViewModel for [SettingsFragment].


class SettingsFragmentViewModel(application: Application) : AndroidViewModel(application) {
    private val applicationContext = application.applicationContext
    private val mediaSessionConnection = MediaSessionConnection(
        applicationContext,
        ComponentName(applicationContext, AutomotiveMusicService::class.java)
    )

    fun logout() {
        // Logout is fire and forget.
        mediaSessionConnection.sendCommand(LOGOUT, null)
    }
}
 */