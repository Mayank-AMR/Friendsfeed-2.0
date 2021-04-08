package com.mayank_amr.friendsfeed.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.mayank_amr.friendsfeed.baserepository.BaseRepository
import com.mayank_amr.friendsfeed.baseviewmodel.BaseViewModel
import com.mayank_amr.friendsfeed.network.DataApi
import com.mayank_amr.friendsfeed.network.RemoteDataSource
import com.mayank_amr.friendsfeed.userauth.data.UserPreferences
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch


/**
 * @Project Friendsfeed
 * @Created_by Mayank Kumar on 30-03-2021 11:07 AM
 */

abstract class BaseFragment<VM : BaseViewModel, B : ViewDataBinding, BR : BaseRepository> : Fragment() {
    protected val remoteDataSource = RemoteDataSource()
    protected lateinit var binding: B
    protected lateinit var viewmodel: VM

    protected lateinit var userPreferences: UserPreferences


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        userPreferences = UserPreferences(requireContext())
        val factory = ViewModelFactory(getFragmentRepository())
        viewmodel = ViewModelProvider(this, factory).get(getViewModel())

        /*
         * It will load the token in main memory when fragment create and and whenever need it will
         * deliver quickly that will not create ANR.
         */
        lifecycleScope.launch { userPreferences.authToken.first() }

        return inflater.inflate(layoutId(), container, false)
    }

    fun logout() = lifecycleScope.launch{
        val authToken = userPreferences.authToken.first()
        val api = remoteDataSource.buildApi(DataApi::class.java,authToken)
        viewmodel.logout(api)
        userPreferences.clearAuthToken()
        showLogoutMessage()

    }

    fun showLogoutMessage(){
        //todo need to design global fragment to show session expire
    }

    abstract fun getViewModel(): Class<VM>


    abstract fun getFragmentRepository(): BR

    abstract fun layoutId(): Int




}