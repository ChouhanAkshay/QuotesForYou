package com.example.quotesforyou.di

import com.example.quotesforyou.BuildConfig
import com.example.quotesforyou.data.service.Service
import com.example.quotesforyou.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class Network {
    @Provides
    @Named(BASE_URL)
    fun provideBaseUrl(): String {
        return BuildConfig.BASE_URL
    }

    @Provides
    fun provideRetrofit(
        @Named(BASE_URL) baseUrl: String,
        okhttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .client(okhttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
    }

    @Provides
    fun provideClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .addInterceptor{
                val originalRequest = it.request()
                val newRequestBuilder = originalRequest.newBuilder()
                newRequestBuilder.addHeader("Accept", "*/*")
                it.proceed(newRequestBuilder.build())
            }
            .connectTimeout(90, TimeUnit.SECONDS)
            .writeTimeout(180, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    fun provideRetrofitService(retrofit: Retrofit): Service {
        return retrofit.create(Service::class.java)
    }
}