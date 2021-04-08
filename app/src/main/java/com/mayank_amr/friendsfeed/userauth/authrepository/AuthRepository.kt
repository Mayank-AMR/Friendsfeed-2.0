package com.mayank_amr.friendsfeed.userauth.authrepository

import com.mayank_amr.friendsfeed.baserepository.BaseRepository
import com.mayank_amr.friendsfeed.network.Api
import com.mayank_amr.friendsfeed.userauth.data.UserPreferences

/**
 * @Project Friendsfeed
 * @Created_by Mayank Kumar on 30-03-2021 10:53 AM
 */
class AuthRepository(
    private val api: Api,
    private val preferences: UserPreferences
) : BaseRepository() {
    private val ACCEPT_TYPE = "application/json"

    suspend fun login(
        email: String,
        password: String
    ) = safeApiCall {
        api.login(ACCEPT_TYPE, email, password)
    }

    suspend fun saveAuthToken(token: String) {
        preferences.saveAuthToken(token)
    }
}