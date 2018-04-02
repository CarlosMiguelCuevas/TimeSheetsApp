package rraya.nearsoft.com.timesheetsapp.dagger.applicationScopeModules

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rraya.nearsoft.com.timesheetsapp.BuildConfig
import rraya.nearsoft.com.timesheetsapp.network.GiphyApi
import rraya.nearsoft.com.timesheetsapp.network.TimesheetsApi
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    companion object {
        private const val NAME_BASE_URL = "NAME_BASE_URL"
        private const val GIPHY_BASE_URL = "GIPHY_BASE_URL"
        private const val GIPHY_API_KEY = "GIPHY_API_KEY"
        private const val GIPHY_RETROFIT = "GIPHY_RETROFIT"
        private const val GIPHY_HTTP_CLIENT = "GIPHY_HTTP_CLIENT"
    }

    @Provides
    @Singleton
    @Named(NAME_BASE_URL)
    internal fun provideBaseUrlString(): String {
        return BuildConfig.HOST
    }

    @Provides
    @Singleton
    @Named(GIPHY_BASE_URL)
    internal fun provideGiphyUrlString(): String {
        return BuildConfig.GIPHY_BACKEND
    }

    @Provides
    @Singleton
    @Named(GIPHY_API_KEY)
    internal fun provideGiphyApiKeyString(): String {
        return BuildConfig.GIPHY_API_KEY
    }

    @Provides
    @Singleton
    internal fun provideGsonConverter(): Converter.Factory {
        return GsonConverterFactory.create()
    }


    @Provides
    @Singleton
    internal fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }


    @Provides
    @Singleton
    internal fun provideOkHttpClient(logging: HttpLoggingInterceptor): OkHttpClient.Builder {
        val httpClientBuilder = OkHttpClient.Builder()
        httpClientBuilder.addInterceptor(logging)
        return httpClientBuilder
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(converter: Converter.Factory, @Named(NAME_BASE_URL) baseUrl: String, httpClientBuilder: OkHttpClient.Builder): Retrofit {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(converter)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClientBuilder.build())
                .build()
    }


    @Provides
    @Singleton
    @Named(GIPHY_HTTP_CLIENT)
    internal fun provideGiphyOkHttpClient(logging: HttpLoggingInterceptor, @Named(GIPHY_API_KEY) apiKey: String): OkHttpClient.Builder {
        val httpClientBuilder = OkHttpClient.Builder()
        httpClientBuilder.addInterceptor(logging)
        httpClientBuilder.addInterceptor {
            val original = it.request()
            val originalHttpUrl = original.url()
            val url = originalHttpUrl.newBuilder()
                    .addQueryParameter("api_key", apiKey)
                    .build()
            it.proceed(original.newBuilder().url(url).build())
        }
        return httpClientBuilder
    }

    @Provides
    @Singleton
    @Named(GIPHY_RETROFIT)
    internal fun provideGiphyRetrofit(converter: Converter.Factory, @Named(GIPHY_BASE_URL) baseUrl: String, @Named(GIPHY_HTTP_CLIENT) httpClientBuilder: OkHttpClient.Builder): Retrofit {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(converter)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClientBuilder.build())
                .build()
    }

    @Provides
    @Singleton
    internal fun provideGiphyApi(@Named(GIPHY_RETROFIT) retrofit: Retrofit): GiphyApi {
        return retrofit.create(GiphyApi::class.java)
    }

    @Provides
    @Singleton
    internal fun provideTimesheetsApi(retrofit: Retrofit): TimesheetsApi {
        return retrofit.create(TimesheetsApi::class.java)
    }

}
