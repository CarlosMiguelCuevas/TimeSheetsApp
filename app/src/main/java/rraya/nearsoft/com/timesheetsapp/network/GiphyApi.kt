package rraya.nearsoft.com.timesheetsapp.network

import com.google.gson.annotations.SerializedName
import io.reactivex.Single
import retrofit2.http.GET


interface GiphyApi{

    @GET("/v1/gifs/search?q=not+good")
    fun getGiphyNotGoodGifs(): Single<GiphyResponse>

    @GET("/v1/gifs/search?q=well+done")
    fun getGiphyGoodGifs(): Single<GiphyResponse>

}


data class GiphyResponse(
		@SerializedName("data") val imageDatas: List<ImageDatas>,
		@SerializedName("pagination") val pagination: Pagination
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
		@SerializedName("user") val user: User,
		@SerializedName("images") val images: Images,
		@SerializedName("title") val title: String //this is bad behind the scenes GIF by The SpongeBob Musical
)

data class Images(
		@SerializedName("fixed_height_still") val fixedHeightStill: FixedHeightStill,
		@SerializedName("original_still") val originalStill: OriginalStill,
		@SerializedName("fixed_width") val fixedWidth: FixedWidth,
		@SerializedName("fixed_height_small_still") val fixedHeightSmallStill: FixedHeightSmallStill,
		@SerializedName("fixed_height_downsampled") val fixedHeightDownsampled: FixedHeightDownsampled,
		@SerializedName("preview") val preview: Preview,
		@SerializedName("fixed_height_small") val fixedHeightSmall: FixedHeightSmall,
		@SerializedName("downsized_still") val downsizedStill: DownsizedStill,
		@SerializedName("downsized") val downsized: Downsized,
		@SerializedName("downsized_large") val downsizedLarge: DownsizedLarge,
		@SerializedName("fixed_width_small_still") val fixedWidthSmallStill: FixedWidthSmallStill,
		@SerializedName("preview_webp") val previewWebp: PreviewWebp,
		@SerializedName("fixed_width_still") val fixedWidthStill: FixedWidthStill,
		@SerializedName("fixed_width_small") val fixedWidthSmall: FixedWidthSmall,
		@SerializedName("downsized_small") val downsizedSmall: DownsizedSmall,
		@SerializedName("fixed_width_downsampled") val fixedWidthDownsampled: FixedWidthDownsampled,
		@SerializedName("downsized_medium") val downsizedMedium: DownsizedMedium,
		@SerializedName("original") val original: Original,
		@SerializedName("fixed_height") val fixedHeight: FixedHeight,
		@SerializedName("looping") val looping: Looping,
		@SerializedName("original_mp4") val originalMp4: OriginalMp4,
		@SerializedName("preview_gif") val previewGif: PreviewGif,
		@SerializedName("480w_still") val wStill: WStill
)

data class WStill(
		@SerializedName("url") val url: String, //https://media0.giphy.com/media/l0MYM0DsJGr6FmX0Q/480w_s.jpg?cid=e1bb72ff5a3ac5f5746b474132bcdcfe
		@SerializedName("width") val width: String, //480
		@SerializedName("height") val height: String //257
)

data class FixedHeightSmall(
		@SerializedName("url") val url: String, //https://media1.giphy.com/media/l0MYM0DsJGr6FmX0Q/100.gif?cid=e1bb72ff5a3ac5f5746b474132bcdcfe
		@SerializedName("width") val width: String, //187
		@SerializedName("height") val height: String, //100
		@SerializedName("size") val size: String, //713390
		@SerializedName("mp4") val mp4: String, //https://media1.giphy.com/media/l0MYM0DsJGr6FmX0Q/100.mp4?cid=e1bb72ff5a3ac5f5746b474132bcdcfe
		@SerializedName("mp4_size") val mp4Size: String, //47068
		@SerializedName("webp") val webp: String, //https://media1.giphy.com/media/l0MYM0DsJGr6FmX0Q/100.webp?cid=e1bb72ff5a3ac5f5746b474132bcdcfe
		@SerializedName("webp_size") val webpSize: String //367648
)

data class FixedHeightStill(
		@SerializedName("url") val url: String, //https://media1.giphy.com/media/l0MYM0DsJGr6FmX0Q/200_s.gif?cid=e1bb72ff5a3ac5f5746b474132bcdcfe
		@SerializedName("width") val width: String, //374
		@SerializedName("height") val height: String //200
)

data class Downsized(
		@SerializedName("url") val url: String, //https://media1.giphy.com/media/l0MYM0DsJGr6FmX0Q/giphy-downsized.gif?cid=e1bb72ff5a3ac5f5746b474132bcdcfe
		@SerializedName("width") val width: String, //250
		@SerializedName("height") val height: String, //133
		@SerializedName("size") val size: String //1185070
)

