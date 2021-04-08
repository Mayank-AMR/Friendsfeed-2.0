package com.mayank_amr.friendsfeed.homeviews.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.mayank_amr.friendsfeed.baseviewmodel.BaseViewModel
import com.mayank_amr.friendsfeed.homeviews.home.data.PostsResponse
import com.mayank_amr.friendsfeed.homeviews.home.repository.PostsRepository
import com.mayank_amr.friendsfeed.network.Resource
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: PostsRepository
) : BaseViewModel(repository) {

//    private val _posts: MutableLiveData<Resource<PostsResponse>> = MutableLiveData()
//    val posts: LiveData<Resource<PostsResponse>>
//        get() = _posts

    lateinit var pagingPosts: LiveData<PagingData<PostsResponse.Message>>

    init {

        viewModelScope.launch {
            pagingPosts = repository.getPostResults()
        }
    }


    fun loadPosts() = viewModelScope.launch {
//        _posts.value = Resource.Loading // For Loading State..
    }

    override fun onCleared() {
        super.onCleared()

    }

}