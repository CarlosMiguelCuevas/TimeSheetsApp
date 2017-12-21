package rraya.nearsoft.com.timesheetsapp.data

import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.Request
import rraya.nearsoft.com.timesheetsapp.BuildConfig
import rraya.nearsoft.com.timesheetsapp.network.GiphyApi
import rraya.nearsoft.com.timesheetsapp.network.GiphyResponse
import java.util.*


class GifsRepository(val api: GiphyApi) : IGifsRepository {


    override fun getWellDoneGif(): Single<ByteArray> {
        return api.getGiphyGoodGifs().map {
            getRandomGifFromResponse(it)
        }.map {
            getImageFromUrl(it)
        }
    }

    override fun getNotGoodGif(): Single<ByteArray> {
        return api.getGiphyNotGoodGifs().map {
            getRandomGifFromResponse(it)
        }.map {
            getImageFromUrl(it)
        }
    }

    private fun getRandomGifFromResponse(it: GiphyResponse): String {
        val arrLength = it.imageDatas.size
        val randomNumber = Random().nextInt(arrLength)
        return it.imageDatas[randomNumber].images.original.url
    }

    private fun getImageFromUrl(imageUrl: String): ByteArray {

        val httpClientBuilder = OkHttpClient.Builder()
        httpClientBuilder.addInterceptor {
            val original = it.request()
            val originalHttpUrl = it.request().url()
            val url = originalHttpUrl.newBuilder()
                    .addQueryParameter("api_key", BuildConfig.GIPHY_API_KEY)
                    .build()
            it.proceed(original.newBuilder().url(url).build())
        }

        val request = Request.Builder().url(imageUrl).build()
        val response = httpClientBuilder.build().newCall(request).execute()
        return response.body()!!.bytes()
    }
}