data class FixedWidthStill(
		@SerializedName("url") val url: String, //https://media1.giphy.com/media/l0MYM0DsJGr6FmX0Q/200w_s.gif?cid=e1bb72ff5a3ac5f5746b474132bcdcfe
		@SerializedName("width") val width: String, //200
		@SerializedName("height") val height: String //107
)

data class DownsizedSmall(
		@SerializedName("width") val width: String, //407
		@SerializedName("height") val height: String, //218
		@SerializedName("mp4") val mp4: String, //https://media1.giphy.com/media/l0MYM0DsJGr6FmX0Q/giphy-downsized-small.mp4?cid=e1bb72ff5a3ac5f5746b474132bcdcfe
		@SerializedName("mp4_size") val mp4Size: String //176153
)

data class OriginalStill(
		@SerializedName("url") val url: String, //https://media1.giphy.com/media/l0MYM0DsJGr6FmX0Q/giphy_s.gif?cid=e1bb72ff5a3ac5f5746b474132bcdcfe
		@SerializedName("width") val width: String, //628
		@SerializedName("height") val height: String //336
)

data class OriginalMp4(
		@SerializedName("width") val width: String, //480
		@SerializedName("height") val height: String, //256
		@SerializedName("mp4") val mp4: String, //https://media1.giphy.com/media/l0MYM0DsJGr6FmX0Q/giphy.mp4?cid=e1bb72ff5a3ac5f5746b474132bcdcfe
		@SerializedName("mp4_size") val mp4Size: String //189325
)

data class DownsizedStill(
		@SerializedName("url") val url: String, //https://media1.giphy.com/media/l0MYM0DsJGr6FmX0Q/giphy-downsized_s.gif?cid=e1bb72ff5a3ac5f5746b474132bcdcfe
		@SerializedName("width") val width: String, //250
		@SerializedName("height") val height: String, //133
		@SerializedName("size") val size: String //25974
)

data class FixedWidthSmall(
		@SerializedName("url") val url: String, //https://media1.giphy.com/media/l0MYM0DsJGr6FmX0Q/100w.gif?cid=e1bb72ff5a3ac5f5746b474132bcdcfe
		@SerializedName("width") val width: String, //100
		@SerializedName("height") val height: String, //54
		@SerializedName("size") val size: String, //225391
		@SerializedName("mp4") val mp4: String, //https://media1.giphy.com/media/l0MYM0DsJGr6FmX0Q/100w.mp4?cid=e1bb72ff5a3ac5f5746b474132bcdcfe
		@SerializedName("mp4_size") val mp4Size: String, //23160
		@SerializedName("webp") val webp: String, //https://media1.giphy.com/media/l0MYM0DsJGr6FmX0Q/100w.webp?cid=e1bb72ff5a3ac5f5746b474132bcdcfe
		@SerializedName("webp_size") val webpSize: String //142090
)

data class PreviewGif(
		@SerializedName("url") val url: String, //https://media1.giphy.com/media/l0MYM0DsJGr6FmX0Q/giphy-preview.gif?cid=e1bb72ff5a3ac5f5746b474132bcdcfe
		@SerializedName("width") val width: String, //121
		@SerializedName("height") val height: String, //65
		@SerializedName("size") val size: String //49044
)

