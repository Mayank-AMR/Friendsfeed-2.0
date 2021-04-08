package com.mayank_amr.friendsfeed.network

import com.mayank_amr.friendsfeed.userauth.responses.LoginResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

/**
 * @Project Friendsfeed
 * @Created_by Mayank Kumar on 30-03-2021 09:29 AM
 */
interface Api {

    @FormUrlEncoded
    @POST("users/login")
    suspend fun login(
        @Header("Accept") accept: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse
}