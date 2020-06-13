package com.core.prefrence.secured_shared_preferences

import android.content.Context
import android.content.SharedPreferences
import com.core.prefrence.SharedPreference

class SecuredSharedPreference constructor(context: Context, var prefName: String) :
    SharedPreference {

    private val mPrefs: SharedPreferences =
        context.getSharedPreferences(prefName, Context.MODE_PRIVATE)

    override fun getString(key: String): String {
        return mPrefs.getString(key, "")!!
    }

    override fun saveString(key: String, value: String) {
        mPrefs.edit().putString(key, value).apply()
    }

    override fun getInt(key: String): Int {
        return mPrefs.getInt(key, 0)
    }

    override fun saveInt(key: String, value: Int) {
        mPrefs.edit().putInt(key, value).apply()
    }

    override fun getBoolean(key: String): Boolean {
        return mPrefs.getBoolean(key, false)
    }

    override fun saveBoolean(key: String, value: Boolean) {
        mPrefs.edit().putBoolean(key, value).apply()
    }
}
