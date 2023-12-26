package com.sevenwinds.data.utils

import android.content.Context
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import com.sevenwinds.data.TOKEN

class SharedPreferencesHelper(private val context: Context) {

    private val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    fun checkToken(): Boolean {
        return sharedPreferences.contains(TOKEN)
    }

    fun setToken(token: String) {
        sharedPreferences.edit {
            putString(TOKEN, token)
        }
    }

    fun getToken(): String {
        return sharedPreferences.getString(TOKEN, "") ?: ""
    }

    fun removeToken() {
        sharedPreferences.edit {
            remove(TOKEN)
        }
    }
}