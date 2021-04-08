package com.mayank_amr.friendsfeed.homeviews.home.repository

import android.util.Log
import androidx.paging.PagingSource
import com.mayank_amr.friendsfeed.homeviews.home.data.PostsResponse
import com.mayank_amr.friendsfeed.network.DataApi
import retrofit2.HttpException
import java.io.IOException

/**
 * @Project Friendsfeed
 * @Created_by Mayank Kumar on 04-04-2021 01:36 PM
 */

private const val HOME_POST_STARTING_PAGE_INDEX = 1

class PostPagingSource(
    private val api: DataApi
) : PagingSource<Int, PostsResponse.Message>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PostsResponse.Message> {
        val position = params.key ?: HOME_POST_STARTING_PAGE_INDEX

        return try {
            val response = api.getPosts(position)
            val post = response.message

            LoadResult.Page(
                data = post,
                prevKey = if (position == HOME_POST_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (post.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

}