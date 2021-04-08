package com.mayank_amr.friendsfeed.homeviews.home.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.mayank_amr.friendsfeed.R
import com.mayank_amr.friendsfeed.base.BaseFragment
import com.mayank_amr.friendsfeed.databinding.HomeFragmentBinding
import com.mayank_amr.friendsfeed.homeviews.home.data.PostsResponse
import com.mayank_amr.friendsfeed.homeviews.home.recyclerview.HomePostAdapter
import com.mayank_amr.friendsfeed.homeviews.home.repository.PostsRepository
import com.mayank_amr.friendsfeed.homeviews.home.viewmodel.HomeViewModel
import com.mayank_amr.friendsfeed.network.DataApi
import com.mayank_amr.friendsfeed.network.TAG
import com.mayank_amr.friendsfeed.utils.visible
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class HomeFragment : BaseFragment<HomeViewModel, HomeFragmentBinding, PostsRepository>() {

    companion object {
        fun newInstance() = HomeFragment()
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = DataBindingUtil.bind<HomeFragmentBinding>(view)!!

        val adapter = HomePostAdapter()
        binding.apply {
            recyclerView.setHasFixedSize(true)
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.adapter = adapter
        }

        //viewmodel.getPosts()


        viewmodel.pagingPosts.observe(viewLifecycleOwner, {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        })


        /*
        viewmodel.posts.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    binding!!.progressBarPostLoad.visible(false)

                    updateUI(it.value)
                }
                is Resource.Loading -> {
                    binding!!.progressBarPostLoad.visible(true)
                }

                is Resource.Failure -> {
                    binding!!.progressBarPostLoad.visible(false)


                }
            }
        })
        */
    }

    private fun updateUI(posts: PostsResponse) {

    }


    override fun getViewModel() = HomeViewModel::class.java

    override fun getFragmentRepository(): PostsRepository {
        val token = runBlocking { userPreferences.authToken.first() }
        val api = remoteDataSource.buildApi(DataApi::class.java, token)
        return PostsRepository(api)
    }

    override fun layoutId() = R.layout.home_fragment

    override fun onDestroyView() {
        super.onDestroyView()
    }

}