data class DownsizedLarge(
		@SerializedName("url") val url: String, //https://media1.giphy.com/media/l0MYM0DsJGr6FmX0Q/giphy.gif?cid=e1bb72ff5a3ac5f5746b474132bcdcfe
		@SerializedName("width") val width: String, //628
		@SerializedName("height") val height: String, //336
		@SerializedName("size") val size: String //7322414
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

data class FixedWidthDownsampled(
		@SerializedName("url") val url: String, //https://media1.giphy.com/media/l0MYM0DsJGr6FmX0Q/200w_d.gif?cid=e1bb72ff5a3ac5f5746b474132bcdcfe
		@SerializedName("width") val width: String, //200
		@SerializedName("height") val height: String, //107
		@SerializedName("size") val size: String, //98517
		@SerializedName("webp") val webp: String, //https://media1.giphy.com/media/l0MYM0DsJGr6FmX0Q/200w_d.webp?cid=e1bb72ff5a3ac5f5746b474132bcdcfe
		@SerializedName("webp_size") val webpSize: String //44636
)

data class FixedHeight(
		@SerializedName("url") val url: String, //https://media1.giphy.com/media/l0MYM0DsJGr6FmX0Q/200.gif?cid=e1bb72ff5a3ac5f5746b474132bcdcfe
		@SerializedName("width") val width: String, //374
		@SerializedName("height") val height: String, //200
		@SerializedName("size") val size: String, //2473337
		@SerializedName("mp4") val mp4: String, //https://media1.giphy.com/media/l0MYM0DsJGr6FmX0Q/200.mp4?cid=e1bb72ff5a3ac5f5746b474132bcdcfe
		@SerializedName("mp4_size") val mp4Size: String, //129968
		@SerializedName("webp") val webp: String, //https://media1.giphy.com/media/l0MYM0DsJGr6FmX0Q/200.webp?cid=e1bb72ff5a3ac5f5746b474132bcdcfe
		@SerializedName("webp_size") val webpSize: String //1022266
)

data class PreviewWebp(
		@SerializedName("url") val url: String, //https://media1.giphy.com/media/l0MYM0DsJGr6FmX0Q/giphy-preview.webp?cid=e1bb72ff5a3ac5f5746b474132bcdcfe
		@SerializedName("width") val width: String, //148
		@SerializedName("height") val height: String, //79
		@SerializedName("size") val size: String //48272
)

data class FixedHeightSmallStill(
		@SerializedName("url") val url: String, //https://media1.giphy.com/media/l0MYM0DsJGr6FmX0Q/100_s.gif?cid=e1bb72ff5a3ac5f5746b474132bcdcfe
		@SerializedName("width") val width: String, //187
		@SerializedName("height") val height: String //100
)

data class FixedWidth(
		@SerializedName("url") val url: String, //https://media1.giphy.com/media/l0MYM0DsJGr6FmX0Q/200w.gif?cid=e1bb72ff5a3ac5f5746b474132bcdcfe
		@SerializedName("width") val width: String, //200
		@SerializedName("height") val height: String, //107
		@SerializedName("size") val size: String, //759314
		@SerializedName("mp4") val mp4: String, //https://media1.giphy.com/media/l0MYM0DsJGr6FmX0Q/200w.mp4?cid=e1bb72ff5a3ac5f5746b474132bcdcfe
		@SerializedName("mp4_size") val mp4Size: String, //59590
		@SerializedName("webp") val webp: String, //https://media1.giphy.com/media/l0MYM0DsJGr6FmX0Q/200w.webp?cid=e1bb72ff5a3ac5f5746b474132bcdcfe
		@SerializedName("webp_size") val webpSize: String //401458
)

data class FixedWidthSmallStill(
		@SerializedName("url") val url: String, //https://media1.giphy.com/media/l0MYM0DsJGr6FmX0Q/100w_s.gif?cid=e1bb72ff5a3ac5f5746b474132bcdcfe
		@SerializedName("width") val width: String, //100
		@SerializedName("height") val height: String //54
)

data class Looping(
		@SerializedName("mp4") val mp4: String, //https://media1.giphy.com/media/l0MYM0DsJGr6FmX0Q/giphy-loop.mp4?cid=e1bb72ff5a3ac5f5746b474132bcdcfe
		@SerializedName("mp4_size") val mp4Size: String //1180616
)

data class DownsizedMedium(
		@SerializedName("url") val url: String, //https://media1.giphy.com/media/l0MYM0DsJGr6FmX0Q/giphy-downsized-medium.gif?cid=e1bb72ff5a3ac5f5746b474132bcdcfe
		@SerializedName("width") val width: String, //502
		@SerializedName("height") val height: String, //268
		@SerializedName("size") val size: String //4488384
)

data class FixedHeightDownsampled(
		@SerializedName("url") val url: String, //https://media1.giphy.com/media/l0MYM0DsJGr6FmX0Q/200_d.gif?cid=e1bb72ff5a3ac5f5746b474132bcdcfe
		@SerializedName("width") val width: String, //374
		@SerializedName("height") val height: String, //200
		@SerializedName("size") val size: String, //298151
		@SerializedName("webp") val webp: String, //https://media1.giphy.com/media/l0MYM0DsJGr6FmX0Q/200_d.webp?cid=e1bb72ff5a3ac5f5746b474132bcdcfe
		@SerializedName("webp_size") val webpSize: String //114626
)

data class Preview(
		@SerializedName("width") val width: String, //208
		@SerializedName("height") val height: String, //110
		@SerializedName("mp4") val mp4: String, //https://media1.giphy.com/media/l0MYM0DsJGr6FmX0Q/giphy-preview.mp4?cid=e1bb72ff5a3ac5f5746b474132bcdcfe
		@SerializedName("mp4_size") val mp4Size: String //37008
)

data class User(
		@SerializedName("avatar_url") val avatarUrl: String, //https://media.giphy.com/avatars/spongebobbway/bX5Bs2tlATOr.gif
		@SerializedName("banner_url") val bannerUrl: String, //https://media.giphy.com/headers/spongebobbway/uthzN1sNfQu5.jpg
		@SerializedName("profile_url") val profileUrl: String, //https://giphy.com/spongebobbway/
		@SerializedName("username") val username: String, //spongebobbway
		@SerializedName("display_name") val displayName: String, //The SpongeBob Musical
		@SerializedName("twitter") val twitter: String, //@spongebobbway
		@SerializedName("is_verified") val isVerified: Boolean //true
)

data class Pagination(
		@SerializedName("total_count") val totalCount: Int, //74763
		@SerializedName("count") val count: Int, //25
		@SerializedName("offset") val offset: Int //0
)

