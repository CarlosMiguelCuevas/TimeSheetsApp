package rraya.nearsoft.com.timesheetsapp

import android.content.Context

/**
 * Created by oaguilar on 12/15/17.
 */
class Repository {
    companion object {
        private val TOKEN_TAG = "Token"
        private val PREFS_NAME = "rraya.nearsoft.com.timesheetsapp.settings"

        fun getUserToken(activityContext: Context): String {
            val settings = activityContext.getSharedPreferences(Repository.PREFS_NAME, 0)
            return settings.getString(Repository.TOKEN_TAG, "")
        }

        fun setUserToken(activityContext: Context, token: String) {
            val settings = activityContext.getSharedPreferences(Repository.PREFS_NAME, 0)
            val editor = settings.edit()
            editor.putString(Repository.TOKEN_TAG, token)
            editor.commit()
        }
    }
}