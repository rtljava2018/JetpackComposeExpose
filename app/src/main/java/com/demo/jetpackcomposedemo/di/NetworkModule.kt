package com.demo.data.di


import com.demo.data.ApiServiceInterface
import com.demo.data.HeadersInterceptor
import com.demo.jetpackcomposedemo.BuildConfig
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesJson(): Json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient,json: Json): Retrofit {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
    }

    @Provides
    @Singleton
    fun provideOkhttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        headersInterceptor: HeadersInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(60L, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(headersInterceptor)
            .build()

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else HttpLoggingInterceptor.Level.NONE
        return HttpLoggingInterceptor().also {
            it.level = level
        }
    }

    @Provides
    @Singleton
    fun provideMoviesApiService(retrofit: Retrofit): ApiServiceInterface =
        retrofit.create(ApiServiceInterface::class.java)

}
