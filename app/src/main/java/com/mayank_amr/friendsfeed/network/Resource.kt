package com.mayank_amr.friendsfeed.network

import okhttp3.ResponseBody

/**
 * @Project Friendsfeed
 * @Created_by Mayank Kumar on 30-03-2021 10:11 AM
 */

/**
 * It handles all type of API responses so return type is generic.
 * Used to wrap Api responses to handle the success and failure.
 */
sealed class Resource<out T> {
    data class Success<out T>(val value: T) : Resource<T>()
    data class Failure(
        val isNetworkError: Boolean,
        val errorCode: Int?,
        val errorBody: ResponseBody?
    ) : Resource<Nothing>()

    object Loading:Resource<Nothing>()

}