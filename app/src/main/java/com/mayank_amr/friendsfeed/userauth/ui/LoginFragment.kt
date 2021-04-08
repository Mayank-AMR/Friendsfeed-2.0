package com.mayank_amr.friendsfeed.userauth.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.mayank_amr.friendsfeed.R
import com.mayank_amr.friendsfeed.base.BaseFragment
import com.mayank_amr.friendsfeed.databinding.LoginFragmentBinding
import com.mayank_amr.friendsfeed.network.Api
import com.mayank_amr.friendsfeed.network.Resource
import com.mayank_amr.friendsfeed.userauth.authrepository.AuthRepository
import com.mayank_amr.friendsfeed.userauth.viewmodel.LoginViewModel
import com.mayank_amr.friendsfeed.utils.enable
import com.mayank_amr.friendsfeed.utils.handleApiError
import com.mayank_amr.friendsfeed.utils.hideKeyboard
import com.mayank_amr.friendsfeed.utils.visible
import kotlinx.coroutines.launch

class LoginFragment :
    BaseFragment<LoginViewModel, /*ViewDataBinding*/LoginFragmentBinding, AuthRepository>() {

    companion object {
        fun newInstance() = LoginFragment()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = DataBindingUtil.bind<LoginFragmentBinding>(view)!!

        binding.buttonSignIn.enable(false)

        binding.textViewCreateAccount.setOnClickListener {
            onCreateNewAccountClick()
        }

        binding.editTextPassword.addTextChangedListener {
            val email = binding.editTextEmail.text.toString().trim()
            binding.buttonSignIn.enable(email.isNotEmpty() && it.toString().isNotEmpty())
        }

        binding!!.buttonSignIn.setOnClickListener {
            hideKeyboard()
            login()


        }

        viewmodel.loginResponse.observe(viewLifecycleOwner, Observer {
            binding!!.progressBarLogin.visible(it is Resource.Loading)
            when (it) {
                is Resource.Success -> {
                    lifecycleScope.launch {
                        viewmodel.saveAuthToken(it.value.message[0].access_token)
                        onSuccessfullyLogin(it.value.message[0].access_token)
                    }
//                    activity?.let { fragmentActivity ->
//                        userPreferences.authToken.asLiveData()
//                            .observe(fragmentActivity, Observer { authToken ->
//                                if (authToken != null)
//                                    onSuccessfullyLogin(authToken)
//                            })
//                    }
                }
                is Resource.Failure -> handleApiError(it) { login() }
            }
        })
    }

    private fun login() {
        val email = binding.editTextEmail.text.toString().trim()
        val password = binding.editTextPassword.text.toString().trim()

        viewmodel.login(email, password)
    }


    private fun onCreateNewAccountClick() {
        findNavController().navigate(
            R.id.action_loginFragment_to_registerFragment,
            null,
            null,
            null
        )
    }

    private fun onSuccessfullyLogin(authToken: String) {
        Toast.makeText(requireContext(), "Login Successful", Toast.LENGTH_LONG).show()
        launchHomeFragment()
    }


    private fun launchHomeFragment() {
        findNavController().navigate(
            R.id.action_loginFragment_to_homeFragment,
            null,
            null,
            null
        )
    }

    override fun getViewModel() = LoginViewModel::class.java

    override fun getFragmentRepository() =
        AuthRepository(remoteDataSource.buildApi(Api::class.java), userPreferences)

    override fun layoutId() = R.layout.login_fragment


}