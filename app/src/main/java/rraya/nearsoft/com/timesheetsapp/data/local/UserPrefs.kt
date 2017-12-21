package rraya.nearsoft.com.timesheetsapp.data.local

import android.content.Context

class UserPrefs(val context: Context) : IUserPrefs {
    companion object {
        private val TOKEN_TAG = "Token"
        private val PREFS_NAME = "rraya.nearsoft.com.timesheetsapp.settings"
    }

    override fun getUserToken(): String {
        val settings = context.getSharedPreferences(PREFS_NAME, 0)
        return settings.getString(TOKEN_TAG, "")
    }

    override fun setUserToken(token: String) {
        val settings = context.getSharedPreferences(PREFS_NAME, 0)
        val editor = settings.edit()
        editor.putString(TOKEN_TAG, token)
        editor.commit()
    }
}

