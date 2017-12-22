package rraya.nearsoft.com.timesheetsapp.dagger

import javax.inject.Named
import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rraya.nearsoft.com.timesheetsapp.BuildConfig
import rraya.nearsoft.com.timesheetsapp.network.TimesheetsApi

/**
 * Created by oaguilar on 12/5/17.
 */

@Module
class NetworkModule {

    @Provides
    @Named(NAME_BASE_URL)
    internal fun provideBaseUrlString(): String {
        return BuildConfig.HOST
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
    internal fun provideTimesheetsApi(retrofit: Retrofit): TimesheetsApi {
        return retrofit.create(TimesheetsApi::class.java)
    }

    companion object {

        private const val NAME_BASE_URL = "NAME_BASE_URL"
    }
}
