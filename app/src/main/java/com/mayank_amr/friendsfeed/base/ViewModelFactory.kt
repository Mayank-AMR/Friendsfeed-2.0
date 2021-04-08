package com.mayank_amr.friendsfeed.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mayank_amr.friendsfeed.baserepository.BaseRepository
import com.mayank_amr.friendsfeed.homeviews.home.repository.PostsRepository
import com.mayank_amr.friendsfeed.homeviews.home.viewmodel.HomeViewModel
import com.mayank_amr.friendsfeed.userauth.authrepository.AuthRepository
import com.mayank_amr.friendsfeed.userauth.viewmodel.LoginViewModel

/**
 * @Project Friendsfeed
 * @Created_by Mayank Kumar on 30-03-2021 12:45 PM
 */
@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val repository: BaseRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> LoginViewModel(repository as AuthRepository) as T
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(repository as PostsRepository) as T

            else -> throw IllegalArgumentException("ViewModelClass Not Found")
        }
    }
}