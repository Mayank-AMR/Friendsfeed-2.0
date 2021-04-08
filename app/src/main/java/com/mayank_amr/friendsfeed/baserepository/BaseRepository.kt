package com.mayank_amr.friendsfeed.baserepository

import com.mayank_amr.friendsfeed.network.DataApi
import com.mayank_amr.friendsfeed.network.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

/**
 * @Project Friendsfeed
 * @Created_by Mayank Kumar on 30-03-2021 10:31 AM
 */
abstract class BaseRepository {
    suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                // Try executing api call..
                Resource.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> {
                        Resource.Failure(false, throwable.code(), throwable.response()?.errorBody())
                    }
                    else -> {
                        Resource.Failure(true, null, null)
                    }
                }
            }

        }
    }

    suspend fun logout(api:DataApi) = safeApiCall {
        api.logout()
    }
}