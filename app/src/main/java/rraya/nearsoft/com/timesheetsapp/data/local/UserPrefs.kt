package rraya.nearsoft.com.timesheetsapp.data.local

import android.content.Context
import javax.inject.Inject

class UserPrefs() : IUserPrefs {

    //    TODO:show in presentation, needed to accomplish @Binds
    @Inject
    constructor(context: Context) : this() {
        mContext = context
    }

    lateinit var mContext: Context

    companion object {
        private val TOKEN_TAG = "Token"
        private val PREFS_NAME = "rraya.nearsoft.com.timesheetsapp.settings"
    }

    override fun getUserToken(): String {
        val settings = mContext.getSharedPreferences(PREFS_NAME, 0)
        return settings.getString(TOKEN_TAG, "")
    }

    override fun setUserToken(token: String) {
        val settings = mContext.getSharedPreferences(PREFS_NAME, 0)
        val editor = settings.edit()
        editor.putString(TOKEN_TAG, token)
        editor.commit()
    }

}

