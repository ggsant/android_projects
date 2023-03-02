package com.gabriela.coffe.infra

import android.content.Context
import android.content.SharedPreferences

class SecurityPreferences(context: Context) {
    private val securityPreferences: SharedPreferences =
        context.getSharedPreferences("Motivation", Context.MODE_PRIVATE)

    fun saveString(key: String, value: String) = securityPreferences.edit().putString(key, value).apply()

    fun getString(key: String) : String = securityPreferences.getString(key, "") ?: "null"

    fun saveBool(key: String, value: Boolean) = securityPreferences.edit().putBoolean(key, value).apply()

    fun getBool(key: String) : Boolean = securityPreferences.getBoolean(key, false)
}