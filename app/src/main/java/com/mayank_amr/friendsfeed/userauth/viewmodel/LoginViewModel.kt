package com.mayank_amr.friendsfeed.userauth.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mayank_amr.friendsfeed.baseviewmodel.BaseViewModel
import com.mayank_amr.friendsfeed.network.Resource
import com.mayank_amr.friendsfeed.userauth.authrepository.AuthRepository
import com.mayank_amr.friendsfeed.userauth.responses.LoginResponse
import kotlinx.coroutines.launch


class LoginViewModel(
    private val repository: AuthRepository
) : BaseViewModel(repository) {
    private val _loginResponse: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val loginResponse: LiveData<Resource<LoginResponse>>
        get() = _loginResponse

    fun login(
        email: String,
        password: String
    ) = viewModelScope.launch {
        _loginResponse.value = Resource.Loading
        _loginResponse.value = repository.login(email, password)

    }

    suspend fun saveAuthToken(token: String)  {
        repository.saveAuthToken(token)
    }


}