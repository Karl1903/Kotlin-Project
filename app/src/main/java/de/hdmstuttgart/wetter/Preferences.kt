package de.hdmstuttgart.wetter

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

class Preferences internal constructor(private val context: Context){

companion object{
    private const val PREFERENCES_NAME = "weather_preferences"
    private const val KEY_TOWN = "town"

    @SuppressLint("StaticFieldLeak")
    private var instance: Preferences? = null

    fun getInstance(context: Context) : Preferences{
        if(instance == null){
            instance = instance(context.applicationContext)

             }
        return instance!! }
}


private val preferences: SharedPreferences by lazy{
    context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
}
    fun setValue(key: String, value: String){
        preferences.edit().putString(key, value)

    }

    fun getValue(key: String): String?{

        return preferences.getString(key, null)
    }

    fun setValueOrNull(key: String?, value: String?){
        if (key != null && value != null) {
            preferences.edit().putString(key, value).apply()
        }
    }

    fun getValueOrNull(key: String?): String?{
        if (key != null) {
            return preferences.getString(key, null)
        }
        return null
    }

    fun clearTownValue(){
        preferences.edit().remove(KEY_TOWN).apply()
    }
}