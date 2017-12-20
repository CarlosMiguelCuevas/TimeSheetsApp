package rraya.nearsoft.com.timesheetsapp

interface IUserPrefs {
    fun getUserToken(): String
    fun setUserToken(token: String)
}