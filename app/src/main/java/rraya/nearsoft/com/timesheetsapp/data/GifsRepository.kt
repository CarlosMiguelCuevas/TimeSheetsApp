package rraya.nearsoft.com.timesheetsapp.data

import io.reactivex.Single
import rraya.nearsoft.com.timesheetsapp.network.GiphyApi
import rraya.nearsoft.com.timesheetsapp.network.GiphyResponse
import java.util.*


class GifsRepository(val api: GiphyApi) : IGifsRepository {

    companion object {
        private val good = "well done"
        private val notGood = "not good"
    }

    override fun getWellDoneGif(): Single<String> {
        return handleApiResponse(good)
    }

    override fun getNotGoodGif(): Single<String> {
        return handleApiResponse(notGood)
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

}