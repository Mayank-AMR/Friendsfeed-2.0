package com.mayank_amr.friendsfeed.network

import com.mayank_amr.friendsfeed.homeviews.home.data.PostsResponse
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * @Project Friendsfeed
 * @Created_by Mayank Kumar on 01-04-2021 01:24 PM
 */
interface DataApi {
    @GET("users/get")
    suspend fun getPosts(
        @Query("page") page: Int
    ): PostsResponse

    @POST("logout")
    suspend fun logout(): ResponseBody
}