package rraya.nearsoft.com.timesheetsapp.network

import com.google.gson.annotations.SerializedName
import io.reactivex.Single
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface TimesheetsApi{

  @POST("/api/token_request/")
  fun getTSTokenFromGoogleToken(@Body params: RequestBody) : Single<Response<TokenBody>>

}

data class TokenBody(@SerializedName("Token") val token: String)