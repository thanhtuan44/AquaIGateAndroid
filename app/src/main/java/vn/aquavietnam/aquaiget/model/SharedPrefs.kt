package vn.aquavietnam.aquaiget.model

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by ThanhTuan on 5/3/2018.
 */

class SharedPrefs private constructor() {
    private val mSharedPreferences: SharedPreferences
    private val PREFS_NAME = "share_prefs"
    init {
        mSharedPreferences = App.self()!!.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    operator fun <T> get(key: String, anonymousClass: Class<T>): T? {
        return if (anonymousClass == String::class.java) {
            mSharedPreferences.getString(key, "") as T
        } else if (anonymousClass == Boolean::class.java) {
            java.lang.Boolean.valueOf(mSharedPreferences.getBoolean(key, false)) as T
        } else if (anonymousClass == Float::class.java) {
            java.lang.Float.valueOf(mSharedPreferences.getFloat(key, 0F)) as T
        } else if (anonymousClass == Int::class.java) {
            Integer.valueOf(mSharedPreferences.getInt(key, 0)) as T
        } else if (anonymousClass == Long::class.java) {
            java.lang.Long.valueOf(mSharedPreferences.getLong(key, 0)) as T
        } else {
            App.self()!!.gSon!!.fromJson(mSharedPreferences.getString(key, ""), anonymousClass)
        }
    }

    fun <T> put(key: String, data: T) {
        val editor = mSharedPreferences.edit()
        if (data is String) {
            editor.putString(key, data as String)
        } else if (data is Boolean) {
            editor.putBoolean(key, data as Boolean)
        } else if (data is Float) {
            editor.putFloat(key, data as Float)
        } else if (data is Int) {
            editor.putInt(key, data as Int)
        } else if (data is Long) {
            editor.putLong(key, data as Long)
        } else {
            editor.putString(key, App.self()!!.gSon!!.toJson(data))
        }
        editor.apply()
    }

    fun clear() {
        mSharedPreferences.edit().clear().apply()
    }

    private object Holder { val INSTANCE = SharedPrefs() }

    companion object {
        val instance: SharedPrefs by lazy { Holder.INSTANCE }
    }

}
