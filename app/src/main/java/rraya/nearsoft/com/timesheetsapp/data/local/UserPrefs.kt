package rraya.nearsoft.com.timesheetsapp.data.local

import android.content.Context

class UserPrefs(val context: Context) : IUserPrefs {
    companion object {
        private val TOKEN_TAG = "Token"
        private val USER_ID_TAG = "UserId"
        private val PREFS_NAME = "rraya.nearsoft.com.timesheetsapp.settings"
    }

    private val settings = context.getSharedPreferences(PREFS_NAME, 0)

    override fun getUserToken(): String {
        return settings.getString(TOKEN_TAG, "")
    }

    override fun setUserToken(token: String) {
        val editor = settings.edit()
        editor.putString(TOKEN_TAG, token)
        editor.apply()
    }

    override fun getUserId(): Int {
        return settings.getInt(USER_ID_TAG, -1)
    }

    override fun setUserUserId(userId: Int) {
        val editor = settings.edit()
        editor.putInt(USER_ID_TAG, userId)
        editor.apply()
    }
}

