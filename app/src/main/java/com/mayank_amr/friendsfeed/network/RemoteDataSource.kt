package com.mayank_amr.friendsfeed.network

import com.mayank_amr.friendsfeed.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @Project Friendsfeed
 * @Created_by Mayank Kumar on 30-03-2021 09:37 AM
 */
class RemoteDataSource {
    companion object {
        private const val BASE_URL = "https://friendsfeed.herokuapp.com/api/"
        private const val ACCEPT_TYPE = "application/json"
    }

    fun <Api> buildApi(
        api: Class<Api>,
        authToken: String? = null
    ): Api {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor { chain ->
                        chain.proceed(chain.request().newBuilder().also {
                            it.addHeader("Authorization", "Bearer $authToken")
                            it.addHeader("Accept", ACCEPT_TYPE)
                        }.build())

                    }.also { client ->
                        if (BuildConfig.DEBUG) {
                            val logging = HttpLoggingInterceptor()
                            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                            client.addInterceptor(logging)
                        }
                    }.build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)
    }
}