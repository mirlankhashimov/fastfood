package kg.mirlan.fastfood.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.lifecycle.LiveData
import kg.mirlan.fastfood.BuildConfig

class AppPreferences(context: Context) {
    private var preferences: SharedPreferences

    init {
        preferences = context.getSharedPreferences(SETTINGS_STORAGE__NAME, Context.MODE_PRIVATE)
    }

    var shopNameKey: String?
        get() {
            return preferences.getString(CITY_NAME_KEY, SELECTED_CITY_NAME_KEY)
        }
        set(value) {
            preferences.edit {
                putString(CITY_NAME_KEY, value)
            }
        }
    var userId: String?
        get() {
            return preferences.getString(USER_ID, SELECTED_USER_ID)
        }
        set(value) {
            preferences.edit {
                putString(USER_ID, value)
            }
        }

    val shopNameKeyLive = object : LiveData<String?>() {
        val listener =
            SharedPreferences.OnSharedPreferenceChangeListener { sharedPreferences, key ->
                if (key == CITY_NAME_KEY) {
                    value = sharedPreferences.getString(CITY_NAME_KEY, SELECTED_CITY_NAME_KEY)
                }
            }

        override fun onActive() {
            super.onActive()
            value = preferences.getString(CITY_NAME_KEY, SELECTED_CITY_NAME_KEY)
            preferences.registerOnSharedPreferenceChangeListener(listener)
        }

        override fun onInactive() {
            super.onInactive()
            preferences.unregisterOnSharedPreferenceChangeListener(listener)
        }
    }

    fun clear() = preferences.edit().clear().apply()

    companion object {
        private const val SETTINGS_STORAGE__NAME = BuildConfig.APPLICATION_ID
        private const val CITY_NAME_KEY = "city_name_key"
        private const val SELECTED_CITY_NAME_KEY = ""
        private const val USER_ID = "city_key"
        private const val SELECTED_USER_ID = ""
    }
}