package com.o9tech.mytestafertoneday.data.SharedPrefrenceManager

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject


class SharedPrefrenceMa @Inject constructor(private val context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_TOKEN = "auth_token"
        private const val KEY_COOKIES = "auth_cookies"
    }

    fun saveToken(token: String) {
        sharedPreferences.edit().putString(KEY_TOKEN, token).apply()
    }

    fun getToken(): String? {
        return sharedPreferences.getString(KEY_TOKEN, null)
    }

    fun clearToken() {
        sharedPreferences.edit().remove(KEY_TOKEN).apply()
    }

    fun saveCookies(cookies: String) {
        sharedPreferences.edit().putString(KEY_COOKIES, cookies).apply()
    }

    fun getCookies(): String? {
        return sharedPreferences.getString(KEY_COOKIES, null)
    }
    fun clearCookies() {
        sharedPreferences.edit().remove(KEY_COOKIES).apply()
    }

}