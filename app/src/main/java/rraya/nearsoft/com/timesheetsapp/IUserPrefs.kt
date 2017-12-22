package rraya.nearsoft.com.timesheetsapp

/**
 * Created by oaguilar on 12/19/17.
 */
interface IUserPrefs {
    fun getUserToken(): String
    fun setUserToken(token: String)
}