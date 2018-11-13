package com.example.orpuwupetup.basicmvpproject.utils.verifiers

import android.content.Context
import android.preference.PreferenceManager
import android.util.Log
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by m.rafalski@matsuu.com on 09/10/2018.
 */
@Singleton
class CacheExpirationManager @Inject constructor(val context: Context) {

    companion object {
        const val CACHE_NOT_SET = 0L
    }

    fun saveRefresh(propertyName: String){
        val prefsEditor = PreferenceManager.getDefaultSharedPreferences(context).edit()
        with(prefsEditor){
            putLong(propertyName,Date().time)
                .apply()
        }
    }

    fun isCacheOlderThanFifteenMinutes(propertyName: String): Boolean{
        return !isStillValid(propertyName,TimeUnit.MINUTES.toMillis(15))
    }

    fun isCacheOlderThanFiveMinutes(propertyName: String): Boolean{
        return !isStillValid(propertyName,TimeUnit.MINUTES.toMillis(5))
    }

    private fun isStillValid(propertyName: String, validityTime: Long): Boolean{
        val lastSave = PreferenceManager.getDefaultSharedPreferences(context).getLong(propertyName, CACHE_NOT_SET)
        return if(lastSave == CACHE_NOT_SET){
            false
        } else {
            val cachedDataAge = Date().time - lastSave
            cachedDataAge < validityTime
        }
    }
}