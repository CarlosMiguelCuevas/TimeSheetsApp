package rraya.nearsoft.com.timesheetsapp

import android.content.Context

/**
 * Created by oaguilar on 12/15/17.
 */
class UserPrefs(val context: Context): IUserPrefs {
    companion object {
        private val TOKEN_TAG = "Token"
        private val PREFS_NAME = "rraya.nearsoft.com.timesheetsapp.settings"
    }

    override fun getUserToken(): String {
        val settings = context.getSharedPreferences(UserPrefs.PREFS_NAME, 0)
        return settings.getString(UserPrefs.TOKEN_TAG, "")
    }

    override fun setUserToken(token: String) {
        val settings = context.getSharedPreferences(UserPrefs.PREFS_NAME, 0)
        val editor = settings.edit()
        editor.putString(UserPrefs.TOKEN_TAG, token)
        editor.commit()
    }
}

