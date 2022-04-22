package com.adonis.cvshealth.data.preferences

import android.content.Context
import android.content.SharedPreferences
import com.adonis.cvshealth.patterns.SingletonHolder
import com.adonis.cvshealth.utils.Utils.Companion.SHARED_PREFERENCES

class SharedPreferencesManager private constructor(context: Context) {

    var preferences: SharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)

    fun setValue(key: String, value: String) {
        val editor = preferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun setBooleanValue(key: String, value: Boolean) {
        val editor = preferences.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun setValue(key: String, value: HashSet<String>) {
        val editor = preferences.edit()
        editor.putStringSet(key, value)
        editor.apply()
    }

    fun getValue(key: String): String {
        return preferences.getString(key, "0") ?: ""
    }

    fun getBooleanValue(key: String): Boolean {
        return preferences.getBoolean(key, false)
    }

    fun getSetValue(key: String): HashSet<String> {
        return preferences.getStringSet(key, hashSetOf()) as HashSet<String>
    }

    companion object : SingletonHolder<SharedPreferencesManager, Context>(::SharedPreferencesManager)
}