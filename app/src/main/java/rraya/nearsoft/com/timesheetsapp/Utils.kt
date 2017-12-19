package rraya.nearsoft.com.timesheetsapp

import android.content.Context

/**
 * Created by oaguilar on 12/15/17.
 */
class Utils {
    companion object {
        private val TOKEN_TAG = "Token"
        private val PREFS_NAME = "rraya.nearsoft.com.timesheetsapp.settings"

        fun getUserToken(activityContext: Context): String {
            val settings = activityContext.getSharedPreferences(Utils.PREFS_NAME, 0)
            return settings.getString(Utils.TOKEN_TAG, "")
        }

        fun setUserToken(activityContext: Context, token: String) {
            val settings = activityContext.getSharedPreferences(Utils.PREFS_NAME, 0)
            val editor = settings.edit()
            editor.putString(Utils.TOKEN_TAG, token)
            editor.commit()
        }
    }
}