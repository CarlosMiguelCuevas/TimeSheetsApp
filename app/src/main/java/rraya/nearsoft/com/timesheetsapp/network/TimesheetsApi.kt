package rraya.nearsoft.com.timesheetsapp.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface TimesheetsApi{


  @POST("/auth/firebase")
  fun getTSTokenFromGoogleToken(@Body params: TokenBody,
                                @Header("Content-Type") contentType: String = "application/json")
          : Single<TokenResponse>

}

data class TokenBody(@Expose @SerializedName("token") val token: String)

data class TokenResponse(@Expose @SerializedName("token")val token: String,
                         @Expose @SerializedName("client_id") val clientId: Int,
                         @Expose @SerializedName("user_id") val userId: Int)
