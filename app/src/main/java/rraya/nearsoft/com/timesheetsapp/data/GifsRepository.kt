package rraya.nearsoft.com.timesheetsapp.data

import io.reactivex.Single
import rraya.nearsoft.com.timesheetsapp.network.GiphyApi
import rraya.nearsoft.com.timesheetsapp.network.GiphyResponse
import java.util.*


class GifsRepository(val api: GiphyApi, private val submittedOnTime: Boolean = false) : IGifsRepository {

    companion object {
        private const val good = "well done"
        private const val notGood = "not good"
        private const val fail = "fail"
    }

    override fun getSuccessGif(): Single<String> {
        if (submittedOnTime) {
            return handleApiResponse(good)
        }
        return handleApiResponse(notGood)
    }

    override fun getFailureGif(): Single<String> {
        return handleApiResponse(fail)
    }

    private fun handleApiResponse(queryParameter: String): Single<String> {
        return api.getGiphyGifs(queryParameter)
                .map { getRandomGifFromResponse(it) }
    }

    private fun getRandomGifFromResponse(giphyResponse: GiphyResponse): String {
        val arrLength = giphyResponse.imageDatas.size
        val randomNumber = Random().nextInt(arrLength)
        return giphyResponse.imageDatas[randomNumber].images.original.url
    }

    override fun isSubmittedOnTime(): Boolean {
        return submittedOnTime
    }

}