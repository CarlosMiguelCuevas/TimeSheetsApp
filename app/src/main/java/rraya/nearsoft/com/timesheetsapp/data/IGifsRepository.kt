package rraya.nearsoft.com.timesheetsapp.data

import io.reactivex.Single

interface IGifsRepository {

    fun getSuccessGif(): Single<String>

    fun getFailureGif(): Single<String>

    fun isSubmittedOnTime(): Boolean

}