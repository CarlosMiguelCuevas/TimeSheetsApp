package rraya.nearsoft.com.timesheetsapp.network

import com.google.gson.annotations.SerializedName
import io.reactivex.Single
import retrofit2.http.GET


interface GiphyApi {

    @GET("/v1/gifs/search?q=not+good")
    fun getGiphyNotGoodGifs(): Single<GiphyResponse>

    @GET("/v1/gifs/search?q=well+done")
    fun getGiphyGoodGifs(): Single<GiphyResponse>

}


data class GiphyResponse(
        @SerializedName("data") val imageDatas: List<ImageDatas>
)

data class ImageDatas(
        @SerializedName("type") val type: String, //gif
        @SerializedName("id") val id: String, //l0MYM0DsJGr6FmX0Q
        @SerializedName("slug") val slug: String, //spongebobbway-behind-the-scenes-rehearsal-spongebob-musical-l0MYM0DsJGr6FmX0Q
        @SerializedName("url") val url: String, //https://giphy.com/gifs/spongebobbway-behind-the-scenes-rehearsal-spongebob-musical-l0MYM0DsJGr6FmX0Q
        @SerializedName("bitly_gif_url") val bitlyGifUrl: String, //https://gph.is/2cfC0aB
        @SerializedName("bitly_url") val bitlyUrl: String, //https://gph.is/2cfC0aB
        @SerializedName("embed_url") val embedUrl: String, //https://giphy.com/embed/l0MYM0DsJGr6FmX0Q
        @SerializedName("username") val username: String, //spongebobbway
        @SerializedName("source") val source: String, //https://thespongebobmusical.com/
        @SerializedName("rating") val rating: String, //g
        @SerializedName("content_url") val contentUrl: String,
        @SerializedName("source_tld") val sourceTld: String, //thespongebobmusical.com
        @SerializedName("source_post_url") val sourcePostUrl: String, //https://thespongebobmusical.com/
        @SerializedName("is_indexable") val isIndexable: Int, //0
        @SerializedName("is_sticker") val isSticker: Int, //0
        @SerializedName("import_datetime") val importDatetime: String, //2016-08-31 22:17:03
        @SerializedName("trending_datetime") val trendingDatetime: String, //0000-00-00 00:00:00
        @SerializedName("images") val images: Images,
        @SerializedName("title") val title: String //this is bad behind the scenes GIF by The SpongeBob Musical
)

data class Images(
        @SerializedName("original") val original: Original,
        @SerializedName("preview_gif") val previewGif: PreviewGif
)


data class PreviewGif(
        @SerializedName("url") val url: String, //https://media1.giphy.com/media/l0MYM0DsJGr6FmX0Q/giphy-preview.gif?cid=e1bb72ff5a3ac5f5746b474132bcdcfe
        @SerializedName("width") val width: String, //121
        @SerializedName("height") val height: String, //65
        @SerializedName("size") val size: String //49044
)


data class Original(
        @SerializedName("url") val url: String, //https://media1.giphy.com/media/l0MYM0DsJGr6FmX0Q/giphy.gif?cid=e1bb72ff5a3ac5f5746b474132bcdcfe
        @SerializedName("width") val width: String, //628
        @SerializedName("height") val height: String, //336
        @SerializedName("size") val size: String, //7322414
        @SerializedName("frames") val frames: String, //56
        @SerializedName("mp4") val mp4: String, //https://media1.giphy.com/media/l0MYM0DsJGr6FmX0Q/giphy.mp4?cid=e1bb72ff5a3ac5f5746b474132bcdcfe
        @SerializedName("mp4_size") val mp4Size: String, //189325
        @SerializedName("webp") val webp: String, //https://media1.giphy.com/media/l0MYM0DsJGr6FmX0Q/giphy.webp?cid=e1bb72ff5a3ac5f5746b474132bcdcfe
        @SerializedName("webp_size") val webpSize: String //2690146
)



