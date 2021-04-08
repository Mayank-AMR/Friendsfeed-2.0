package com.mayank_amr.friendsfeed.baseviewmodel

import androidx.lifecycle.ViewModel
import com.mayank_amr.friendsfeed.baserepository.BaseRepository
import com.mayank_amr.friendsfeed.network.DataApi

/**
 * @Project Friendsfeed
 * @Created_by Mayank Kumar on 02-04-2021 10:44 AM
 */
abstract class BaseViewModel(
    private val repository: BaseRepository
) : ViewModel() {

    // Need to call this in Dispatcher IO
    suspend fun logout(api:DataApi) = repository.logout(api)

}