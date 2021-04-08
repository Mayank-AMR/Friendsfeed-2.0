package com.mayank_amr.friendsfeed.homeviews.home.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.mayank_amr.friendsfeed.baserepository.BaseRepository
import com.mayank_amr.friendsfeed.network.DataApi

/**
 * @Project Friendsfeed
 * @Created_by Mayank Kumar on 02-04-2021 08:19 AM
 */
class PostsRepository(
    private val api: DataApi
) : BaseRepository() {

//    suspend fun getPosts() = safeApiCall {
//        api.getPosts()
//    }


    fun getPostResults() = Pager(
        config = PagingConfig(
            pageSize = 20,
            maxSize = 80,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { PostPagingSource(api) }
    ).liveData
}