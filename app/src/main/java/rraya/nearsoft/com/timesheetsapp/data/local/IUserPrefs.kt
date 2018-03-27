package rraya.nearsoft.com.timesheetsapp.data.local

interface IUserPrefs {

    fun getUserToken(): String

    fun setUserToken(token: String)

    fun getUserId(): Int

    fun setUserUserId(userId: Int)

}